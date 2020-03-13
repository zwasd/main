package seedu.address.logic.commands.account;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Change to another account.
 */
public class AccCheckoutCommand extends Command {

    public static final String COMMAND_WORD = "checkout";

    public static final String MESSAGE_SUCCESS = "You are now at account: ";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }
}
