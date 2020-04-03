package seedu.saveit.logic.parser.account;

import static seedu.saveit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.saveit.logic.commands.account.AccAddCommand;
import seedu.saveit.logic.parser.Parser;
import seedu.saveit.logic.parser.exceptions.ParseException;
import seedu.saveit.model.Account;

/**
 * Parse account add command.
 */
public class AccAddCommandParser implements Parser<AccAddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AccAddCommand
     * and returns an AccAddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AccAddCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AccAddCommand.MESSAGE_USAGE));
        }
        if (trimmedArgs.contains(" ") || trimmedArgs.length() == 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AccAddCommand.NAME_CONTAIN_SPACE + "\n" + AccAddCommand.MESSAGE_USAGE));
        }
        if (trimmedArgs.length() >= 26) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AccAddCommand.NAME_TOO_LONG + "\n" + AccAddCommand.MESSAGE_USAGE));
        }
        Account newAccount = new Account(trimmedArgs);
        return new AccAddCommand(newAccount);
    }

}
