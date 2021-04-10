package shops;

import hooks.DiscordBot;
import items.Item;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashSet;

@Getter
@Setter
@Slf4j
public abstract class Shop implements Runnable
{
    private String name;
    private int timeout;
    private HashSet<Item> itemList;

    public static boolean discordEnabled = false;
    public static DiscordBot bot;

    public Shop(String name, int timeout)
    {
        this.name = name;
        this.timeout = timeout;
    }
    public abstract int checkCurrentStatus(Item itemToCheck);
    public abstract double checkPrice(Item itemToCheck);
    public abstract void checkPage(Item itemToCheck) throws IOException;

    @Override
    public void run() {
        itemList.forEach(item -> {
            log.debug("Starting " + item.getName());
            final Thread t = new Thread(item);
            t.start();
            try {
                log.trace("Sleeping...");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.error("Thread interrupted on start!");
            }
            log.debug("Finished starting " + item.getLink());
        });
    }
    public static void enableDiscord(DiscordBot botInstance)
    {
        if (discordEnabled)
        {
            log.error("Discord already enabled!");
        }
        else
        {
            discordEnabled = true;
            bot = botInstance;
        }
        log.debug("Discord enabled!");
    }
}
