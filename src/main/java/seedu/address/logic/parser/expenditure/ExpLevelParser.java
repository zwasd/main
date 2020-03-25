package seedu.address.logic.parser.expenditure;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.expenditure.ExpAddCommand;
import seedu.address.logic.commands.expenditure.ExpDeleteCommand;
import seedu.address.logic.commands.expenditure.ExpEditCommand;
import seedu.address.logic.commands.expenditure.ExpFindCommand;
import seedu.address.logic.commands.expenditure.ExpSetBudgetCommand;
import seedu.address.logic.commands.general.HelpCommand;
import seedu.address.logic.parser.TopLevelParser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parse expenditure commands.
 */
public class ExpLevelParser extends TopLevelParser {

    public static final String COMMAND_WORD = "exp";

    public static final String MESSAGE_USAGE = "exp add\nexp edit\nexp delete\nexp find\nexp setsubget\n";

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

        System.out.println("--" + commandWord + "--");

        switch (commandWord) {

        case ExpAddCommand.COMMAND_WORD:
            return new ExpAddCommandParser().parse(arguments);

        case ExpEditCommand.COMMAND_WORD:
            return new ExpEditCommandParser().parse(arguments);

        case ExpDeleteCommand.COMMAND_WORD:
            return new ExpDeleteCommandParser().parse(arguments);

        case ExpFindCommand.COMMAND_WORD:
            return new ExpFindCommandParser().parse(arguments);

        case ExpSetBudgetCommand.COMMAND_WORD:
            return new ExpSetBudgetCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
