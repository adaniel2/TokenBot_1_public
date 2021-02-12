import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import commands.*;
import events.CommentWatcher;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

/**
 * Main class for TokenBot (public version). This public version is intended
 * for presentation purposes only. Future iterations will have all
 * sensitive variables passed in via variables defined on Heroku.
 * 
 * @author      Daniel Almeida
 * @version     11/2/20
 */
public class Main {
    public static void main(String[] args) throws LoginException {
        // bot info
        JDABuilder builder = JDABuilder.createDefault(System.getenv(""));

        // to-do: implement differently to allow mod to input these values using commands
        // CommentWatcher class should only work once these values are set "correctly"

        // to-do: tokenLevel | YT link -> a format to help replacement
        // let users pick which token they want to use by specifying level

        // server specific inputs
        String targetChannelID = "";

        String curatorID = "";

        // this token ID array must increase in level (from left to right)
        String[] tokens = new String[] {"", "",
                "", ""};

        String helpChannelID = "";

        // String tokenName = "PBToken";

        // int chLimit = 10;

        // waiter
        EventWaiter waiter = new EventWaiter();
        builder.addEventListeners(waiter);

        // comments
        CommentWatcher comments = new CommentWatcher(curatorID, targetChannelID, 0, true, waiter);

        // commands
        TBBalanceCommand tbBalanceCommand = new TBBalanceCommand();
        TBCommandsCommand tbCommandsCommand = new TBCommandsCommand();
        TBHelpCommand tbHelpCommand = new TBHelpCommand(helpChannelID);
        TBLevelCommand tbLevelCommand = new TBLevelCommand(tokens);
        TBSlotsCommand tbSlotsCommand = new TBSlotsCommand(targetChannelID);

        // add event listeners and build
        builder.addEventListeners(comments);
        builder.addEventListeners(tbBalanceCommand);
        builder.addEventListeners(tbCommandsCommand);
        builder.addEventListeners(tbHelpCommand);
        builder.addEventListeners(tbLevelCommand);
        builder.addEventListeners(tbSlotsCommand);

        builder.build();
    }
}
