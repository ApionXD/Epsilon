package cards;

import hooks.DiscordBot;
import lombok.Getter;
import shops.Shop;

import java.io.IOException;

@Getter
public class Card implements Runnable
{
    private String name;
    private String link;
    private Shop shop;
    private int verbosity;
    private boolean inStock;
    private static boolean isDiscordEnabled = false;

    private static DiscordBot bot;

    public Card(String name, String link, Shop shop, int verbosity)
    {
        this.name = name;
        this.link = link;
        this.shop = shop;
        this.verbosity = verbosity;
        inStock = false;
    }
    public static void enableDiscord(String token, String channel, String name)
    {
        if (isDiscordEnabled)
        {
            System.out.println("Discord already enabled");
        }
        else
        {
            isDiscordEnabled = true;
            bot = new DiscordBot(token, channel, name);
        }
    }
    @Override
    public void run()
    {
        while(true)
        {
            try
            {
                shop.checkPage(this);
            }
            catch (IOException e)
            {
                System.out.println("There was an error getting " + this.getLink());
            }
            int status = shop.checkCurrentStatus(this);
            if (status == 1 && verbosity >= 0)
            {
                double price = shop.checkPrice(this);
                System.out.println("Card: " + '\n' + name + " is in stock"  + " at " + shop.getName() + '!' + '\n' + link + '\n' + "for " + price);
                if (isDiscordEnabled && !inStock)
                {
                    bot.sendStockAlert(this, price);
                }
                inStock = true;
            }
            else if (verbosity >= 1)
            {
                if (status == -1)
                {
                    System.out.println("Card: " + name + '\n' + "Shop: " + shop.getName() + '\n' + "Status: " + "Encountered captcha" + '\n');
                }
                if (status == 0)
                {
                    System.out.println("Card: " + name + '\n' + "Shop: " + shop.getName() + '\n' + "Status: " + "Out of stock" + '\n');
                    inStock = false;
                }
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
}
