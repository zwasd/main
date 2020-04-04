package seedu.saveit.logic.parser.account;

import static seedu.saveit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.saveit.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.saveit.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.saveit.logic.commands.account.AccAddCommand;
import seedu.saveit.logic.commands.account.AccDeleteCommand;

public class AccDeleteCommandParserTest {
    private final AccDeleteCommandParser parser = new AccDeleteCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "school", new AccDeleteCommand("school"));
    }

    @Test
    public void parseInvalidArgs_nameContainSpace_parseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AccDeleteCommand.MESSAGE_USAGE);

        String expectedMessage1 = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AccAddCommand.NAME_CONTAIN_SPACE + "\n" + AccDeleteCommand.MESSAGE_USAGE);


        // contains space
        assertParseFailure(parser, "my account", expectedMessage1);

        // empty
        assertParseFailure(parser, "", expectedMessage);
    }

    @Test
    public void parseInvalidArgs_nameTooLong_parseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AccAddCommand.NAME_TOO_LONG
                + "\n" + AccDeleteCommand.MESSAGE_USAGE);

        assertParseFailure(parser, "thisIsAVeryLongAccountName", expectedMessage);
    }
}
