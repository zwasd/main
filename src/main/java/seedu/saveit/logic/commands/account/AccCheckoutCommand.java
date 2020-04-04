package seedu.saveit.logic.commands.account;

import seedu.saveit.commons.core.Messages;
import seedu.saveit.logic.commands.Command;
import seedu.saveit.logic.commands.CommandResult;
import seedu.saveit.logic.commands.exceptions.CommandException;
import seedu.saveit.logic.parser.account.AccLevelParser;
import seedu.saveit.model.Model;
import seedu.saveit.model.MonthlySpendingCalculator;

import static java.util.Objects.requireNonNull;

/**
 * Change to another account.
 */
public class AccCheckoutCommand extends Command {

    public static final String COMMAND_WORD = "checkout";
    public static final String MESSAGE_USAGE = AccLevelParser.COMMAND_WORD + " " + COMMAND_WORD
            + ": Checks out the account with the specified name.\n"
            + "Parameters: ACCOUNT_NAME (one word containing only alphanumeric characters, less than 26 characters)\n"
            + "Example: " + AccLevelParser.COMMAND_WORD + " " + COMMAND_WORD + " default";
    public static final String MESSAGE_CHECKOUT_SUCCESS = "Successfully checked out account: %1$s";

    private final String accountName;

    public AccCheckoutCommand(String accountName) {
        requireNonNull(accountName);
        this.accountName = accountName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (!model.updateActiveAccount(accountName)) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    String.format(Messages.MESSAGE_INVALID_ACCOUNT_NAME, accountName)));
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
