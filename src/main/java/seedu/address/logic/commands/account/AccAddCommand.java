package seedu.address.logic.commands.account;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Account;
import seedu.address.model.Model;

/**
 * Add account.
 */
public class AccAddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_SUCCESS = "New account added: %1$s";
    public static final String MESSAGE_DUPLICATE_ACCOUNT = "This account name already exists in the $AVE IT";

    private final Account toAdd;

    public AccAddCommand(Account account) {
        requireNonNull(account);
        toAdd = account;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult("Not implemented yet.");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AccAddCommand // instanceof handles nulls
                && toAdd.equals(((AccAddCommand) other).toAdd));
    }

}
