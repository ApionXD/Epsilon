package util;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;

public class StreetParse
{
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
            reader = new JsonReader(new FileReader("changeThis.json"));
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
