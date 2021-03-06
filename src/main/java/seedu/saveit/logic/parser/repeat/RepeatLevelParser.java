package seedu.saveit.logic.parser.repeat;

import static java.util.Objects.requireNonNull;

import static seedu.saveit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.saveit.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.saveit.logic.commands.Command;
import seedu.saveit.logic.commands.general.HelpCommand;
import seedu.saveit.logic.commands.repeat.RepeatAddCommand;
import seedu.saveit.logic.commands.repeat.RepeatDeleteCommand;
import seedu.saveit.logic.commands.repeat.RepeatEditCommand;
import seedu.saveit.logic.parser.TopLevelParser;
import seedu.saveit.logic.parser.exceptions.ParseException;

/**
 * Parse repeat commands.
 */
public class RepeatLevelParser extends TopLevelParser {

    public static final String COMMAND_WORD = "repeat";

    public static final String MESSAGE_USAGE = "repeat add\nrepeat edit\nrepeat delete\n";

    public static final String HELP_MESSAGE = RepeatAddCommand.MESSAGE_USAGE + "\n\n"
                                            + RepeatEditCommand.MESSAGE_USAGE + "\n\n"
                                            + RepeatDeleteCommand.MESSAGE_USAGE;

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

        case RepeatAddCommand.COMMAND_WORD:
            return new RepeatAddCommandParser().parse(arguments);

        case RepeatEditCommand.COMMAND_WORD:
            return new RepeatEditCommandParser().parse(arguments);

        case RepeatDeleteCommand.COMMAND_WORD:
            return new RepeatDeleteCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
