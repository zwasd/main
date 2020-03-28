package seedu.address.logic.parser.report;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.time.format.DateTimeParseException;
import java.util.InvalidPropertiesFormatException;

import seedu.address.logic.commands.general.HelpCommand;
import seedu.address.logic.commands.report.ExportReportCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Report;
import seedu.address.model.expenditure.Date;

/**
 * Parse export report.
 */
public class ExportReportCommandParser implements Parser<ExportReportCommand> {
    public ExportReportCommandParser() {

    }

    @Override
    public ExportReportCommand parse(String userInput) throws ParseException {

        String userInputTrimmed = userInput.trim();
        String[] userInputArray = userInputTrimmed.split(" ");

        if (userInputArray.length < 3) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExportReportCommand.MESSAGE_FAIL));
        }

        String startDateStr = userInputArray[1];
        String endDateStr = userInputArray[2];
        Date startDate;
        Date endDate;

        try {

            startDate = new Date(startDateStr);
            endDate = new Date(endDateStr);

        } catch (DateTimeParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        if (!Date.isEqualOrBefore(startDate, endDate)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        String graph = userInputArray[3];
        Report.GraphType graphType = null;

        switch (graph) {
        case "BAR":
            graphType = Report.GraphType.BAR;
            break;
        case "PIE":
            graphType = Report.GraphType.PIE;
            break;
        default:
            try {
                throw new InvalidPropertiesFormatException("Available graph types are : BAR, STACK, PIE");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        Report report = new Report(startDate, endDate, graphType);

        return new ExportReportCommand(report);
    }
}
