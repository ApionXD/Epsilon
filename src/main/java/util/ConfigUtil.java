package util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
@Getter
@Slf4j
public class ConfigUtil {
    private int amazonTimeout;
    private boolean amazonEnabled;
    private int bestBuyTimeout;
    private boolean bestBuyEnabled;
    private String bestBuyKey;
    private boolean bestBuyUseKey;

    private boolean isDiscordEnabled;
    private String discordBotToken;
    private String discordChannelName;
    private String discordBotName;

    public ConfigUtil() {
        initDefaults();
    }
    private void initDefaults() {
        amazonTimeout = 30000;
        amazonEnabled = false;
        bestBuyTimeout = 30000;
        bestBuyEnabled = false;
        bestBuyUseKey = false;
        isDiscordEnabled = false;
        discordBotToken = "N/A";
        discordChannelName = "N/A";
        discordBotName = "N/A";
    }
}
