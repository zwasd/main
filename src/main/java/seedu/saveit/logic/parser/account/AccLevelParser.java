package seedu.saveit.logic.parser.account;

import static seedu.saveit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.saveit.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.saveit.logic.commands.Command;
import seedu.saveit.logic.commands.account.AccAddCommand;
import seedu.saveit.logic.commands.account.AccCheckoutCommand;
import seedu.saveit.logic.commands.account.AccClearCommand;
import seedu.saveit.logic.commands.account.AccDeleteCommand;
import seedu.saveit.logic.commands.account.AccListCommand;
import seedu.saveit.logic.commands.account.AccRenameCommand;
import seedu.saveit.logic.commands.general.HelpCommand;
import seedu.saveit.logic.parser.TopLevelParser;
import seedu.saveit.logic.parser.exceptions.ParseException;

/**
 * Parse account commands.
 */
public class AccLevelParser extends TopLevelParser {

    public static final String COMMAND_WORD = "acc";

    public static final String MESSAGE_USAGE = "acc add\nacc checkout\nacc clear\nacc delete\nacc list\nacc rename\n";

    public static final String HELP_MESSAGE = AccAddCommand.MESSAGE_USAGE + "\n\n"
                                                + AccDeleteCommand.MESSAGE_USAGE + "\n\n"
                                                + AccCheckoutCommand.MESSAGE_USAGE + "\n\n"
                                                + AccRenameCommand.MESSAGE_USAGE + "\n\n"
                                                + AccListCommand.MESSAGE_USAGE + "\n\n"
                                                + AccClearCommand.MESSAGE_USAGE;

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
        case AccAddCommand.COMMAND_WORD:
            return new AccAddCommandParser().parse(arguments);

        case AccDeleteCommand.COMMAND_WORD:
            return new AccDeleteCommandParser().parse(arguments);

        case AccCheckoutCommand.COMMAND_WORD:
            return new AccCheckoutCommandParser().parse(arguments);

        case AccRenameCommand.COMMAND_WORD:
            return new AccRenameCommandParser().parse(arguments);

        case AccClearCommand.COMMAND_WORD:
            return new AccClearCommandParser().parse(arguments);

        case AccListCommand.COMMAND_WORD:
            return new AccListCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
