package seedu.address.logic.parser.account;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.account.AccListCommand;

public class AccListCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AccListCommand.MESSAGE_USAGE);

    private final AccListCommandParser parser = new AccListCommandParser();

    @Test
    public void parse_validValue_success() {
        // whitespace
        assertParseSuccess(parser, " ", new AccListCommand());

        // empty
        assertParseSuccess(parser, "", new AccListCommand());
    }

    @Test
    public void parse_invalidValue_failure() {
        // any letters or words
        assertParseFailure(parser, "something", MESSAGE_INVALID_FORMAT);
    }
}
