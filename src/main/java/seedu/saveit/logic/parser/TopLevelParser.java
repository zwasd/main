package seedu.saveit.logic.parser;

import static seedu.saveit.commons.core.Messages.MESSAGE_EMPTY_COMMAND;
import static seedu.saveit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.saveit.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.saveit.logic.commands.Command;
import seedu.saveit.logic.commands.general.ExitCommand;
import seedu.saveit.logic.commands.general.FindCommand;
import seedu.saveit.logic.commands.general.GoCommand;
import seedu.saveit.logic.commands.general.HelpCommand;
import seedu.saveit.logic.commands.general.SetBudgetCommand;
import seedu.saveit.logic.parser.account.AccLevelParser;
import seedu.saveit.logic.parser.exceptions.ParseException;
import seedu.saveit.logic.parser.expenditure.ExpLevelParser;
import seedu.saveit.logic.parser.general.FindCommandParser;
import seedu.saveit.logic.parser.general.GoCommandParser;
import seedu.saveit.logic.parser.general.HelpCommandParser;
import seedu.saveit.logic.parser.general.SetBudgetCommandParser;
import seedu.saveit.logic.parser.repeat.RepeatLevelParser;
import seedu.saveit.logic.parser.report.ReportLevelParser;

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
        if (userInput.isEmpty()) {
            throw new ParseException(MESSAGE_EMPTY_COMMAND);
        }

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

        case SetBudgetCommand.COMMAND_WORD:
            return new SetBudgetCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
