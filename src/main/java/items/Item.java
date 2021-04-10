package items;

import hooks.DiscordBot;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import shops.Shop;

import java.io.IOException;

@Getter
@Slf4j
public class Item implements Runnable
{
    private String name;
    private String link;
    private Shop shop;
    private boolean inStock;
    private boolean running;
    private static boolean isDiscordEnabled = false;
    private static DiscordBot bot;

    public Item(String name, String link, Shop shop) {
        this.name = name;
        this.link = link;
        this.shop = shop;
        running = false;
        inStock = false;
    }
    @Override
    public void run() {
        running = true;
        while(running) {
            try {
                shop.checkPage(this);
            }
            catch (IOException e) {
                log.error("Error fetching " + this.getName() + " is link incorrect?");
            }
            log.trace("Checking status of " + this.getName());
            int status = shop.checkCurrentStatus(this);
            if (status == 1) {
                log.trace("Checking price!");
                double price = shop.checkPrice(this);
                System.out.println("Card: " + '\n' + name + " is in stock"  + " at " + shop.getName() + '!' + '\n' + link + '\n' + "for " + price);
                if (isDiscordEnabled && !inStock) {
                    bot.sendStockAlert(this, price);
                }
                inStock = true;
            }
            if (status == -1) {
                log.debug("Encountered captcha for card " + this.getName() + " at shop " + this.getShop().getName());
            }
            if (status == 0) {
                log.debug("Card " + this.getName() + " at shop " + this.getShop().getName() + " is out of stock");
                inStock = false;
            }
            try
            {
                Thread.sleep(shop.getTimeout());
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
    public void stop()
    {
        running = false;
    }
}
