package seedu.address.logic.parser.account;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.account.AccAddCommand;
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
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AccDeleteCommand.MESSAGE_USAGE));
        }
        if (trimmedArgs.contains(" ") || trimmedArgs.length() == 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AccAddCommand.NAME_CONTAIN_SPACE + "\n" + AccDeleteCommand.MESSAGE_USAGE));
        }
        if (trimmedArgs.length() >= 26) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AccAddCommand.NAME_TOO_LONG
                    + "\n" + AccDeleteCommand.MESSAGE_USAGE));
        }
        return new AccDeleteCommand(trimmedArgs);
    }
}
