import cards.Card;
import com.google.common.base.Stopwatch;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import shops.Amazon;
import shops.Shop;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static com.google.gson.JsonParser.parseReader;

public class Main
{
    public static final Shop AMAZON = new Amazon();
    public static ArrayList<Card> cards;
    public static GsonBuilder builder = new GsonBuilder();
    public static void main(String[] args)
    {
        Stopwatch timer = Stopwatch.createStarted();
        Shop amazon = new Amazon();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        System.out.println("Beginning test!");
        cards = getCardList();
        System.out.println(gson.toJson(cards));
        while (true)
        {
            for (int i = 0; i < cards.size(); i++)
            {
                Thread t = new Thread(cards.get(i));
                t.start();
                System.out.println("Thread " + i + " started!");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try
            {
                Thread.sleep(10000);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

    }
    public static ArrayList<Card> getCardList()
    {
        ArrayList<Card> result = new ArrayList<Card>();
        JsonReader reader = null;
        try
        {
            reader = new JsonReader(new FileReader("src/main/resources/cards.json"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();

        JsonArray amazon = json.getAsJsonArray("amazon");
        for (int i = 0; i < amazon.size(); i++)
        {
            JsonObject o = amazon.get(i).getAsJsonObject();
            Card c = new Card(o.get("name").getAsString(), o.get("link").getAsString(), AMAZON);
            result.add(c);
        }

        return result;
    }

}
