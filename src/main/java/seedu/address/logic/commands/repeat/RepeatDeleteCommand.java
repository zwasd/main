package seedu.address.logic.commands.repeat;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.expenditure.ExpDeleteCommand;
import seedu.address.logic.parser.repeat.RepeatLevelParser;
import seedu.address.model.Model;
import seedu.address.model.expenditure.Expenditure;

import java.util.List;


/**
 * Delete repeat object.
 * TODO: NEED MODIFY
 */
public class RepeatDeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = RepeatLevelParser.COMMAND_WORD + " " + COMMAND_WORD
            + ": Deletes the expenditure identified by the index number used in the displayed expenditure list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_EXPENDITURE_SUCCESS = "Deleted Expenditure: %1$s";


    private final Index targetIndex;

    public RepeatDeleteCommand(Index index) {
        requireNonNull(index);
        this.targetIndex = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        /*
        requireNonNull(model);
        List<Expenditure> lastShownList = model.getFilteredExpenditureList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EXPENDITURE_DISPLAYED_INDEX);
        }

        Expenditure expenditureToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteExpenditure(expenditureToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_EXPENDITURE_SUCCESS, expenditureToDelete));

         */
        return null;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RepeatDeleteCommand // instanceof handles nulls
                && targetIndex.equals(((RepeatDeleteCommand) other).targetIndex)); // state check
    }
}
