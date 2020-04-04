package seedu.address.logic.parser.general;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.general.FindCommand;
import seedu.address.logic.commands.general.GoCommand;
import seedu.address.model.expenditure.InfoContainsKeywordsPredicate;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

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

        GoCommand expectedGoCommand = new GoCommand(pivot, false);
        assertParseSuccess(parser, pivot.toString(), expectedGoCommand);

    }

}
