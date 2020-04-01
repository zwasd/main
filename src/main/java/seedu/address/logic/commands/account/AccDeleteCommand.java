package seedu.address.logic.commands.account;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.MonthlySpendingCalculator;

/**
 * Delete account.
 */
public class AccDeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String NAME_CONTAIN_SPACE = "The account name should not contain space";

    public static final String NAME_TOO_LONG = "The account name should be less than 25 characters";

    public static final String MESSAGE_DELETE_ACCOUNT_SUCCESS = "Deleted Account: ";

    private final String targetAccountName;

    public AccDeleteCommand(String targetAccountName) {
        this.targetAccountName = targetAccountName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        String newActiveAccountName = model.deleteAccount(this.targetAccountName);
        MonthlySpendingCalculator monthlyCalculator = model.getMonthlySpending();
        return new CommandResult(MESSAGE_DELETE_ACCOUNT_SUCCESS + this.targetAccountName,
                newActiveAccountName, monthlyCalculator.getBudget(), monthlyCalculator.getTotalSpending());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AccDeleteCommand // instanceof handles nulls
                && targetAccountName.equals(((AccDeleteCommand) other).targetAccountName));
    }
}
