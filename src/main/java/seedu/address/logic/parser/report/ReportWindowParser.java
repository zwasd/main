package seedu.address.logic.parser.report;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATE;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_GRAPH_TYPE;

import java.time.format.DateTimeParseException;

import seedu.address.logic.commands.ReportCommand;
import seedu.address.logic.commands.report.ReportWindowExitCommand;
import seedu.address.logic.commands.report.ReportWindowStatsCommand;
import seedu.address.logic.parser.ParserReportWindow;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Report;
import seedu.address.model.expenditure.Date;

/**
 * Parses commands typed in report window.
 */
public class ReportWindowParser implements ParserReportWindow<ReportCommand> {


    @Override
    public ReportCommand parse(String userInput) throws ParseException {
        String userInputTrimmed = userInput.trim();
        String[] userInputArray = userInputTrimmed.split(" ");

        if (userInput.equals("exit")) {
            return new ReportWindowExitCommand();
        }

        if (userInputArray.length < 3) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ReportWindowStatsCommand.MESSAGE_USAGE));
        }

        String graph = userInputArray[0];
        Report.GraphType graphType = null;

        switch (graph) {
        case "BAR":
            graphType = Report.GraphType.BAR;
            break;
        case "PIE":
            graphType = Report.GraphType.PIE;
            break;
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_GRAPH_TYPE,
                    ReportWindowStatsCommand.MESSAGE_USAGE));
        }

        String startDateStr = userInputArray[1];
        String endDateStr = userInputArray[2];
        Date startDate;
        Date endDate;

        try {
            startDate = new Date(startDateStr);
            endDate = new Date(endDateStr);
        } catch (DateTimeParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_DATE,
                    ReportWindowStatsCommand.MESSAGE_USAGE));
        }

        if (!Date.isEqualOrBefore(startDate, endDate)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ReportWindowStatsCommand.MESSAGE_USAGE));
        }

        Report report = new Report(startDate, endDate, graphType);
        return new ReportWindowStatsCommand(report);

    }
}


