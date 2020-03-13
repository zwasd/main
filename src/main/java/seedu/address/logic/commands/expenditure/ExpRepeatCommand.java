package seedu.address.logic.commands.expenditure;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Repeat expenditure.
 */
public class ExpRepeatCommand extends Command {

    public static final String COMMAND_WORD = "repeat";

    public ExpRepeatCommand(){

    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }
}
