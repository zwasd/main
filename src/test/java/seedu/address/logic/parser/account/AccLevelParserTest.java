package seedu.address.logic.parser.account;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.account.AccListCommand;
import seedu.address.logic.commands.general.HelpCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class AccLevelParserTest {
    private final AccLevelParser parser = new AccLevelParser();

    //TODO: DELETE ADD CHECKOUT RENAME TESTS NEED TO BE ADDED

    @Test
    public void parseAccCommand_list() throws Exception {
        assertTrue(parser.accParseCommand(AccListCommand.COMMAND_WORD) instanceof AccListCommand);
        assertTrue(parser.accParseCommand(AccListCommand.COMMAND_WORD + " 3") instanceof AccListCommand);
    }

    @Test
    public void accParseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.accParseCommand(""));
    }

    @Test
    public void accParseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, ()
            -> parser.accParseCommand("unknownCommand"));
    }

}
