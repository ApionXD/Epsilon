package cards;

import lombok.Getter;
import shops.Shop;

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
