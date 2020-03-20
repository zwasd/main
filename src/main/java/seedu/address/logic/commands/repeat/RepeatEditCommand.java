package seedu.address.logic.commands.repeat;

import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INFO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.expenditure.ExpLevelParser;
import seedu.address.model.Model;


/**
 * Edit repeat object.
 * TODO: NEED MODIFY
 */
public class RepeatEditCommand extends Command {
    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = ExpLevelParser.COMMAND_WORD + " " + COMMAND_WORD
            + ": Edits the details of the expenditure identified "
            + "by the index number used in the displayed expenditure list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_INFO + "INFO] "
            + "[" + PREFIX_AMOUNT + "AMOUNT] "
            + "[" + PREFIX_DATE + "DATE] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + ExpLevelParser.COMMAND_WORD + " " + COMMAND_WORD + " 1 "
            + PREFIX_AMOUNT + "4.3";

    public static final String MESSAGE_EDIT_EXPENDITURE_SUCCESS = "Edited Repeat Expenditure: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";



    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }
}
