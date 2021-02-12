package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * This is the help command, it... helps.
 *
 * Provides some text to help caller get started with the bot.
 *
 * @author      Daniel Almeida
 * @version     11/4/20
 */
public class TBHelpCommand extends ListenerAdapter {
    // variables & constants
    String infoChId;

    /**
     * Constructor for the server's info channel ID.
     *
     * @param ch        info channel ID
     */
    public TBHelpCommand(String ch) { infoChId = ch; }

    /**
     * Reply with a help message providing caller with how-to information regarding
     * the bot's purpose and available functions.
     *
     * @param e     guild message event
     */
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent e) {
        // grab message
        String message = e.getMessage().getContentRaw();

        if (message.equals("]help")) {
            // embed builder
            EmbedBuilder eb = new EmbedBuilder();

            eb.setTitle("TokenBot");
            eb.setColor(new Color(255,178,113));

            String msg = "\nHi, i'm TokenBot and I was built to help manage submissions on this server " +
                    "^_^\n\n" +
                        "To learn how to submit, check out the <#" + infoChId + "> channel for full instructions.\n\n" +
                            "I also provide some commands `]commands` that you may use, check them out!";

            eb.addField("`Version 1.0`", msg, true);

            // reply
            e.getChannel().sendMessage(eb.build()).queue();
        }

    }

}
