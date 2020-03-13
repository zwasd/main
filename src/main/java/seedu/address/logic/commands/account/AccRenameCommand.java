package seedu.address.logic.commands.account;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Rename account.
 */
public class AccRenameCommand extends Command {

    public static final String COMMAND_WORD = "rename";

    public static final String MESSAGE_DELETE_EXPENDITURE_SUCCESS = "The name of : %1$s has changed to : %1$s";

    private final String newName;

    public AccRenameCommand(String newName) {
        this.newName = newName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }
}
