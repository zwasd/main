package seedu.saveit.logic.parser.report;

import static seedu.saveit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.saveit.logic.commands.report.ReportWindowExportCommand;
import seedu.saveit.logic.parser.ParserReportWindow;
import seedu.saveit.logic.parser.exceptions.ParseException;
import seedu.saveit.model.report.ExportFile;

/**
 * Parses report window export command.
 */
public class ExportReportWindowCommandParser implements ParserReportWindow<ReportWindowExportCommand> {

    @Override
    public ReportWindowExportCommand parse(String userInputTrimmed) throws ParseException {

        String[] userInputArray = userInputTrimmed.split("\\s+");

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
    }
}
