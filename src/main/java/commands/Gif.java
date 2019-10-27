package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class Gif extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        String[] message = event.getMessage().getContentRaw().split(" ");

        if(message.length == 1 && message[0].equalsIgnoreCase("#gif")) {
            EmbedBuilder helpEmbed = new EmbedBuilder();
            helpEmbed.setTitle("Gifs");
            helpEmbed.addField("#gif", "???", false);
            helpEmbed.addField("#gif junkrat", "정크렛이 나가신다!", false);
            helpEmbed.addField("#gif bomb", "펑~", false);

            event.getChannel().sendMessage(helpEmbed.build()).queue();


        } else if(message.length >= 2 && message[0].equalsIgnoreCase("#gif")) {
            String findGif = message[1];

            if(findGif.equalsIgnoreCase( "junkrat")) {
                event.getChannel().sendMessage("https://contents.vryjam.com/3YQSiqLN4izK1fZ.gif?size=2048").queue();
            } else if(findGif.equalsIgnoreCase("bomb")) {
                event.getChannel().sendMessage("https://contents.vryjam.com/umziAP0g0lSty2w.gif").queue();
            }
        }
    }
}
