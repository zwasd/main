package seedu.saveit.logic.commands.expenditure;

import static java.util.Objects.requireNonNull;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_YEARMONTH;

import java.time.YearMonth;

import seedu.saveit.logic.commands.Command;
import seedu.saveit.logic.commands.CommandResult;
import seedu.saveit.logic.commands.exceptions.CommandException;
import seedu.saveit.logic.parser.expenditure.ExpLevelParser;
import seedu.saveit.model.Model;
import seedu.saveit.model.MonthlySpendingCalculator;
import seedu.saveit.model.budget.Budget;
import seedu.saveit.model.expenditure.Amount;

/**
 * Set budget.
 */
public class ExpSetBudgetCommand extends Command {

    public static final String COMMAND_WORD = "setbudget";

    public static final String MESSAGE_SUCCESS = "Budget for %s set to $%.2f.";
    public static final String MESSAGE_USAGE = ExpLevelParser.COMMAND_WORD + " " + COMMAND_WORD
            + ": Sets the budget for a certain month. "
            + "\n" + "Parameters: "
            + PREFIX_AMOUNT + "AMOUNT "
            + "[" + PREFIX_YEARMONTH + "YEARMONTH] "
            + "Example: " + ExpLevelParser.COMMAND_WORD + " " + COMMAND_WORD + " "
            + PREFIX_AMOUNT + "200.0 "
            + PREFIX_YEARMONTH + "2020-04";

    private YearMonth yearMonth;
    private Amount budgetAmount;

    public ExpSetBudgetCommand(YearMonth yearMonth, Amount budgetAmount) {
        requireNonNull(budgetAmount);
        this.yearMonth = yearMonth;
        this.budgetAmount = budgetAmount;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (this.yearMonth == null) {
            this.yearMonth = YearMonth.from(model.getActiveDate());
        }
        model.setBudget(new Budget(yearMonth, budgetAmount));
        MonthlySpendingCalculator monthlyCalculator = model.getMonthlySpending();
        return new CommandResult(String.format(MESSAGE_SUCCESS, yearMonth.toString(), budgetAmount.value),
                monthlyCalculator.getBudget(), monthlyCalculator.getTotalSpending());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExpSetBudgetCommand // instanceof handles nulls
                && yearMonth.equals(((ExpSetBudgetCommand) other).yearMonth)
                && budgetAmount.equals(((ExpSetBudgetCommand) other).budgetAmount));
    }
}
