import AccessGuild.AccessGuilds;
import commands.*;
import events.HelloEvent;
import events.Ready;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.SelfUser;

public class Bot {

    public static void main(String args[]) throws Exception{
        JDA jda = new JDABuilder("token-key").build();

        jda.addEventListener(new Ready());
        jda.awaitReady();

        SelfUser su = jda.getSelfUser();

        //Access guilds Init
        AccessGuilds ags = AccessGuilds.createAccessGuildsInst(jda.getGuilds());
        System.out.println(ags.toStringGuild());
        System.out.println(ags.toStringEmoji());
        System.out.println(su.getName());

        jda.addEventListener(new Emoji());
        jda.addEventListener(new Reaction());
        jda.addEventListener(new Gif());
        jda.addEventListener(new Notice());
        jda.addEventListener(new HelloEvent());
        jda.addEventListener(new InviteCommand());
        jda.addEventListener(new UserInfo());
    }


}
