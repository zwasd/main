package seedu.saveit.logic.parser.account;

import static seedu.saveit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.saveit.logic.commands.account.AccAddCommand.NAME_CONTAINS_INVALID_CHAR;
import static seedu.saveit.logic.commands.account.AccAddCommand.NAME_TOO_LONG;

import seedu.saveit.commons.util.StringUtil;
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
        String oldName;
        String newName;
        if (allName.length == 2) {
            oldName = allName[0];
            newName = allName[1];
        } else if (allName.length == 1) {
            oldName = null;
            newName = allName[0];
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    "The number of parameters is incorrect\n" + AccRenameCommand.MESSAGE_USAGE));
        }


        if ((oldName != null && oldName.length() >= 26) || newName.length() >= 26) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    NAME_TOO_LONG + "\n" + AccRenameCommand.MESSAGE_USAGE));
        }

        if (!StringUtil.isAlphanumeric(oldName) || !StringUtil.isAlphanumeric(newName)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    NAME_CONTAINS_INVALID_CHAR + "\n" + AccRenameCommand.MESSAGE_USAGE));
        }

        return new AccRenameCommand(oldName, newName);

    }
}
