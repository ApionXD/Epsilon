package shops;

import cards.Card;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

public class Amazon extends Shop
{
    final ArrayList<Card> CARDS = new ArrayList<Card>();
    final static private String NAME = "Amazon";
    public Amazon()
    {
        super(NAME);
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
                    .header("Accept-Encoding", "gzip, deflate")
                    .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
                    .header("DNT", "1")
                    .header("Connection", "Close")
                    .header("Upgrade-Insecure-Requests", "1")
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
