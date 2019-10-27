package AccessGuild;

import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class AccessGuilds {
    List<Guild> guilds;
    /*HashMap<String GuildName, ...>*/
    HashMap<String, HashMap<String, Emote>> hashEmoji;
    HashMap<String, Guild> hashGuild;
    HashMap<String, HashMap<String, TextChannel>> hashChannel;

    private static AccessGuilds staticInstance = null;

    public static AccessGuilds createAccessGuildsInst() {
        return staticInstance;
    }

    public static AccessGuilds createAccessGuildsInst(List<Guild> botGuilds) {
        if(staticInstance == null) {
            staticInstance = new AccessGuilds(botGuilds);
        }

        return staticInstance;
    }

    private AccessGuilds(List<Guild> botGuilds) {
        this.guilds = botGuilds;
        Iterator guildsIterator = botGuilds.iterator();
        hashGuild = new HashMap<>();
        hashEmoji = new HashMap<>();
        hashChannel = new HashMap<>();

        while (guildsIterator.hasNext()) {
            Guild elementGuild = (Guild) guildsIterator.next();
            hashGuild.put(elementGuild.getName(), elementGuild);

            HashMap<String, Emote> tempEmoteHashMap = new HashMap<>();
            List<Emote> listEmotes =  elementGuild.getEmotes();

            for (Emote nEmote : listEmotes) {
                tempEmoteHashMap.put(nEmote.getName(), nEmote);
            }

            HashMap<String, TextChannel> tempChannelHashMap = new HashMap<>();
            List<TextChannel> listChannels = elementGuild.getTextChannels();

            for (TextChannel nChannel : listChannels) {
                tempChannelHashMap.put(nChannel.getName(), nChannel);
            }

            hashEmoji.put(elementGuild.getName(), tempEmoteHashMap);
            hashChannel.put(elementGuild.getName(), tempChannelHashMap);
        }
        //ashGuild.get("codeMonkey_DiscordBot").getDefaultChannel().sendMessage((Message) hashEmoji.get("codeMonkey_DiscordBot").get("partyblob"));
        System.out.println(hashChannel);
        Emote em1 = hashEmoji.get("codeMonkey_DiscordBot").get("partyblob");
        Emote em2 = hashEmoji.get("JavaLab-자바랩 (개발 커뮤니티) - Admin").get("logo");
        System.out.println("hashEmoji.get().get().getName: " + hashEmoji.get("codeMonkey_DiscordBot").get("partyblob").getName());

        //System.out.println(hashGuild.get("JavaLab-자바랩 (개발 커뮤니티)").getDefaultChannel().toString());
        //System.out.println(hashGuild.get("JavaLab-자바랩 (개발 커뮤니티) - Admin").getDefaultChannel().toString());
        //hashGuild.get("JavaLab-자바랩 (개발 커뮤니티) - Admin").getDefaultChannel().sendMessage(em2.getAsMention()).queue();
        //hashGuild.get("JavaLab-자바랩 (개발 커뮤니티)").getDefaultChannel().sendMessage(em1.getAsMention()).queue();
        //hashGuild.get("JavaLab-자바랩 (개발 커뮤니티)").getDefaultChannel().sendMessage("Message Test").queue();
        //hashGuild.get("codeMonkey_DiscordBot").getDefaultChannel().sendMessage(em2.getAsMention()).queue();
    }

    public String toStringGuild() {
        return hashGuild.toString();
    }
    public String toStringEmoji() {
        return hashEmoji.toString();
    }
    public HashMap<String, HashMap<String, Emote>> getHashEmojis() {
        return this.hashEmoji;
    }
    public HashMap<String, HashMap<String, TextChannel>> getHashChannels() {
        return this.hashChannel;
    }
}
