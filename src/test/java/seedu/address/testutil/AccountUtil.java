package seedu.address.testutil;

import seedu.address.logic.commands.account.AccAddCommand;
import seedu.address.model.Account;

/**
 * A utility class for Account.
 */
public class AccountUtil {

    /**
     * Returns an add command string for adding the {@code account}.
     */
    public static String getAddCommand(Account account) {
        return AccAddCommand.COMMAND_WORD + " " + account.getAccountName();
    }
}
