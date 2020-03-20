package seedu.address.logic.commands.repeat;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.repeat.RepeatLevelParser;
import seedu.address.model.Model;


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


    private final String deleteType;

    public RepeatDeleteCommand(String type) {
        requireNonNull(type);
        this.deleteType = type;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }
}
