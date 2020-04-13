package seedu.saveit.logic.parser.general;

import seedu.saveit.logic.commands.general.ExitCommand;
import seedu.saveit.logic.commands.general.FindCommand;
import seedu.saveit.logic.commands.general.GoCommand;
import seedu.saveit.logic.commands.general.HelpCommand;
import seedu.saveit.logic.commands.general.SetBudgetCommand;
import seedu.saveit.logic.parser.Parser;
import seedu.saveit.logic.parser.account.AccLevelParser;
import seedu.saveit.logic.parser.exceptions.ParseException;
import seedu.saveit.logic.parser.expenditure.ExpLevelParser;
import seedu.saveit.logic.parser.repeat.RepeatLevelParser;
import seedu.saveit.logic.parser.report.ReportLevelParser;

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

        switch (args.trim()) {

        case ExpLevelParser.COMMAND_WORD:
            return new HelpCommand(ExpLevelParser.HELP_MESSAGE);

        case RepeatLevelParser.COMMAND_WORD:
            return new HelpCommand(RepeatLevelParser.HELP_MESSAGE);

        case ReportLevelParser.COMMAND_WORD:
            return new HelpCommand(ReportLevelParser.HELP_MESSAGE);

        case AccLevelParser.COMMAND_WORD:
            return new HelpCommand(AccLevelParser.HELP_MESSAGE);

        case GoCommand.COMMAND_WORD:
            return new HelpCommand(GoCommand.MESSAGE_USAGE);

        case ExitCommand.COMMAND_WORD:
            return new HelpCommand(ExitCommand.MESSAGE_USAGE);

        case FindCommand.COMMAND_WORD:
            return new HelpCommand(FindCommand.MESSAGE_USAGE);

        case SetBudgetCommand.COMMAND_WORD:
            return new HelpCommand(SetBudgetCommand.MESSAGE_USAGE);

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            return new HelpCommand(args.trim(), true);
        }
    }
}
