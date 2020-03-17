package seedu.address.logic.commands.expenditure;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import java.time.LocalDate;

/**
 * Set budget.
 */
public class ExpSetBudgetCommand extends Command {

    public static final String COMMAND_WORD = "setbudget";
    public static final String MESSAGE_FAIL = "Budget cannot be set";

    private LocalDate date;
    private double budget;

    public ExpSetBudgetCommand(LocalDate date, double budget){

        this.date = date;
        this.budget = budget;

    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }
}
