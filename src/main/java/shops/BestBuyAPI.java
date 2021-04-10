package shops;

import items.Item;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.*;

public class BestBuyAPI extends Shop
{
    private String apiKey;
    private static final String NAME = "Best Buy";
    private JsonObject jsonFile;
    public BestBuyAPI(int timeout, String apiKey)
    {
        super(NAME, timeout);
        this.apiKey = apiKey;
    }
    @Override
    public int checkCurrentStatus(Item itemToCheck)
    {
        try
        {
            int result = -100;
            if (!jsonFile.has("errorMessage"))
            {
                String status = jsonFile.get("orderable").getAsString();
                if (status.equals("SoldOut"))
                {
                    result = 0;
                }
                if (status.equals("Available"))
                {
                    result = 1;
                }
            }
           else
            {
                System.out.println(jsonFile);
                result = -1;
            }
            return result;
        }
        catch (Exception e)
        {
            System.out.println(jsonFile);
        }
        return -100;
    }

    @Override
    public double checkPrice(Item itemToCheck)
    {
        double result = 0;
        return jsonFile.get("salePrice").getAsDouble();
    }

    @Override
    public void checkPage(Item itemToCheck) throws IOException {
        JsonReader reader = null;
        String url = itemToCheck.getLink() + apiKey;
        OkHttpClient client = new OkHttpClient();
        Request req = new Request.Builder().url(url).build();
        Response response = client.newCall(req).execute();
        jsonFile = JsonParser.parseString(response.body().string()).getAsJsonObject();

    }
}
