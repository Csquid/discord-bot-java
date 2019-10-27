package events;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class HelloEvent extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] messageSent = event.getMessage().getContentRaw().split(" ");
        String name = event.getMember().getUser().getName();

        char prefix = '/';

        if(messageSent[0].equalsIgnoreCase("hello")) {
            event.getChannel().sendMessage("Hi!").queue();
        } else if(messageSent[0].equalsIgnoreCase("code")) {
            event.getChannel().sendMessage("Monkey!").queue();
        } else if(messageSent[0].equalsIgnoreCase("hi")) {
            if(!event.getMember().getUser().isBot())
                event.getChannel().sendMessage("hi").queue();
        }

    }
}
