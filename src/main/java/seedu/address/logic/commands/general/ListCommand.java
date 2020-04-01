package seedu.address.logic.commands.general;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

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
