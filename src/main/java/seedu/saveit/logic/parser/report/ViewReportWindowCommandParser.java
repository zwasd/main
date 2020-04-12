package seedu.saveit.logic.parser.report;

import static seedu.saveit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.saveit.commons.core.Messages.MESSAGE_INVALID_DATE;

import java.time.format.DateTimeParseException;

import seedu.saveit.logic.commands.report.ReportWindowViewCommand;
import seedu.saveit.logic.parser.ParserReportWindow;
import seedu.saveit.logic.parser.exceptions.ParseException;
import seedu.saveit.model.expenditure.Date;
import seedu.saveit.model.report.Report;

/**
 * Parses report window view command.
 */
public class ViewReportWindowCommandParser implements ParserReportWindow<ReportWindowViewCommand> {


    @Override
    public ReportWindowViewCommand parse(String userInputTrimmed) throws ParseException {

        String[] userInputArray = userInputTrimmed.split("\\s+");

        if (userInputArray.length < 5) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ReportWindowViewCommand.MESSAGE_USAGE));
        }

        String startDateStr = userInputArray[1];
        String endDateStr = userInputArray[2];
        Date startDate;
        Date endDate;

        try {
            startDate = new Date(startDateStr);
            endDate = new Date(endDateStr);
        } catch (DateTimeParseException | IllegalArgumentException e) {
            throw new ParseException(String.format(Date.MESSAGE_CONSTRAINTS,
                    ReportWindowViewCommand.MESSAGE_USAGE));
        }

        if (!Date.isEqualOrBefore(startDate, endDate)) {
            throw new ParseException(String.format(MESSAGE_INVALID_DATE,
                    ReportWindowViewCommand.MESSAGE_USAGE));
        }

        String graph = userInputArray[3];
        Report.GraphType graphType = Report.GraphType.NULL;

        graphType = Report.GraphType.mapToGraphType(graph);

        assert graphType != Report.GraphType.NULL;

        switch (userInputArray[4]) {

        case "tag":
            Report report = new Report(startDate, endDate, graphType, "tag");
            return new ReportWindowViewCommand(report);

        case "month":
            Report report1 = new Report(startDate, endDate, graphType, "month");
            return new ReportWindowViewCommand(report1);

        default:
            throw new ParseException(Report.ORGANISE_TYPE_MESSAGE_CONSTRAINT);

        }
    }

}
