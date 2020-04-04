package seedu.address.logic.commands.general;

import java.time.LocalDate;
import java.time.YearMonth;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;

import seedu.address.model.Model;
import seedu.address.model.MonthlySpendingCalculator;

import static java.util.Objects.requireNonNull;


/**
 * Switch to a date.
 */
public class GoCommand extends Command {

    public static final String COMMAND_WORD = "go";

    public static final String MESSAGE_SUCCESS = "We are at : %1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Go to a specific date\n"
            + "the specified date format YYYY-MM-DD.\n"
            + "Example: " + COMMAND_WORD + " 2001-09-11";


    private final LocalDate toDate;
    private final boolean fromUi;

    public GoCommand(LocalDate toDate, boolean fromUi) {
        this.toDate = toDate;
        this.fromUi = fromUi;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public boolean getFromUi() {
        return fromUi;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateActiveDate(toDate);
        YearMonth target = YearMonth.of(toDate.getYear(), toDate.getMonthValue());
        MonthlySpendingCalculator monthlyCalculator = model.getMonthlySpending(target);
        if (fromUi) {
            return new CommandResult(String.format(MESSAGE_SUCCESS, toDate),
                    monthlyCalculator.getBudget(), monthlyCalculator.getTotalSpending());

        } else {
            return new CommandResult(String.format(MESSAGE_SUCCESS, toDate), toDate,
                    monthlyCalculator.getBudget(), monthlyCalculator.getTotalSpending());

        }
    }

    @Override
    public boolean equals(Object obj) {
        requireNonNull(obj);
        if (obj == this) {
            return true;
        } else {
            return ((GoCommand) obj).getToDate().equals(this.toDate)
                    && (((GoCommand) obj).getFromUi() == this.fromUi);
        }
    }
}
