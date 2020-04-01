package seedu.address.logic.commands.account;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.account.AccLevelParser;
import seedu.address.model.Model;

/**
 * Delete account.
 */
public class AccDeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_DELETE_ACCOUNT_SUCCESS = "Deleted Account: ";
    public static final String MESSAGE_USAGE = AccLevelParser.COMMAND_WORD + " " + COMMAND_WORD
            + ": deletes the account with the specified name\n"
            + "Parameters: ACCOUNT_NAME (one word containing only alphanumeric characters, less than 26 characters)\n"
            + "Example: " + AccLevelParser.COMMAND_WORD + " " + COMMAND_WORD + " default";

    private final String targetAccountName;

    public AccDeleteCommand(String targetAccountName) {
        this.targetAccountName = targetAccountName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        String newActiveAccountName = model.deleteAccount(this.targetAccountName);
        return new CommandResult(MESSAGE_DELETE_ACCOUNT_SUCCESS + this.targetAccountName,
                newActiveAccountName);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AccDeleteCommand // instanceof handles nulls
                && targetAccountName.equals(((AccDeleteCommand) other).targetAccountName));
    }
}
