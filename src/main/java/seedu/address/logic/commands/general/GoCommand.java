package seedu.address.logic.commands.general;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.expenditure.Date;

/**
 * Switch to a date.
 */
public class GoCommand extends Command {

    public static final String COMMAND_WORD = "go";

    public static final String MESSAGE_SUCCESS = "We are at : %1$s";

    private final Date toDate;

    public GoCommand(Date toDate) {
        this.toDate = toDate;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }

}
