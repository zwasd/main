package seedu.saveit.logic.commands.account;

import static java.util.Objects.requireNonNull;

import seedu.saveit.logic.commands.Command;
import seedu.saveit.logic.commands.CommandResult;
import seedu.saveit.logic.commands.exceptions.CommandException;
import seedu.saveit.logic.parser.account.AccLevelParser;
import seedu.saveit.model.Model;
import seedu.saveit.model.MonthlySpendingCalculator;


/**
 * Delete account.
 */
public class AccDeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_DELETE_ACCOUNT_SUCCESS = "Deleted Account: ";
    public static final String MESSAGE_USAGE = AccLevelParser.COMMAND_WORD + " " + COMMAND_WORD
            + ": deletes the account with the specified name\n"
            + "Parameters: ACCOUNT_NAME (one word containing only alphanumeric characters, less than 26 characters)\n"
            + "Example: " + AccLevelParser.COMMAND_WORD + " " + COMMAND_WORD + " default";

    private final String targetAccountName;

    public AccDeleteCommand(String targetAccountName) {
        requireNonNull(targetAccountName);
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
