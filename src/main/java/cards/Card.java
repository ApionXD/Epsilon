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
    public Card(String name, String link, Shop shop)
    {
        this.name = name;
        this.link = link;
        this.shop = shop;
    }

    @Override
    public void run()
    {
        System.out.println("Card: " + name + '\n' + "Shop: " + shop.getName() + '\n' + "Status: " + shop.getCurrentStatus(this) + '\n');
    }
}
