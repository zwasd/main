package seedu.address.logic.parser.report;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_GRAPH_TYPE;

import seedu.address.logic.commands.report.ViewReportCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Report;
import seedu.address.model.expenditure.Date;

/**
 * Parse view report.
 */
public class ViewReportCommandParser implements Parser<ViewReportCommand> {
    public ViewReportCommandParser() {

    }

    @Override
    public ViewReportCommand parse(String userInput) throws ParseException {
        String userInputTrimmed = userInput.trim();
        String[] userInputArray = userInputTrimmed.split(" ");

        if (userInputArray.length < 3) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewReportCommand.MESSAGE_USAGE));
        }

        String startDateStr = userInputArray[0];
        String endDateStr = userInputArray[1];
        Date startDate;
        Date endDate;

        try {
            startDate = new Date(startDateStr);
            endDate = new Date(endDateStr);
        } catch (Exception e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ViewReportCommand.MESSAGE_USAGE));
        }

        if (!Date.isEqualOrBefore(startDate, endDate)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ViewReportCommand.MESSAGE_USAGE));
        }

        String graph = userInputArray[2];
        Report.GraphType graphType = null;

        switch (graph) {
        case "BAR":
            graphType = Report.GraphType.BAR;
            break;
        case "STACK":
            graphType = Report.GraphType.STACK;
            break;
        case "PIE":
            graphType = Report.GraphType.PIE;
            break;
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_GRAPH_TYPE,
                    ViewReportCommand.MESSAGE_USAGE));
        }
        Report report = new Report(startDate, endDate, graphType);

        return new ViewReportCommand(report);
    }

}
