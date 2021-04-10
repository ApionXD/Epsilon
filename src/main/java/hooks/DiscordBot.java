package hooks;

import items.Item;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class DiscordBot
{
    private static final String BOT_LINK = "https://github.com/ApionXD/Epsilon";
    private JDA jda;
    private String channelName;
    private String botName;
    public DiscordBot(String apiKey, String channelName, String botName)
    {
        this.channelName = channelName;
        this.botName =  botName;
        try
        {
            jda = JDABuilder.createDefault(apiKey).build();
        }
        catch (LoginException e)
        {
            e.printStackTrace();
        }
    }
    public void sendStockAlert(Item itemToSend, double priceToSend)
    {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setAuthor(botName, BOT_LINK);
        builder.setTitle(itemToSend.getName(), itemToSend.getLink());
        builder.addField("Price:", String.valueOf(priceToSend), false);
        builder.addField("Store: ", itemToSend.getShop().getName(), false);
        jda.getGuilds().get(0).getTextChannelsByName(channelName, true).get(0).sendMessage(builder.build()).queue();
    }

}
