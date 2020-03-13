package seedu.address.logic.commands.expenditure;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Set budget.
 */
public class ExpSetBudgetCommand extends Command {

    public static final String COMMAND_WORD = "setbudget";

    public ExpSetBudgetCommand(){

    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }
}
