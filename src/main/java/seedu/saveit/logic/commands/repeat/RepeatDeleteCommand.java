package seedu.saveit.logic.commands.repeat;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.saveit.commons.core.Messages;
import seedu.saveit.commons.core.index.Index;
import seedu.saveit.logic.commands.Command;
import seedu.saveit.logic.commands.CommandResult;
import seedu.saveit.logic.commands.exceptions.CommandException;
import seedu.saveit.logic.parser.repeat.RepeatLevelParser;
import seedu.saveit.model.Model;
import seedu.saveit.model.MonthlySpendingCalculator;
import seedu.saveit.model.expenditure.BaseExp;
import seedu.saveit.model.expenditure.Repeat;

/**
 * Delete repeat object.
 * TODO: NEED MODIFY
 */
public class RepeatDeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = RepeatLevelParser.COMMAND_WORD + " " + COMMAND_WORD
            + ": Deletes the repeat identified by the index number used in the displayed list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + RepeatLevelParser.COMMAND_WORD + " " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_REPEAT_SUCCESS = "Deleted Repeat: %1$s";


    private final Index targetIndex;

    public RepeatDeleteCommand(Index index) {
        requireNonNull(index);
        this.targetIndex = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<BaseExp> lastShownList = model.getFilteredBaseExpList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EXPENDITURE_DISPLAYED_INDEX);
        }

        BaseExp baseExp = lastShownList.get(targetIndex.getZeroBased());
        if (!(baseExp instanceof Repeat)) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_TYPE_AT_INDEX,
                    Repeat.class.getSimpleName()));
        }
        Repeat repeatToDelete = (Repeat) baseExp;
        model.deleteRepeat(repeatToDelete);
        MonthlySpendingCalculator monthlyCalculator = model.getMonthlySpending();
        return new CommandResult(String.format(MESSAGE_DELETE_REPEAT_SUCCESS, repeatToDelete),
                monthlyCalculator.getBudget(), monthlyCalculator.getTotalSpending());

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RepeatDeleteCommand // instanceof handles nulls
                && targetIndex.equals(((RepeatDeleteCommand) other).targetIndex)); // state check
    }
}
