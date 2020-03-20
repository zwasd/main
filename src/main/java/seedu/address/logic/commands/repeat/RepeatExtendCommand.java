package seedu.address.logic.commands.repeat;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Extend repeat object's end date.
 */
public class RepeatExtendCommand extends Command {
    public static final String COMMAND_WORD = "extend";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }
}
