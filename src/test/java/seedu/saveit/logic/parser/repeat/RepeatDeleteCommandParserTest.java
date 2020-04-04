package seedu.saveit.logic.parser.repeat;

import static seedu.saveit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.saveit.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.saveit.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.saveit.testutil.TypicalIndexes.INDEX_FIRST_REPEAT;

import org.junit.jupiter.api.Test;

import seedu.saveit.logic.commands.repeat.RepeatDeleteCommand;


public class RepeatDeleteCommandParserTest {
    private final RepeatDeleteCommandParser parser = new RepeatDeleteCommandParser();
    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new RepeatDeleteCommand(INDEX_FIRST_REPEAT));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RepeatDeleteCommand.MESSAGE_USAGE));
    }
}
