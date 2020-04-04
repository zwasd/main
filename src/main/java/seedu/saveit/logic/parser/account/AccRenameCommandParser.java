package seedu.saveit.logic.parser.account;

import static seedu.saveit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.saveit.logic.commands.account.AccAddCommand.NAME_TOO_LONG;

import seedu.saveit.logic.commands.account.AccRenameCommand;
import seedu.saveit.logic.parser.Parser;
import seedu.saveit.logic.parser.exceptions.ParseException;


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
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AccRenameCommand.MESSAGE_USAGE));
        }
        String [] allName = trimmedArgs.split("\\s+");

        if (allName.length != 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    "The number of parameters is incorrect\n" + AccRenameCommand.MESSAGE_USAGE));
        }

        String oldName = allName[0];
        String newName = allName[1];

        if (oldName.length() >= 26 || newName.length() >= 26 || oldName.length() == 0 || newName.length() == 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    NAME_TOO_LONG + "\n" + AccRenameCommand.MESSAGE_USAGE));
        }

        return new AccRenameCommand(oldName, newName);

    }
}
