package seedu.saveit.logic.parser.account;

import static seedu.saveit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.saveit.logic.commands.account.AccCheckoutCommand;
import seedu.saveit.logic.parser.Parser;
import seedu.saveit.logic.parser.exceptions.ParseException;

/**
 * Parse change account.
 */
public class AccCheckoutCommandParser implements Parser<AccCheckoutCommand> {
    @Override
    public AccCheckoutCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AccCheckoutCommand.MESSAGE_USAGE));
        }
        String[] accountName = trimmedArgs.split("\\s+");
        if (accountName.length != 1) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AccCheckoutCommand.MESSAGE_USAGE));
        }
        return new AccCheckoutCommand(accountName[0]);
    }
}
