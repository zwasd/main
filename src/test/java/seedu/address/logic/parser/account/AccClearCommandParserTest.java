package seedu.address.logic.parser.account;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.account.AccClearCommand;

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
