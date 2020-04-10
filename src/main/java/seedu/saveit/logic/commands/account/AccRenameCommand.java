package seedu.saveit.logic.commands.account;

import static java.util.Objects.requireNonNull;

import seedu.saveit.logic.commands.Command;
import seedu.saveit.logic.commands.CommandResult;
import seedu.saveit.logic.commands.exceptions.CommandException;
import seedu.saveit.logic.parser.account.AccLevelParser;
import seedu.saveit.model.Model;


/**
 * Rename account.
 */
public class AccRenameCommand extends Command {

    public static final String COMMAND_WORD = "rename";

    public static final String MESSAGE_SUCCESS = "%1$s has been renamed to %2$s";

    public static final String MESSAGE_USAGE = AccLevelParser.COMMAND_WORD + " " + COMMAND_WORD
            + ": renames an account to the specified new name\n"
            + "Parameters: OLD_NAME NEW_NAME "
            + "(each is one word containing only alphanumeric characters, less than 26 characters)\n"
            + "Example: " + AccLevelParser.COMMAND_WORD + " " + COMMAND_WORD + " default school";

    private final String newName;

    private final String oldName;

    public AccRenameCommand(String oldName, String newName) {
        requireNonNull(newName);
        this.newName = newName;
        this.oldName = oldName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String accountName = model.renameAccount(this.oldName, this.newName);
        return new CommandResult(String.format(MESSAGE_SUCCESS, oldName == null
                ? "This account" : "The account " + oldName, newName), accountName);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AccRenameCommand // instanceof handles nulls
                && newName.equals(((AccRenameCommand) other).newName)
                && oldName.equals(((AccRenameCommand) other).oldName));
    }
}
