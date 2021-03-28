package shops;

import cards.Card;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

public class Amazon extends Shop
{
    final static private String NAME = "Amazon";
    private int timeout;
    private Document lastPage;
    public Amazon(int timeout)
    {
        super(NAME, timeout);
    }
    @Override
    public int getCurrentStatus(Card cardToCheck)
    {

        int result = -100;
        try
        {
            String url = cardToCheck.getLink();
            lastPage = Jsoup.connect(url)
                    .header("Host", "www.amazon.com")
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:87.0) Gecko/20100101 Firefox/87.0")
                    .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                    .header("Accept-Language", "en-US,en;q=0.5")
                    .header("Accept-Encoding", "gzip, deflate, br")
                    .header("Connection", "keep-alive")
                    .header("Upgrade-Insecure-Requests", "1")
                    .get();
            Element e = lastPage.selectFirst("#addToCart_feature_div");
            if (e != null)
            {
                result = 1;
            }
            else
            {
                e = lastPage.getElementsContainingText("enter the characters you see below").first();
                if (e != null)
                {
                    result = 0;
                }
                else
                {
                    result = -1;
                }
            }
        }
        catch (IOException e)
        {
            result = 100;
        }
        return result;
    }

    @Override
    public double getPrice(Card cardToCheck)
    {
        Element e = lastPage.selectFirst("#price_inside_buybox");
        return Double.parseDouble(e.text().substring(1));
    }
}
