package seedu.saveit.logic.parser.report;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.saveit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.saveit.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.saveit.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.saveit.logic.commands.general.HelpCommand;
import seedu.saveit.logic.commands.report.ExportReportCommand;
import seedu.saveit.logic.commands.report.PrintReportCommand;
import seedu.saveit.logic.commands.report.ViewReportCommand;
import seedu.saveit.logic.parser.exceptions.ParseException;
import seedu.saveit.model.Report;
import seedu.saveit.testutil.ReportBuilder;
import seedu.saveit.testutil.ReportUtil;


public class ReportLevelParserTest {
    private final ReportLevelParser parser = new ReportLevelParser();

    //TODO: NEED TEST CASE FOR VIEW AND EXPORT REPORT COMMAND

    @Test
    public void parseReportCommand_view() throws Exception {
        Report report = new ReportBuilder().build();
        ViewReportCommand command = (ViewReportCommand) parser.parseCommand(ReportUtil.getReportViewCommand(report));
        assertEquals(new ViewReportCommand(report), command);
    }

    @Test
    public void parseReportCommand_export() throws Exception {
        Report report = new ReportBuilder().build();
        ExportReportCommand command = (ExportReportCommand) parser.parseCommand(ReportUtil
                .getReportExportCommand(report));
        assertEquals(new ExportReportCommand(report), command);
    }

    @Test
    public void parseReportCommand_print() throws Exception {
        Report report = new ReportBuilder().build();
        PrintReportCommand command = (PrintReportCommand) parser.parseCommand(ReportUtil.getReportPrintCommand(report));
        assertEquals(new PrintReportCommand(report), command);
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
