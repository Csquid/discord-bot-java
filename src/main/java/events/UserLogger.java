package events;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class UserLogger extends ListenerAdapter {
    private final long userId;

    public UserLogger(User user) {
        this.userId = user.getIdLong();
    }

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        User author = event.getAuthor();
        Message message = event.getMessage();
        if(author.getIdLong() == userId) {
            System.out.println(author.getAsTag() + ": " + message.getContentDisplay());
        }
    }

    @Override
    public void onGuildJoin(@Nonnull GuildJoinEvent event) {
        JDA api = event.getJDA();
        User user = api.getUserById(userId);
        user.openPrivateChannel().queue((channel) -> {
            channel.sendMessageFormat("I have joined a new guild: **%s**", event.getGuild().getName()).queue();
        });
    }
}
