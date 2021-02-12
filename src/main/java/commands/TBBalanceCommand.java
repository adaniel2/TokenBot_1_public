package commands;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * This is the balance command. It will return the number of token(s) a user currently has
 * and associated level.
 *
 * Note: Token name is currently hardcoded.
 *
 * @author      Daniel Almeida
 * @version     11/2/20
 */
public class TBBalanceCommand extends ListenerAdapter {
    /**
     * Watch guild messages for the "balance" command and reply with user's token balance.
     *
     * @param e     guild message event
     */
    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent e) {
        // grab message
        String message = e.getMessage().getContentRaw();

        // if it's the command
        if (message.equals("]balance")) {
            // count number of tokens
            int nTokens = 0;

            for (int i = 0; i < Objects.requireNonNull(e.getMember()).getRoles().size(); i++) {
                if (e.getMember().getRoles().get(i).getName().contains("PBToken")) {
                    // increase count
                    nTokens++;
                }
            }

            // reply
            e.getChannel().sendMessage("<@" + e.getAuthor().getId() + ">,"
                    + " your token balance is: " + nTokens ).queue();
        }

    }

}
