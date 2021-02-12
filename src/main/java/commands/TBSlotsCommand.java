package commands;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

/**
 * This is the slots command. It will return the target channel's available slots.
 *
 * Note: Currently, the channel's limit is hardcoded in here.
 *
 * @author      Daniel Almeida
 * @version     11/2/20
 */
public class TBSlotsCommand extends ListenerAdapter {
    // variables or constants
    private final String chId;

    /**
     * Constructor initializes target channel ID.
     *
     * @param ch        target channel ID
     */
    public TBSlotsCommand(String ch) {
        chId = ch;
    }

    /**
     * Listen and reply with number of available slots in target channel.
     *
     * @param e     guild message event
     */
    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent e) {
        // grab message
        String message = e.getMessage().getContentRaw();

        // if it is the command, calculate and reply with the number of slots available
        if (message.equals("]slots")) {
            // grab channel
            MessageChannel channel = e.getGuild().getTextChannelById(chId);

            // count number of messages
            assert channel != null;
            int numMsg = channel.getHistoryFromBeginning(100).complete().size();

            // reply
            e.getChannel().sendMessage("There are " + (10 - numMsg) + " submission slots open!").queue();
        }

    }

}
