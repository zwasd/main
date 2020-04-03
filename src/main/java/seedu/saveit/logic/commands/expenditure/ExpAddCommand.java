package seedu.saveit.logic.commands.expenditure;

import static java.util.Objects.requireNonNull;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_INFO;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_TAG;

import java.time.format.DateTimeFormatter;

import seedu.saveit.logic.commands.Command;
import seedu.saveit.logic.commands.CommandResult;
import seedu.saveit.logic.commands.exceptions.CommandException;
import seedu.saveit.logic.parser.expenditure.ExpLevelParser;
import seedu.saveit.model.Model;
import seedu.saveit.model.MonthlySpendingCalculator;
import seedu.saveit.model.expenditure.Date;
import seedu.saveit.model.expenditure.Expenditure;

/**
 * Adds a expenditure to the address book.
 */
public class ExpAddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = ExpLevelParser.COMMAND_WORD + " " + COMMAND_WORD
            + ": Adds a expenditure to the $AVE IT. "
            + "\n" + "Parameters: "
            + PREFIX_INFO + "INFO "
            + PREFIX_AMOUNT + "AMOUNT "
            + "[" + PREFIX_DATE + "DATE] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + ExpLevelParser.COMMAND_WORD + " " + COMMAND_WORD + " "
            + PREFIX_INFO + "Chicken rice "
            + PREFIX_AMOUNT + "3.5 "
            + PREFIX_DATE + "2019-09-11 "
            + PREFIX_TAG + "friends";

    public static final String MESSAGE_SUCCESS = "New expenditure added: %1$s";
    public static final String MESSAGE_DUPLICATE_EXPENDITURE = "This expenditure already exists in $AVE IT.";

    private final Expenditure toAdd;
    private final boolean getActiveDate;

    /**
     * Creates an ExpAddCommand to add the specified {@code Expenditure}
     */
    public ExpAddCommand(Expenditure expenditure) {
        this(expenditure, false);
    }

    /**
     * Creates an ExpAddCommand to add the specified {@code Expenditure}
     */
    public ExpAddCommand(Expenditure expenditure, boolean getActiveDate) {
        requireNonNull(expenditure);
        toAdd = expenditure;
        this.getActiveDate = getActiveDate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Expenditure added = !getActiveDate ? toAdd
            : new Expenditure(toAdd.getInfo(), toAdd.getAmount(),
                                new Date(model.getActiveDate().format(DateTimeFormatter.ISO_DATE)), toAdd.getTag());
        model.addExpenditure(added);
        MonthlySpendingCalculator monthlyCalculator = model.getMonthlySpending();
        return new CommandResult(String.format(MESSAGE_SUCCESS, added), monthlyCalculator.getBudget(),
                monthlyCalculator.getTotalSpending());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExpAddCommand // instanceof handles nulls
                && getActiveDate == ((ExpAddCommand) other).getActiveDate
                && toAdd.equals(((ExpAddCommand) other).toAdd)); // same fields
    }
}
