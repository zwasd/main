package seedu.address.logic.commands.account;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.account.AccLevelParser;
import seedu.address.model.Model;
import seedu.address.model.MonthlySpendingCalculator;

/**
 * Change to another account.
 */
public class AccCheckoutCommand extends Command {

    public static final String COMMAND_WORD = "checkout";
    public static final String MESSAGE_USAGE = AccLevelParser.COMMAND_WORD + " " + COMMAND_WORD
            + ": Checks out the account with the specified name.\n"
            + "Parameters: ACCOUNT_NAME (must be one word containing any characters)\n"
            + "Example: " + AccLevelParser.COMMAND_WORD + " " + COMMAND_WORD + " default";
    public static final String MESSAGE_CHECKOUT_SUCCESS = "Successfully checked out account: %1$s";

    private final String accountName;

    public AccCheckoutCommand(String accountName) {
        this.accountName = accountName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (!model.updateActiveAccount(accountName)) {
            throw new CommandException(Messages.MESSAGE_INVALID_ACCOUNT_NAME);
        }
        MonthlySpendingCalculator monthlyCalculator = model.getMonthlySpending(accountName);
        return new CommandResult(String.format(MESSAGE_CHECKOUT_SUCCESS, accountName), accountName,
                monthlyCalculator.getBudget(), monthlyCalculator.getTotalSpending());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AccCheckoutCommand // instanceof handles nulls
                && accountName.equals(((AccCheckoutCommand) other).accountName));
    }
}
