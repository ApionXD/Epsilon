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
            double random = Math.random();
            if (random <= 0.2)
            {
                lastPage = Jsoup.connect(url)
                        .header("Host", "www.amazon.com")
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:87.0) Gecko/20100101 Firefox/87.0")
                        .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                        .header("Accept-Language", "en-US,en;q=0.5")
                        .header("Accept-Encoding", "gzip, deflate, br")
                        .header("Connection", "keep-alive")
                        .header("Upgrade-Insecure-Requests", "1")
                        .header("Cache-Control", "max-age=0")
                        .header("TE", "Trailers").get();
            }
            else if (random <= 0.4)
            {
                lastPage = Jsoup.connect(url)
                        .header("Host", "www.amazon.com")
                        .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36")
                        .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                        .header("Accept-Language", "en-US,en;q=0.5")
                        .header("Accept-Encoding", "gzip, deflate, br")
                        .header("Connection", "keep-alive")
                        .header("Upgrade-Insecure-Requests", "1")
                        .header("Cache-Control", "max-age=0")
                        .header("TE", "Trailers").get();
            }
            else if (random <= 0.6)
            {
                lastPage = Jsoup.connect(url)
                        .header("Host", "www.amazon.com")
                        .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36 OPR/38.0.2220.41")
                        .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                        .header("Accept-Language", "en-US,en;q=0.5")
                        .header("Accept-Encoding", "gzip, deflate, br")
                        .header("Connection", "keep-alive")
                        .header("Upgrade-Insecure-Requests", "1")
                        .header("Cache-Control", "max-age=0")
                        .header("TE", "Trailers").get();
            }
            else if (random <= 0.8)
            {
                lastPage = Jsoup.connect(url)
                        .header("Host", "www.amazon.com")
                        .userAgent("Mozilla/5.0 (iPhone; CPU iPhone OS 13_5_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.1.1 Mobile/15E148 Safari/604.1")
                        .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                        .header("Accept-Language", "en-US,en;q=0.5")
                        .header("Accept-Encoding", "gzip, deflate, br")
                        .header("Connection", "keep-alive")
                        .header("Upgrade-Insecure-Requests", "1")
                        .header("Cache-Control", "max-age=0")
                        .header("TE", "Trailers").get();
            }
            else if (random <= 1)
            {
                lastPage = Jsoup.connect(url)
                        .header("Host", "www.amazon.com")
                        .userAgent("Mozilla/5.0 (compatible; MSIE 9.0; Windows Phone OS 7.5; Trident/5.0; IEMobile/9.0)")
                        .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                        .header("Accept-Language", "en-US,en;q=0.5")
                        .header("Accept-Encoding", "gzip, deflate, br")
                        .header("Connection", "keep-alive")
                        .header("Upgrade-Insecure-Requests", "1")
                        .header("Cache-Control", "max-age=0")
                        .header("TE", "Trailers").get();
            }
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
                    result = -1;
                }
                else
                {
                    result = 0;
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
