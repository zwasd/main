package seedu.address.logic.commands.repeat;

import static java.util.Objects.requireNonNull;

import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INFO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.repeat.RepeatLevelParser;
import seedu.address.model.Model;
import seedu.address.model.expenditure.Repeat;


/**
 * Add repeat object.
 * TODO: NEED MODIFY
 */
public class RepeatAddCommand extends Command {
    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = RepeatLevelParser.COMMAND_WORD + " " + COMMAND_WORD
            + ": Adds a repeating expenditure to the $AVE IT. "
            + "Parameters: "
            + PREFIX_INFO + "INFO "
            + PREFIX_AMOUNT + "AMOUNT "
            + "[" + PREFIX_DATE + "DATE] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_INFO + "Chicken rice "
            + PREFIX_AMOUNT + "3.5 "
            + PREFIX_DATE + "2019-09-11 "
            + PREFIX_TAG + "friends";

    public static final String MESSAGE_SUCCESS = "New repeat added: %1$s";
    private final Repeat toAdd;

    public RepeatAddCommand(Repeat repeat) {
        requireNonNull(repeat);
        toAdd = repeat;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.addRepeat(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RepeatAddCommand // instanceof handles nulls
                && toAdd.equals(((RepeatAddCommand) other).toAdd));
    }


}
