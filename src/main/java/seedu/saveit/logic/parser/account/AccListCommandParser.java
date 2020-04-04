package seedu.saveit.logic.parser.account;

import static seedu.saveit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.saveit.logic.commands.account.AccListCommand;
import seedu.saveit.logic.parser.Parser;
import seedu.saveit.logic.parser.exceptions.ParseException;

/**
 * Parse list accounts.
 */
public class AccListCommandParser implements Parser<AccListCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AccListCommand
     * and returns an AccListCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public AccListCommand parse(String userInput) throws ParseException {
        String trimmedArgs = userInput.trim();
        if (trimmedArgs.length() > 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AccListCommand.MESSAGE_USAGE));
        }
        return new AccListCommand();
    }
}
