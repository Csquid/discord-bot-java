package commands;

import AccessGuild.AccessGuilds;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.guild.GuildAvailableEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.util.HashMap;

public class Reaction extends ListenerAdapter {
    AccessGuilds accguild = AccessGuilds.createAccessGuildsInst();

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        String[] message = event.getMessage().getContentRaw().split(" ");
        HashMap<String, HashMap<String, Emote>> emojis = accguild.getHashEmojis();

        /*
        if(event.getMessage().getMember().getUser().getName().equalsIgnoreCase("Kwabang")) {
            event.getChannel().addReactionById(event.getMessage().getId(),
                    emojis.get("codeMonkey_DiscordBot").get("partyblob")).queue();
        }
         */
        if(message[0].equalsIgnoreCase( "이런!")) {
            event.getChannel().addReactionById(event.getMessage().getId(),
                    emojis.get("codeMonkey_DiscordBot").get("partyblob")).queue();
        }
    }
}
