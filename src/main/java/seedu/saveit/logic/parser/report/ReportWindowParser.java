package seedu.saveit.logic.parser.report;

import static java.util.Objects.requireNonNull;

import seedu.saveit.logic.commands.ReportCommand;
import seedu.saveit.logic.commands.report.ReportWindowExitCommand;
import seedu.saveit.logic.commands.report.ReportWindowHelpCommand;
import seedu.saveit.logic.commands.report.ReportWindowPrintCommand;
import seedu.saveit.logic.parser.ParserReportWindow;
import seedu.saveit.logic.parser.exceptions.ParseException;


/**
 * Parses commands typed in report window.
 */
public class ReportWindowParser implements ParserReportWindow<ReportCommand> {

    public static final String MESSAGE_INVALID_COMMAND = "Invalid Command.";

    @Override
    public ReportCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        String userInputTrimmed = userInput.trim();
        String[] userInputArray = userInputTrimmed.split("\\s+");

        switch (userInputArray[0]) {
        case "exit":
            if (!userInputTrimmed.equals("exit")) {
                throw new ParseException(MESSAGE_INVALID_COMMAND);
            }

            return new ReportWindowExitCommand();
        case "help":
            if (!userInputTrimmed.equals("help")) {
                throw new ParseException(MESSAGE_INVALID_COMMAND);
            }
            return new ReportWindowHelpCommand();

        case "print":
            if (!userInputTrimmed.equals("print")) {
                throw new ParseException(MESSAGE_INVALID_COMMAND);
            }
            return new ReportWindowPrintCommand();

        case "export":

            return new ExportReportWindowCommandParser().parse(userInputTrimmed);

        case "view":

            return new ViewReportWindowCommandParser().parse(userInputTrimmed);

        default:
            throw new ParseException(MESSAGE_INVALID_COMMAND);
        }

    }

}


