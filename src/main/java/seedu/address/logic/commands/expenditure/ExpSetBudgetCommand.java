package seedu.address.logic.commands.expenditure;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.expenditure.Amount;
import seedu.address.model.expenditure.Date;

import java.time.LocalDate;

/**
 * Set budget.
 */
public class ExpSetBudgetCommand extends Command {

    public static final String COMMAND_WORD = "setbudget";
    public static final String MESSAGE_FAIL = "Budget cannot be set";

    private Date date;
    private Amount budget;

    public ExpSetBudgetCommand(Date date, Amount budget){

        this.date = date;
        this.budget = budget;

    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }
}
