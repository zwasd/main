package seedu.saveit.logic.commands.expenditure;

import seedu.saveit.commons.core.Messages;
import seedu.saveit.logic.commands.Command;
import seedu.saveit.logic.commands.CommandResult;
import seedu.saveit.logic.parser.expenditure.ExpLevelParser;
import seedu.saveit.model.Model;

/**
 * Terminates the program.
 */
public class ExpListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = ExpLevelParser.COMMAND_WORD + " " + COMMAND_WORD
            + ": lists expenditure records\n" + "Example: " + ExpLevelParser.COMMAND_WORD + " " + COMMAND_WORD;




    @Override
    public CommandResult execute(Model model) {
        model.updateFilteredBaseExpList(Model.PREDICATE_SHOW_ALL_BASEEXP);
        return new CommandResult(
                String.format(Messages.MESSAGE_EXPENDITURES_LISTED_OVERVIEW,
                        model.getFilteredBaseExpList().size()));
    }

}
