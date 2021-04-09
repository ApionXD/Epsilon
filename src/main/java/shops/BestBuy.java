package shops;

import cards.Card;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class BestBuy extends Shop
{
    final static private String NAME = "Best Buy";
    private Document lastPage;
    public BestBuy(int timeout)
    {
        super(NAME, timeout);
    }
    @Override
    public int checkCurrentStatus(Card cardToCheck)
    {

        int result = -100;
        Element e = lastPage.getElementsContainingText("Add to Cart").first();
        if (e != null)
        {
            result = 1;
        }
        else
        {
            e = lastPage.getElementsContainingText("Sold Out").first();
            if (e != null)
            {
                result = 0;
            }
            else
            {
                result = -1;
            }
        }
        return result;
    }

    @Override
    public double checkPrice(Card cardToCheck)
    {
        Element e = lastPage.select(".priceView-hero-price priceView-customer-price").first();
        return Double.parseDouble(e.text().substring(1));
    }

    @Override
    public void checkPage(Card cardToCheck) throws IOException {
        String url = cardToCheck.getLink();
        double random = Math.random();
        if (random <= 0.2)
        {
            lastPage = Jsoup.connect(url)
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
                    .userAgent("Mozilla/5.0 (compatible; MSIE 9.0; Windows Phone OS 7.5; Trident/5.0; IEMobile/9.0)")
                    .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                    .header("Accept-Language", "en-US,en;q=0.5")
                    .header("Accept-Encoding", "gzip, deflate, br")
                    .header("Connection", "keep-alive")
                    .header("Upgrade-Insecure-Requests", "1")
                    .header("Cache-Control", "max-age=0")
                    .header("TE", "Trailers").get();
        }
    }
}
