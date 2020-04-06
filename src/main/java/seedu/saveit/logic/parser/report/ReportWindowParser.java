package seedu.saveit.logic.parser.report;

import static java.util.Objects.requireNonNull;

import static seedu.saveit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.saveit.commons.core.Messages.MESSAGE_INVALID_DATE;

import java.time.format.DateTimeParseException;

import seedu.saveit.logic.commands.ReportCommand;
import seedu.saveit.logic.commands.report.ReportWindowExitCommand;
import seedu.saveit.logic.commands.report.ReportWindowExportCommand;
import seedu.saveit.logic.commands.report.ReportWindowHelpCommand;
import seedu.saveit.logic.commands.report.ReportWindowPrintCommand;
import seedu.saveit.logic.commands.report.ReportWindowViewCommand;
import seedu.saveit.logic.parser.ParserReportWindow;
import seedu.saveit.logic.parser.exceptions.ParseException;
import seedu.saveit.model.expenditure.Date;
import seedu.saveit.model.report.ExportFile;
import seedu.saveit.model.report.Report;



/**
 * Parses commands typed in report window.
 */
public class ReportWindowParser implements ParserReportWindow<ReportCommand> {

    @Override
    public ReportCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        String userInputTrimmed = userInput.trim();
        String[] userInputArray = userInputTrimmed.split("\\s+");

        switch (userInputArray[0]) {
        case "exit":
            if (userInputArray.length > 1) {
                throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT);
            }

            return new ReportWindowExitCommand();
        case "help":
            if (userInputArray.length > 1) {
                throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT);
            }
            return new ReportWindowHelpCommand();

        case "print":
            if (userInputArray.length > 1) {
                throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT);
            }
            return new ReportWindowPrintCommand();

        case "export":
            if (userInputArray.length != 2) {

                if (userInputArray.length > 2) {
                    throw new ParseException(ExportFile.FILENAME_CONSTRAINT);
                }

                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        ReportWindowExportCommand.MESSAGE_USAGE));
            } else {
                String fileName = userInputArray[1];
                return new ReportWindowExportCommand(fileName);
            }
        case "view":
            if (userInputArray.length < 4) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        ReportWindowViewCommand.MESSAGE_USAGE));
            }
            String graph = userInputArray[1];
            Report.GraphType graphType = Report.GraphType.NULL;

            graphType = Report.GraphType.mapToGraphType(graph);

            String startDateStr = userInputArray[2];
            String endDateStr = userInputArray[3];
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

            Report report = new Report(startDate, endDate, graphType);
            return new ReportWindowViewCommand(report);

        default:
            throw new ParseException("Unknown Command");
        }

    }

}


