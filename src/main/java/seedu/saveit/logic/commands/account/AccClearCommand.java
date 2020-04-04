package seedu.saveit.logic.commands.account;

import static java.util.Objects.requireNonNull;

import seedu.saveit.logic.commands.Command;
import seedu.saveit.logic.commands.CommandResult;
import seedu.saveit.logic.parser.account.AccLevelParser;
import seedu.saveit.model.Model;

/**
 * Clears the account.
 */
public class AccClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";

    public static final String MESSAGE_SUCCESS = "This account's data has been cleared!";

    public static final String MESSAGE_USAGE = AccLevelParser.COMMAND_WORD + " " + COMMAND_WORD
            + ": clears all data in this account\nExample: " + AccLevelParser.COMMAND_WORD + " " + COMMAND_WORD;


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.clearActiveAccount();
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || other instanceof AccClearCommand;
    }
}
