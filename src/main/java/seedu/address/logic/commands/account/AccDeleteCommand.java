package seedu.address.logic.commands.account;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Delete account.
 */
public class AccDeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String NAME_CONTAIN_SPACE = "The account name should not contain space";

    public static final String NAME_TOO_LONG = "The account name should be less than 25 characters";

    public static final String MESSAGE_DELETE_EXPENDITURE_SUCCESS = "Deleted Account: %1$s";

    private final String targetAccountName;

    public AccDeleteCommand(String targetAccountName) {
        this.targetAccountName = targetAccountName;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }
}
