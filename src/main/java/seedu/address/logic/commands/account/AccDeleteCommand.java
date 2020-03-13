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

    public static final String MESSAGE_DELETE_EXPENDITURE_SUCCESS = "Deleted Account: %1$s";

    private final Index targetIndex;

    public AccDeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }
}
