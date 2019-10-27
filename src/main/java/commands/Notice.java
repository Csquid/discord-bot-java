package commands;

import AccessGuild.AccessGuilds;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Notice extends ListenerAdapter {
    private HashMap<String, HashMap<String, Emote>> hashEmojis;
    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        AccessGuilds ag = AccessGuilds.createAccessGuildsInst();
        hashEmojis = ag.getHashEmojis();

        String[] message = event.getMessage().getContentRaw().split(" ");

        if(message[0].equalsIgnoreCase("#notice")) {
            if(message[1].equalsIgnoreCase("join")) {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();

                EmbedBuilder noticeEmbed = new EmbedBuilder();
                EmbedBuilder userEmbed = new EmbedBuilder();
                String em = this.hashEmojis.get("codeMonkey_DiscordBot").get("partyblob").getAsMention();
                String ems = "";

                for(int i = 0; i < 16; i ++) {
                    ems += em;
                }
                User user = event.getMember().getUser();
                userEmbed.setTitle(user.getAsTag() + "'s 님께서 들어오셧습니다!");
                userEmbed.setColor(Color.CYAN);
                userEmbed.setImage(user.getAvatarUrl() + "?size=2048");
                userEmbed.setFooter("Request was made @" + formatter.format(date), event.getGuild().getIconUrl());

                noticeEmbed.setColor(Color.yellow);
                noticeEmbed.setAuthor("Welcome! " + event.getMember().getUser().getName() + ".");
                noticeEmbed.setDescription(ems);
                noticeEmbed.addField("JAVA, C++, C# 프로그래밍, 웹개발 스터디 부설방에 오신걸 환영합니다.", "개발 관련한 대화는 어떤 주제든 환영이고, 질문도 자유롭게 하시면 됩니다.", false);
                noticeEmbed.setThumbnail(event.getGuild().getIconUrl() + "?size=1024");
                noticeEmbed.addField("1. 공식카페", "https://cafe.naver.com/hellosharp", true);
                noticeEmbed.addField("2. 메인 오픈채팅방", "https://open.kakao.com/o/gGp80zJ", false);
                noticeEmbed.addField("3. 슬랙 채팅방", "https://cafe.naver.com/hellosharp", false);
                noticeEmbed.addField("4. 디스코드 채팅방", "https://discord.gg/nzpKp3w", false);
                noticeEmbed.addField("5. 정모방", "https://open.kakao.com/o/ghK6Lqrb", false);
                event.getChannel().sendMessage(userEmbed.build()).queue();
                event.getChannel().sendMessage(noticeEmbed.build()).queue();
            }
        }
    }
}
