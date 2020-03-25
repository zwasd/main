package seedu.address.logic.parser.account;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.account.AccCheckoutCommand;

public class AccCheckoutCommandParserTest {

    private final String MESSAGE_INVALID_FORMAT =
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
