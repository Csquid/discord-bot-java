package commands;

import AccessGuild.AccessGuilds;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Emoji extends ListenerAdapter {

    AccessGuilds ag = AccessGuilds.createAccessGuildsInst();
    HashMap<String, HashMap<String, Emote>> hashEmojisGuild = ag.getHashEmojis();

    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        String[] message = event.getMessage().getContentRaw().split(" ");

        if (message[0].equalsIgnoreCase("$emoji") &&
                message[1].equalsIgnoreCase("all")) {
            EmbedBuilder emojiEmbed = new EmbedBuilder();
            emojiEmbed.setAuthor("java_codeMonkey");
            emojiEmbed.setDescription("have Emotes");
            emojiEmbed.setColor(Color.YELLOW);
            for (String fKey : hashEmojisGuild.keySet()) {
                System.out.println("fkey: " + fKey);
                HashMap<String, Emote> hashEmojis = hashEmojisGuild.get(fKey);
                for (String sKey : hashEmojis.keySet()) {
                    Emote emoji = hashEmojis.get(sKey);
                    emojiEmbed.addField("Channel: " + fKey, "Emoji Name: " +
                            emoji.getName() + "  Emoji: " + emoji.getAsMention(), false);
                }
            }

            event.getChannel().sendMessage(emojiEmbed.build()).queue();

        } else if (message[0].equalsIgnoreCase("$emoji") && message.length == 3) {
            String guildName = message[1];
            String emojiName = message[2];

            event.getChannel().sendMessage(
                    hashEmojisGuild.get(guildName).get(emojiName).getAsMention()
            ).queue();
        } else if ( (message[0].equalsIgnoreCase("$emoji") && message[1].equalsIgnoreCase("command") ) ||
                (message[0].equalsIgnoreCase("$emoji") && message[1].equalsIgnoreCase("help")) ) {
            EmbedBuilder helpEmbed = new EmbedBuilder();

            helpEmbed.setTitle("emoji commands");
            helpEmbed.setDescription("How to use emoji commands manual");
            helpEmbed.setColor(Color.lightGray);
            helpEmbed.addField("$emoji all", "This bot shows what it has.", false);
            helpEmbed.addField("$emoji [Guild Name] [Emoji Name]", "Just Show Emoji, First args: guild name, Second args: emoji name", false);

            event.getChannel().sendMessage(helpEmbed.build()).queue();
        }
    }
}
