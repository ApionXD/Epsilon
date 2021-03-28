package cards;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import lombok.Getter;
import shops.Shop;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

@Getter
public class Card implements Runnable
{
    private String name;
    private String link;
    private Shop shop;
    private int verbosity;
    public Card(String name, String link, Shop shop, int verbosity)
    {
        this.name = name;
        this.link = link;
        this.shop = shop;
        this.verbosity = verbosity;
    }

    @Override
    public void run()
    {
        while(true)
        {
            int status = shop.getCurrentStatus(this);
            if (status == 1 && verbosity == 1)
            {
                System.out.println("Card: " + '\n' + name + " is in stock"  + " at " + shop.getName() + '!' + '\n' + link + '\n' + "for " + shop.getPrice(this));
            }
            else if (verbosity == 2)
            {
                if (status == -1)
                {
                    System.out.println("Card: " + name + '\n' + "Shop: " + shop.getName() + '\n' + "Status: " + "Encountered captcha" + '\n');
                }
                if (status == 0)
                {
                    System.out.println("Card: " + name + '\n' + "Shop: " + shop.getName() + '\n' + "Status: " + "Out of stock" + '\n');
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
