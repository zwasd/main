package seedu.saveit.logic.commands.general;

import seedu.saveit.commons.core.Messages;
import seedu.saveit.logic.commands.Command;
import seedu.saveit.logic.commands.CommandResult;
import seedu.saveit.model.Model;

/**
 * Terminates the program.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    @Override
    public CommandResult execute(Model model) {
        model.updateFilteredBaseExpList(Model.PREDICATE_SHOW_ALL_BASEEXP);
        return new CommandResult(
                String.format(Messages.MESSAGE_EXPENDITURES_LISTED_OVERVIEW,
                        model.getFilteredBaseExpList().size()));
    }

}
