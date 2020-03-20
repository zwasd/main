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
    public static final String NAME_CONTAIN_SPACE = "The account name contains space";
    public static final String NAME_TOO_LONG = "The account name should be less than 25 characters";
    public static final String MESSAGE_SUCCESS = "New account added: ";
    public static final String MESSAGE_DUPLICATE_ACCOUNT = "This account name already exists in the $AVE IT";

    private final Account toAdd;

    public AccAddCommand(Account account) {
        requireNonNull(account);
        toAdd = account;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.addAccount(toAdd);
        return new CommandResult(MESSAGE_SUCCESS + toAdd.getAccountName());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AccAddCommand // instanceof handles nulls
                && toAdd.equals(((AccAddCommand) other).toAdd));
    }

}
