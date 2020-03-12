package seedu.address.logic.parser.report;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.general.HelpCommand;
import seedu.address.logic.parser.exceptions.ParseException;


public class ReportLevelParserTest {
    private final ReportLevelParser parser = new ReportLevelParser();

    //TODO: NEED TEST CASE FOR VIEW AND EXPORT REPORT COMMAND

    @Test
    public void reportParseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.reportParseCommand(""));
    }

    @Test
    public void reportParseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, ()
            -> parser.reportParseCommand("unknownCommand"));
    }
}
