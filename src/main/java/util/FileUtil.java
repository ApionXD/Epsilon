package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
@Slf4j
public class FileUtil {
    public static Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    @SneakyThrows
    public static void createFile(Path path) {
        if (!Files.exists(path.getParent())) {
            log.info(path.toString() + " doesn't exist! Making it now...");
            Files.createDirectory(path.getParent());
        }
        else {
            Files.createFile(path);
        }
    }
    public static boolean checkFileExists(Path path) {
        return Files.exists(path);
    }
    @SneakyThrows
    public static void genDefaultShopConfig(Path shopFile) {
        JsonArray exampleArray = new JsonArray();
        JsonObject o = new JsonObject();
        o.addProperty("name", "SAMPLE ITEM");
        o.addProperty("link", "https://www.amazon.com/dp/B0002YKBV2");
        o.addProperty("description", "This is an example item so you can see what the format is supposed to be");
        exampleArray.add(o);
        o = new JsonObject();
        o.addProperty("name", "SAMPLE ITEM 2");
        o.addProperty("link", "https://www.amazon.com/dp/B0002YKBV2");
        o.addProperty("description", "This is an example item so you can see what the format is supposed to be. Notice the commas separating the items");
        exampleArray.add(o);
        String defaultShopFile = GSON.toJson(exampleArray);
        BufferedWriter writer = Files.newBufferedWriter(shopFile);
        writer.append(defaultShopFile);
        writer.close();
    }
}
