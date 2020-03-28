package seedu.address.logic.commands.expenditure;

import java.time.YearMonth;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Set budget.
 */
public class ExpSetBudgetCommand extends Command {

    public static final String COMMAND_WORD = "setbudget";
    public static final String MESSAGE_FAIL = "Budget cannot be set";

    private YearMonth yearMonth;
    private double budgetAmount;

    public ExpSetBudgetCommand(YearMonth yearMonth, double budgetAmount) {
        this.yearMonth = yearMonth;
        this.budgetAmount = budgetAmount;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }
}
