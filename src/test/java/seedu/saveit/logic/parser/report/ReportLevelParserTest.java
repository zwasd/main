package seedu.saveit.logic.parser.report;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.saveit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.saveit.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.saveit.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.saveit.logic.commands.general.HelpCommand;
import seedu.saveit.logic.commands.repeat.RepeatAddCommand;
import seedu.saveit.logic.parser.exceptions.ParseException;
import seedu.saveit.model.expenditure.Repeat;
import seedu.saveit.testutil.RepeatBuilder;
import seedu.saveit.testutil.RepeatUtil;


public class ReportLevelParserTest {
    private final ReportLevelParser parser = new ReportLevelParser();

    //TODO: NEED TEST CASE FOR VIEW AND EXPORT REPORT COMMAND

    @Test
    public void parseReportCommand_view() throws Exception {
        Repeat repeat = new RepeatBuilder().build();
        RepeatAddCommand command = (RepeatAddCommand) parser.parseCommand(RepeatUtil.getAddCommand(repeat));
        assertEquals(new RepeatAddCommand(repeat), command);
    }


    @Test
    public void reportParseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void reportParseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, ()
            -> parser.parseCommand("unknownCommand"));
    }
}
