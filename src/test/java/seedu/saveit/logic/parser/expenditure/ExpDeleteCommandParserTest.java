package seedu.saveit.logic.parser.expenditure;

import static seedu.saveit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.saveit.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.saveit.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.saveit.testutil.TypicalIndexes.INDEX_FIRST_EXPENDITURE;

import org.junit.jupiter.api.Test;

import seedu.saveit.logic.commands.expenditure.ExpDeleteCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the ExpDeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the ExpDeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class ExpDeleteCommandParserTest {

    private ExpDeleteCommandParser parser = new ExpDeleteCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new ExpDeleteCommand(INDEX_FIRST_EXPENDITURE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExpDeleteCommand.MESSAGE_USAGE));
    }
}
