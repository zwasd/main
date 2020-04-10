package seedu.saveit.logic.parser.general;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.saveit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.saveit.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.saveit.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.saveit.logic.commands.general.GoCommand;

public class GoCommandParserTest {
    private final GoCommandParser parser = new GoCommandParser();

    @Test
    public void parse_nullArg_throwsParseException() {
        assertThrows(NullPointerException.class, () -> new GoCommandParser().parse(null));
    }

    @Test
    public void parse_invalidDate_throwsParserException() {
        assertParseFailure(parser, "2019-02-31",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GoCommand.MESSAGE_USAGE));
    }


    @Test
    public void parse_invalidDateFormat_throwsParserException() {
        assertParseFailure(parser, "2020/04/04",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GoCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsGoCommand() {
        // no leading and trailing whitespaces
        LocalDate pivot = LocalDate.now();

        GoCommand expectedGoCommand = new GoCommand(pivot);
        assertParseSuccess(parser, pivot.toString(), expectedGoCommand);

    }

}
