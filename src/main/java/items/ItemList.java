package items;

import com.google.common.collect.Collections2;
import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import enums.ShopType;
import shops.Shop;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;

public class ItemList {
    private SetMultimap<String, Item> itemMap;
    public ItemList() {
        itemMap = MultimapBuilder.hashKeys().hashSetValues().build();
    }
    public void addStoreToCardList (Path filePath, Shop shop) throws IOException {
        BufferedReader reader = Files.newBufferedReader(filePath);
        JsonArray cardArray = JsonParser.parseReader(reader).getAsJsonArray();
        for (int i = 0; i < cardArray.size(); i++) {
            JsonObject o = cardArray.get(i).getAsJsonObject();
            Item itemToAdd = new Item(o.get("name").getAsString(), o.get("link").getAsString(), shop);
            itemMap.put(shop.getName(), itemToAdd);
        }
    }
    public HashSet<Item> getAllCards() {
        final HashSet<Item> result = Sets.newHashSet();
        result.addAll(itemMap.values());
        return result;
    }
    public HashSet<Item> getCardsByShop(String shopName) {
        return Sets.newHashSet(itemMap.get(shopName));
    }
    public HashSet<Item> getCardsByShop(Shop shop) {
        return Sets.newHashSet(itemMap.get(shop.getName()));
    }
}
