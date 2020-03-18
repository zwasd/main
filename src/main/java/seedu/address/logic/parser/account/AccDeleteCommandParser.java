package seedu.address.logic.parser.account;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.account.AccDeleteCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parse delete account.
 */
public class AccDeleteCommandParser implements Parser<AccDeleteCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AccDeleteCommand
     * and returns an AccDeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public AccDeleteCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.contains(" ")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AccDeleteCommand.NAME_CONTAIN_SPACE));
        }
        if (trimmedArgs.length() >= 26) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AccDeleteCommand.NAME_TOO_LONG));
        }
        return new AccDeleteCommand(trimmedArgs);
    }
}
