package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.general.ExitCommand;
import seedu.address.logic.commands.general.FindCommand;
import seedu.address.logic.commands.general.GoCommand;
import seedu.address.logic.commands.general.HelpCommand;
import seedu.address.logic.parser.account.AccLevelParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.expenditure.ExpLevelParser;
import seedu.address.logic.parser.general.FindCommandParser;
import seedu.address.logic.parser.general.GoCommandParser;
import seedu.address.logic.parser.general.HelpCommandParser;
import seedu.address.logic.parser.repeat.RepeatLevelParser;
import seedu.address.logic.parser.report.ReportLevelParser;

/**
 * Parses user input.
 */
public class TopLevelParser {

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
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {

        case ExpLevelParser.COMMAND_WORD:
            return new ExpLevelParser().parseCommand(arguments);

        case ReportLevelParser.COMMAND_WORD:
            return new ReportLevelParser().parseCommand(arguments);

        case AccLevelParser.COMMAND_WORD:
            return new AccLevelParser().parseCommand(arguments);

        case RepeatLevelParser.COMMAND_WORD:
            return new RepeatLevelParser().parseCommand(arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case GoCommand.COMMAND_WORD:
            return new GoCommandParser().parse(arguments);

        case HelpCommand.COMMAND_WORD:
            return new HelpCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
