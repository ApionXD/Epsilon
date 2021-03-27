package shops;

import cards.Card;
import com.google.common.base.Stopwatch;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.Connection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Amazon extends Shop implements Runnable
{
    final ArrayList<Card> CARDS = new ArrayList<Card>();
    final private String NAME = "Amazon";
    public Amazon()
    {
        super();
        addCards();
        setCards(CARDS);
        setName(NAME);
    }
    private void addCards()
    {
        CARDS.add(new Card("ASUS Dual NVIDIA GeForce RTX 3060 Ti OC Edition", "https://www.amazon.com/dp/B08P2HBBLX"));
        CARDS.add(new Card("Gigabyte RTX 3060 Ti Gaming OC", "https://www.amazon.com/dp/B08NYP7KG6"));
        CARDS.add(new Card("Gigabyte RTX 3060 Ti Gaming OC PRO", "https://www.amazon.com/dp/B08NYPLXPJ"));
        CARDS.add(new Card("Gigabyte RTX 3060 Ti Eagle", "https://www.amazon.com/dp/B08NYNJ6RC"));
        CARDS.add(new Card("ASUS TUF RTX 3060 Ti", "https://www.amazon.com/dp/B083Z5P6TX"));
        CARDS.add(new Card("MSI RTX 3060 Ti Gaming X Trio", "https://www.amazon.com/dp/B08P2D3JSG"));
        CARDS.add(new Card("MSI RTX 3060 Ti Ventus 2X OC", "https://www.amazon.com/dp/B08P2DQ28S"));
        CARDS.add(new Card("Zotac RTX 3060 Ti Twin Edge", "https://www.amazon.com/dp/B08P3XJLJJ"));
        CARDS.add(new Card("Zotac RTX 3060 Ti Twin Edge OC", "https://www.amazon.com/dp/B08P3V572B"));
        CARDS.add(new Card("ASUS KO RTX 3060 Ti OC", "https://www.amazon.com/dp/B08P2D1JZZ"));

    }
    @Override
    public String getCurrentStatus(int numCard)
    {

        String result = "";
        try
        {

            String url = CARDS.get(numCard).getLink();
            Stopwatch timer = Stopwatch.createStarted();
            Document d = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X x.y; rv:42.0) Gecko/20100101 Firefox/42.0")
                    .get();
            timer.stop();
            Element e = d.selectFirst("#addToCart_feature_div");

            System.out.println(timer.elapsed(TimeUnit.MILLISECONDS));
            if (e != null)
            {
                result = "In stock!";
            }
            else
            {
                e = d.getElementsContainingText("enter the characters you see below").first();
                if (e != null)
                {
                    result = "Encountered a captcha";
                }
                else
                {
                    result = "Out of Stock";
                }
            }
        }
        catch (IOException e)
        {
            result = "Link " + CARDS.get(numCard).getLink() + " not found!";
        }
        return result;
    }

    @Override
    public double getPrice() {
        return 0;
    }

    @Override
    public void run()
    {
            
    }
}
