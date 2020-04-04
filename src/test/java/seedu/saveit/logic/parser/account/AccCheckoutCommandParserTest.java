package seedu.saveit.logic.parser.account;

import static seedu.saveit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.saveit.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.saveit.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.saveit.logic.commands.account.AccCheckoutCommand;

public class AccCheckoutCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AccCheckoutCommand.MESSAGE_USAGE);

    private final AccCheckoutCommandParser parser = new AccCheckoutCommandParser();

    @Test
    public void parse_validValue_success() {
        assertParseSuccess(parser, "school", new AccCheckoutCommand("school"));
    }

    @Test
    public void parse_invalidValue_failure() {
        // empty value
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

        // with space
        assertParseFailure(parser, "my account", MESSAGE_INVALID_FORMAT);
    }
}
