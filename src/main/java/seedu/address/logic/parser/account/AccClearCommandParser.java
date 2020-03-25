package seedu.address.logic.parser.account;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.account.AccClearCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parse clearing of account.
 */
public class AccClearCommandParser implements Parser<AccClearCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AccClearCommand
     * and returns an AccClearCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public AccClearCommand parse(String userInput) throws ParseException {
        String trimmedArgs = userInput.trim();
        if (trimmedArgs.length() > 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AccClearCommand.MESSAGE_FAILURE));
        }
        return new AccClearCommand();
    }
}
