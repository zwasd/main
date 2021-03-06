package seedu.saveit.logic.parser;

import seedu.saveit.logic.commands.ReportCommand;
import seedu.saveit.logic.parser.exceptions.ParseException;

/**
 * Represents a Parser that is able to parse user input into a {@code Command} of type {@code T}.
 */
public interface ParserReportWindow<T extends ReportCommand> {

    /**
     * Parses {@code userInput} into a command and returns it.
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    T parse(String userInput) throws ParseException;
}
