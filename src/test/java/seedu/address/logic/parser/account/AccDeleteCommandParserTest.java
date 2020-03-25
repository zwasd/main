package seedu.address.logic.parser.account;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.account.AccDeleteCommand;

public class AccDeleteCommandParserTest {
    private final AccDeleteCommandParser parser = new AccDeleteCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "school", new AccDeleteCommand("school"));
    }

    @Test
    public void parseInvalidArgs_nameContainSpace_parseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AccDeleteCommand.NAME_CONTAIN_SPACE);

        // contains space
        assertParseFailure(parser, "my account", expectedMessage);

        // empty
        assertParseFailure(parser, "", expectedMessage);
    }

    @Test
    public void parseInvalidArgs_nameTooLong_parseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AccDeleteCommand.NAME_TOO_LONG);

        assertParseFailure(parser, "thisIsAVeryLongAccountName", expectedMessage);
    }
}
