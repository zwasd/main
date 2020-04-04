package seedu.saveit.logic.parser.general;

import static java.util.Objects.requireNonNull;
import static seedu.saveit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.saveit.logic.commands.general.GoCommand;

import seedu.saveit.logic.parser.Parser;
import seedu.saveit.logic.parser.exceptions.ParseException;


/**
 * Parse switch to a date.
 */
public class GoCommandParser implements Parser<GoCommand> {

    /**
     * Goes to a target date.
     * @param args The date argument to be parsed.
     */
    public GoCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GoCommand.MESSAGE_USAGE));
        }

        if (trimmedArgs.equals("today")) {
            return new GoCommand(LocalDate.now(), false);
        }

        try {
            LocalDate targetDate = LocalDate.parse(trimmedArgs, DateTimeFormatter.ISO_DATE);
            return new GoCommand(targetDate, false);
        } catch (DateTimeParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, GoCommand.MESSAGE_USAGE));
        }


    }


}
