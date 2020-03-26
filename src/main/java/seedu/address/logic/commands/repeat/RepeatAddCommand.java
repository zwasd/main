package seedu.address.logic.commands.repeat;

import static java.util.Objects.requireNonNull;

import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INFO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERIOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
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
            + PREFIX_START_DATE + "STARTDATE "
            + PREFIX_END_DATE + "ENDDATE "
            + PREFIX_PERIOD + "[daily|monthly|weekly|annually]"
            + "[" + PREFIX_TAG + "TAG]...\n"

            + "Example: " + COMMAND_WORD + " "
            + PREFIX_INFO + "MRT topup"
            + PREFIX_AMOUNT + "50.0"
            + PREFIX_START_DATE + "2019-01-01 "
            + PREFIX_END_DATE + "2019-12-31 "
            + PREFIX_PERIOD + "monthly"
            + PREFIX_TAG + "Transport";

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
