import cards.Card;
import com.google.common.base.Stopwatch;
import shops.Amazon;
import shops.Shop;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Main
{
    public static final Shop AMAZON = new Amazon();
    public static ArrayList<Card> cards;
    public static void main(String[] args)
    {
        Stopwatch timer = Stopwatch.createStarted();
        Shop amazon = new Amazon();
        System.out.println("Beginning test!");
        cards = getCardList();
        for (int i = 0; i < cards.size(); i++)
        {
            cards.get(i).run();
        }
        timer.stop();
        System.out.println(timer.elapsed(TimeUnit.MILLISECONDS));
    }
    public static ArrayList<Card> getCardList()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card("ASUS Dual NVIDIA GeForce RTX 3060 Ti OC Edition", "https://www.amazon.com/dp/B08P2HBBLX", AMAZON));
        cards.add(new Card("Gigabyte RTX 3060 Ti Gaming OC", "https://www.amazon.com/dp/B08NYP7KG6", AMAZON));
        cards.add(new Card("Gigabyte RTX 3060 Ti Gaming OC PRO", "https://www.amazon.com/dp/B08NYPLXPJ", AMAZON));
        cards.add(new Card("Gigabyte RTX 3060 Ti Eagle", "https://www.amazon.com/dp/B08NYNJ6RC", AMAZON));
        cards.add(new Card("ASUS TUF RTX 3060 Ti", "https://www.amazon.com/dp/B083Z5P6TX", AMAZON));
        cards.add(new Card("MSI RTX 3060 Ti Gaming X Trio", "https://www.amazon.com/dp/B08P2D3JSG", AMAZON));
        cards.add(new Card("MSI RTX 3060 Ti Ventus 2X OC", "https://www.amazon.com/dp/B08P2DQ28S", AMAZON));
        cards.add(new Card("Zotac RTX 3060 Ti Twin Edge", "https://www.amazon.com/dp/B08P3XJLJJ", AMAZON));
        cards.add(new Card("Zotac RTX 3060 Ti Twin Edge OC", "https://www.amazon.com/dp/B08P3V572B", AMAZON));
        cards.add(new Card("ASUS KO RTX 3060 Ti OC", "https://www.amazon.com/dp/B08P2D1JZZ", AMAZON));
        return cards;
    }

}
