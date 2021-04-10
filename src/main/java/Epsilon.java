import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hooks.DiscordBot;
import items.Item;
import items.ItemList;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import shops.Amazon;
import shops.BestBuy;
import shops.BestBuyAPI;
import shops.Shop;
import util.ConfigUtil;
import util.FileUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;


@Slf4j

public class Epsilon
{
    public static final String SHOP_DIR = "shops/";
    public static final Path CONF_FILE = Paths.get("config/conf.json");
    public static final Path AMAZON_FILE = Paths.get(SHOP_DIR +"amazon.json");
    public static final Path BEST_BUY_FILE = Paths.get(SHOP_DIR,"best-buy.json");

    public static final Gson gson =  new GsonBuilder()
                    .setPrettyPrinting()
                    .create();

    public static ConfigUtil config;
    public static ItemList itemList;
    public static ArrayList<Item> items;

    public static DiscordBot discordBot;


    public static Shop amazon;
    public static Shop bestBuy;


    public static void main(String[] args)
    {
        log.debug("Checking config file");
        checkConfig();
        itemList = new ItemList();
        log.debug("Checking Amazon link file");
        checkAmazon();
        log.debug("Checking Best Buy link file");
        checkBestBuy();
        log.debug("Checking discord integration");
        checkDiscord();
    }
    @SneakyThrows
    public static void checkConfig() {
        if (FileUtil.checkFileExists(CONF_FILE)) {
            BufferedReader reader = Files.newBufferedReader(CONF_FILE);
            config = gson.fromJson(reader, ConfigUtil.class);
        }
        else {
            log.info("Config file not found, generating a new one!");
            config = new ConfigUtil();
            FileUtil.createFile(CONF_FILE);
            BufferedWriter w = Files.newBufferedWriter(CONF_FILE);
            w.append(gson.toJson(config));
            w.close();
        }
        log.debug("Config read finished");
    }
    @SneakyThrows
    public static void checkAmazon() {
        if (config.isAmazonEnabled())
        {
            if (!FileUtil.checkFileExists(AMAZON_FILE)) {
                log.info("Amazon config file not found, generating a new one");
                FileUtil.createFile(AMAZON_FILE);
                FileUtil.genDefaultShopConfig(AMAZON_FILE);
            }
            amazon = new Amazon(config.getAmazonTimeout());
            itemList.addStoreToCardList(AMAZON_FILE, amazon);
            HashSet<Item> items = itemList.getCardsByShop(amazon);
            amazon.setItemList(items);
            amazon.run();
        }
        log.debug("Amazon init finished");
    }
    @SneakyThrows
    public static void checkBestBuy() {
        if (config.isBestBuyEnabled())
        {
            if(config.isBestBuyUseKey()) {
                //bestBuy = new BestBuyAPI(config.getBestBuyTimeout(), config.getBestBuyKey());
                log.warn("Best Buy API is not supported");
            }
            if (!FileUtil.checkFileExists(BEST_BUY_FILE)) {
                log.info("Best Buy config file not found, generating a new one");
                FileUtil.createFile(BEST_BUY_FILE);
                FileUtil.genDefaultShopConfig(BEST_BUY_FILE);
            }
            bestBuy = new BestBuy(config.getBestBuyTimeout());
            itemList.addStoreToCardList(BEST_BUY_FILE, bestBuy);
            bestBuy.setItemList(itemList.getCardsByShop(bestBuy));
            bestBuy.run();
        }
        log.debug("Best Buy init finished");
    }
    public static void checkDiscord() {
        if (config.isDiscordEnabled())
        {
            discordBot = new DiscordBot(config.getDiscordBotToken(), config.getDiscordChannelName(), config.getDiscordBotName());
            log.info("Enabling Discord");
            Shop.enableDiscord(discordBot);
        }
        log.debug("Discord init finished");
    }
}
