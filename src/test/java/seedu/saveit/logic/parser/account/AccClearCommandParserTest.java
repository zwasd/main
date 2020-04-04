package seedu.saveit.logic.parser.account;

import static seedu.saveit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.saveit.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.saveit.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.saveit.logic.commands.account.AccClearCommand;

public class AccClearCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AccClearCommand.MESSAGE_USAGE);

    private final AccClearCommandParser parser = new AccClearCommandParser();

    @Test
    public void parse_validValue_success() {
        // whitespace
        assertParseSuccess(parser, " ", new AccClearCommand());

        // empty
        assertParseSuccess(parser, "", new AccClearCommand());
    }

    @Test
    public void parse_invalidValue_failure() {
        // any letters or words
        assertParseFailure(parser, "something", MESSAGE_INVALID_FORMAT);
    }
}
