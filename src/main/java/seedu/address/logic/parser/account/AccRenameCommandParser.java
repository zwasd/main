package seedu.address.logic.parser.account;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.account.AccRenameCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parse rename account.
 */
public class AccRenameCommandParser implements Parser<AccRenameCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AccRenameCommand
     * and returns an AccRenameCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public AccRenameCommand parse(String userInput) throws ParseException {
        String trimmedArgs = userInput.trim();
        String [] allName = trimmedArgs.split(" ");

        if (allName.length != 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AccRenameCommand.INVALID_NAME_INPUT));
        }

        String oldName = allName[0];
        String newName = allName[1];

        if (oldName.length() >= 26 || newName.length() >= 26) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AccRenameCommand.INVALID_NAME_INPUT));
        }

        return new AccRenameCommand(oldName, newName);

    }
}
