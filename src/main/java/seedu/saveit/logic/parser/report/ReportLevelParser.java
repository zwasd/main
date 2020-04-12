package seedu.saveit.logic.parser.report;

import static java.util.Objects.requireNonNull;
import static seedu.saveit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.saveit.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.saveit.logic.commands.Command;
import seedu.saveit.logic.commands.general.HelpCommand;
import seedu.saveit.logic.commands.report.ExportReportCommand;
import seedu.saveit.logic.commands.report.PrintReportCommand;
import seedu.saveit.logic.commands.report.ViewReportCommand;
import seedu.saveit.logic.parser.TopLevelParser;
import seedu.saveit.logic.parser.exceptions.ParseException;

/**
 * Parse report commands.
 */
public class ReportLevelParser extends TopLevelParser {

    public static final String COMMAND_WORD = "report";
    public static final String MESSAGE_USAGE = "report view\nreport export\nreport print\n";
    public static final String HELP_MESSAGE = ViewReportCommand.MESSAGE_USAGE + "\n\n"
                                                + ExportReportCommand.MESSAGE_USAGE + "\n\n"
                                                + PrintReportCommand.MESSAGE_USAGE;

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        requireNonNull(userInput);

        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case ViewReportCommand.COMMAND_WORD:
            return new ViewReportCommandParser().parse(arguments);

        case ExportReportCommand.COMMAND_WORD:
            return new ExportReportCommandParser().parse(arguments);

        case PrintReportCommand.COMMAND_WORD:
            return new PrintReportCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
