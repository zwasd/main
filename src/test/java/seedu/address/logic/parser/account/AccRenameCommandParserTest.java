package seedu.address.logic.parser.account;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.account.AccRenameCommand;

public class AccRenameCommandParserTest {
    private final AccRenameCommandParser parser = new AccRenameCommandParser();

    @Test
    public void parse_validArgs_returnsAccRenameCommand() {
        assertParseSuccess(parser, "school work", new AccRenameCommand("school", "work"));
    }

    @Test
    public void parse_invalidArgs_parseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AccRenameCommand.INVALID_NAME_INPUT);

        // more than 2 words
        assertParseFailure(parser, "my school work", expectedMessage);

        // only one word
        assertParseFailure(parser, "work", expectedMessage);

        // empty
        assertParseFailure(parser, "", expectedMessage);

        // old account name at least 26 characters
        assertParseFailure(parser, "thisIsAVeryLongAccountName work", expectedMessage);

        // new account name at least 26 characters
        assertParseFailure(parser, "school thisIsAVeryLongAccountName", expectedMessage);

        // old account name empty
        assertParseFailure(parser, " work", expectedMessage);

        // new account name empty
        assertParseFailure(parser, "work ", expectedMessage);
    }
}
