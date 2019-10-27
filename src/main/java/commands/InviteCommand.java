package commands;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class InviteCommand extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        JDA jda;
        String[] message = event.getMessage().getContentRaw().split(" ");

        if(message[0].equalsIgnoreCase("$invite") && message.length == 1) {
            event.getChannel().sendMessage("To use $invite do: $invite create").queue();
        } else if(message[0].equalsIgnoreCase("$invite") && message[1].equalsIgnoreCase("create")) {
            event.getChannel().sendMessage("Hey " + event.getAuthor().getName() + "! You want to invite someone? Cool!").queue();
            event.getChannel().sendMessage("Give them this invite link: " + event.getChannel().createInvite().setMaxAge(3600).complete().getUrl()).queue();
        }

        if(message[0].equalsIgnoreCase("!ping")){
            event.getChannel().sendMessage("My Ping: " + event.getMessage().getJDA().getMaxReconnectDelay() + "ms").queue();
        }
    }
}
