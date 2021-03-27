package shops;

import cards.Card;
import com.google.common.base.Stopwatch;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Amazon extends Shop
{
    final ArrayList<Card> CARDS = new ArrayList<Card>();
    final private String NAME = "Amazon";
    public Amazon()
    {
        super();
    }
    @Override
    public String getCurrentStatus(Card cardToCheck)
    {

        String result = "";
        try
        {
            String url = cardToCheck.getLink();
            Document d = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X x.y; rv:42.0) Gecko/20100101 Firefox/42.0")
                    .get();
            Element e = d.selectFirst("#addToCart_feature_div");
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
            result = "Link " + cardToCheck.getLink() + " not found!";
        }
        return result;
    }

    @Override
    public double getPrice() {
        return 0;
    }
}
