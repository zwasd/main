package seedu.address.logic.parser.general;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.address.logic.commands.general.GoCommand;
import seedu.address.logic.commands.general.HelpCommand;

import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parse switch to a date.
 */
public class GoCommandParser implements Parser<GoCommand> {

    /**
     * Goes to a target date.
     * @param args The date argument to be parsed.
     */
    public GoCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GoCommand.MESSAGE_USAGE));
        }

        try {
            LocalDate targetDate = LocalDate.parse(trimmedArgs, DateTimeFormatter.ISO_DATE);
            return new GoCommand(targetDate, false);
        } catch (DateTimeParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }


    }


}
