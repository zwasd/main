package seedu.address.logic.parser.account;

import seedu.address.logic.commands.account.AccAddCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Account;

/**
 * Parse add account.
 */
public class AccAddCommandParser implements Parser<AccAddCommand> {
    public AccAddCommandParser() {

    }

    @Override
    public AccAddCommand parse(String userInput) throws ParseException {
        return new AccAddCommand(new Account(userInput));
    }
}
