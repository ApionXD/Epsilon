import cards.Card;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import shops.Amazon;
import shops.BestBuy;
import shops.Shop;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;


public class Epsilon
{
    public static final String CONF_FILE = "conf.json";
    public static final String LINK_FILE = "cards.json";
    public static final String DEVEL_PATH = "src/main/resources/";
    public static ArrayList<Card> cards;

    public static int amazonTimeout;
    public static boolean amazonEnabled;
    public static int bestBuyTimeout;
    public static boolean bestBuyEnabled;
    public static String bestBuyKey;
    public static boolean bestBuyUseKey;
    public static int outputVerbosity;

    public static Shop amazon;
    public static Shop bestBuy;

    public static void main(String[] args)
    {
        readConfig();
        if (amazonEnabled)
        {
            amazon = new Amazon(amazonTimeout);
        }
        if (bestBuyEnabled)
        {
            bestBuy= new BestBuy(bestBuyTimeout, bestBuyKey);
        }
        cards = getCardList();
        for (int i = 0; i < cards.size(); i++)
        {
            Thread t = new Thread(cards.get(i));
            t.start();
            if (outputVerbosity == 2)
            {
                System.out.println("Thread " + i + " started!");
            }
            try
            {
                Thread.sleep(500);
            }
            catch (InterruptedException e)
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
            Path current = Paths.get(LINK_FILE);
            String s = current.toAbsolutePath().toString();
            reader = new JsonReader(new FileReader(s));
        }
        catch (Exception e)
        {
            try
            {
                System.out.println("Link file not found, checking to see if you're in a development environment");
                reader = new JsonReader(new FileReader(DEVEL_PATH + LINK_FILE));
            }
            catch (Exception f)
            {
                System.out.println("Can't find config file, make sure that conf.json is in your current directory!");
            }
        }
        JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();

        if (amazonEnabled)
        {
            JsonArray amazonObject = json.getAsJsonArray("amazon");
            for (int i = 0; i < amazonObject.size(); i++)
            {
                JsonObject o = amazonObject.get(i).getAsJsonObject();
                Card c = new Card(o.get("name").getAsString(), o.get("link").getAsString(), amazon, outputVerbosity);
                result.add(c);
            }
        }
        if (bestBuyEnabled)
        {
            JsonArray bestBuyObject = json.getAsJsonArray("best_buy");
            for (int i = 0; i < bestBuyObject.size(); i++)
            {
                JsonObject o = bestBuyObject.get(i).getAsJsonObject();
                if (o.has("sku"))
                {
                    Card c = new Card(o.get("name").getAsString(), "https://api.bestbuy.com/v1/products/" + o.get("sku").getAsString() + ".json?show=sku,name,salePrice,orderable&apiKey=" , bestBuy, outputVerbosity);
                    result.add(c);
                }
                else if (o.has("link"))
                {
                    System.out.println("Epsilon doesn't support BestBuy links yet! Please use the SKU of the product instead.");
                }

            }
        }


        return result;
    }
    public static void readConfig()
    {
        JsonReader reader = null;

        try
        {
            Path current = Paths.get(CONF_FILE);
            String s = current.toAbsolutePath().toString();
            reader = new JsonReader(new FileReader(s));
        }
        catch (Exception e)
        {
            try
            {
                System.out.println("Config file not found, checking to see if you're in a development environment");
                reader = new JsonReader(new FileReader(DEVEL_PATH + CONF_FILE));
            }
            catch (Exception f)
            {
                System.out.println("Can't find config file, make sure that conf.json is in your current directory!");
            }
        }
        JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
        outputVerbosity = json.get("OUTPUT_VERBOSITY").getAsInt();
        if (outputVerbosity < 0 || outputVerbosity > 2)
        {
            outputVerbosity = 0;
            System.out.println("Invalid verbosity option in config, defaulting to 0");

        }
        amazonTimeout = json.get("AMAZON_TIMEOUT").getAsInt();
        amazonEnabled = json.get("AMAZON_ENABLED").getAsBoolean();
        bestBuyTimeout = json.get("BEST_BUY_TIMEOUT").getAsInt();
        bestBuyEnabled = json.get("BEST_BUY_ENABLED").getAsBoolean();
        bestBuyUseKey = json.get("BEST_BUY_USE_KEY").getAsBoolean();
        if (bestBuyUseKey)
        {
            bestBuyKey = json.get("BEST_BUY_KEY").getAsString();
        }
        else
        {
            bestBuyEnabled = false;
        }

    }
    public static void parseAmazonLinks()
    {
        JsonReader reader = null;
        try
        {
            reader = new JsonReader(new FileReader("links.json"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
        JsonArray array = json.get("links").getAsJsonArray();
        JsonArray outputArray = new JsonArray();
        for (int i = 0; i < array.size(); i++)
        {
            JsonObject o = array.get(i).getAsJsonObject();
            JsonObject output = new JsonObject();
            if (o.get("series").getAsString().equals("3060") || o.get("series").getAsString().equals("3060ti") || o.get("series").getAsString().equals("3070") || o.get("series").getAsString().equals("3080") || o.get("series").getAsString().equals("3090"))
            {
                String name = o.get("brand").getAsString().toUpperCase() + " RTX " + o.get("series").getAsString() + " " + o.get("model").getAsString();
                output.add("name", new JsonPrimitive(name));
                String link = o.get("url").getAsString();
                output.add("link", new JsonPrimitive(link));
                outputArray.add(output);
            }
        }
        System.out.println(outputArray.isJsonNull());
        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(outputArray));
    }
    public static void parseBestBuyLinks()
    {
        JsonReader reader = null;
        try
        {
            reader = new JsonReader(new FileReader(DEVEL_PATH + "links.json"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
        JsonArray array = json.get("links").getAsJsonArray();
        JsonArray outputArray = new JsonArray();
        for (int i = 0; i < array.size(); i++)
        {
            JsonObject o = array.get(i).getAsJsonObject();
            JsonObject output = new JsonObject();
            if (o.get("series").getAsString().equals("3060") || o.get("series").getAsString().equals("3060ti") || o.get("series").getAsString().equals("3070") || o.get("series").getAsString().equals("3080") || o.get("series").getAsString().equals("3090"))
            {
                String name = o.get("brand").getAsString().toUpperCase() + " RTX " + o.get("series").getAsString() + " " + o.get("model").getAsString();
                output.add("name", new JsonPrimitive(name));
                String link = o.get("url").getAsString().substring(32, 39);
                output.add("sku", new JsonPrimitive(link));
                outputArray.add(output);
            }
        }
        System.out.println(outputArray.isJsonNull());
        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(outputArray));
    }
}
