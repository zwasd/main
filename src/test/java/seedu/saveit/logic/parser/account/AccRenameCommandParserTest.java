package seedu.saveit.logic.parser.account;

import static seedu.saveit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.saveit.logic.commands.account.AccAddCommand.NAME_TOO_LONG;
import static seedu.saveit.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.saveit.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.saveit.logic.commands.account.AccRenameCommand;

public class AccRenameCommandParserTest {
    private final AccRenameCommandParser parser = new AccRenameCommandParser();

    @Test
    public void parse_validArgs_returnsAccRenameCommand() {
        assertParseSuccess(parser, "school work", new AccRenameCommand("school", "work"));
    }

    @Test
    public void parse_invalidArgs_parseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AccRenameCommand.MESSAGE_USAGE);
        String expectedMessage1 = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                "The number of parameters is incorrect\n" + AccRenameCommand.MESSAGE_USAGE);
        String expectedMessage2 = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                NAME_TOO_LONG + "\n" + AccRenameCommand.MESSAGE_USAGE);

        // more than 2 words
        assertParseFailure(parser, "my school work", expectedMessage1);

        // only one word
        assertParseFailure(parser, "work", expectedMessage1);

        // empty
        assertParseFailure(parser, "", expectedMessage);

        // old account name at least 26 characters
        assertParseFailure(parser, "thisIsAVeryLongAccountName work", expectedMessage2);

        // new account name at least 26 characters
        assertParseFailure(parser, "school thisIsAVeryLongAccountName", expectedMessage2);

        // old account name empty
        assertParseFailure(parser, " work", expectedMessage1);

        // new account name empty
        assertParseFailure(parser, "work ", expectedMessage1);
    }
}
