package events;

import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class Ready extends ListenerAdapter {
    @Override
    public void onReady(@Nonnull ReadyEvent event) {
        System.out.println("I'm Ready!");
        System.out.println("Logged in as " + event.getJDA().getSelfUser().getAsTag() + "!");
    }
}
