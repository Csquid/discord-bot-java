package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserInfo extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        String[] message = event.getMessage().getContentRaw().split(" ");

        if(message.length == 2 &&
                message[0].equalsIgnoreCase("$user") && message[1].equalsIgnoreCase("Avatar")) {
            User user = event.getMember().getUser();
            String avatar = user.getAvatarUrl() + "?size=2048";
            EmbedBuilder avatarEmbed = new EmbedBuilder();
            avatarEmbed.setImage(avatar);
            avatarEmbed.setColor(Color.MAGENTA);

            event.getChannel().sendMessage(avatarEmbed.build()).queue();
        } else if(message.length == 1 && message[0].equalsIgnoreCase("$user")) {
            event.getChannel().sendMessage("To get a user's info, type $user [name]").queue();
        } else if(message.length == 2 && message[0].equalsIgnoreCase("$user")) {
            String userName = message[1];

            try {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                User user = event.getGuild().getMembersByName(userName, true).get(0).getUser();
                String avatar = user.getAvatarUrl();
                EmbedBuilder avatarEmbed = new EmbedBuilder();


                avatarEmbed.setTitle(userName + "'s Info");
                avatarEmbed.setColor(Color.GREEN);
                avatarEmbed.addField("Name", user.getName(), true);
                avatarEmbed.addField("Online Status: ", event.getGuild().getMembersByName(userName, true).get(0).getOnlineStatus().toString(), true);
                avatarEmbed.addField("Avatar: ", "The Avatar is below, " + event.getMember().getAsMention(), true);
                avatarEmbed.setImage(avatar + "?size=1024");
                avatarEmbed.setFooter("Request was made @" + formatter.format(date), event.getGuild().getIconUrl());

                event.getChannel().sendMessage(avatarEmbed.build()).queue();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("not found user");
                event.getChannel().sendMessage("유저를 찾을수 없습니다.").queue();
            }
        }
    }
}
