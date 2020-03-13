package seedu.address.logic.parser.general;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import seedu.address.logic.commands.account.AccClearCommand;
import seedu.address.logic.commands.general.ExitCommand;
import seedu.address.logic.commands.general.GoCommand;
import seedu.address.logic.commands.general.HelpCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.account.AccLevelParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.expenditure.ExpLevelParser;
import seedu.address.logic.parser.report.ReportLevelParser;

/**
 * Parse help.
 */
public class HelpCommandParser implements Parser<HelpCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the HelpCommand
     * and returns an HelpCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public HelpCommand parse(String args) throws ParseException {

        if (args.trim().equals("")) {
            return new HelpCommand();
        }

        switch (args) {

        case ExpLevelParser.COMMAND_WORD:
            return new HelpCommand();

        case ReportLevelParser.COMMAND_WORD:
            return new HelpCommand();

        case AccLevelParser.COMMAND_WORD:
            return new HelpCommand();

        case AccClearCommand.COMMAND_WORD:
            return new HelpCommand();

        case GoCommand.COMMAND_WORD:
            return new HelpCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ExitCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
