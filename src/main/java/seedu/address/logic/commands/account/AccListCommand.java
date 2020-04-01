package seedu.address.logic.commands.account;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.parser.account.AccLevelParser;
import seedu.address.model.Model;

/**
 * Lists all expenditures in the address book to the user.
 */
public class AccListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all accounts: \n1. default";

    public static final String MESSAGE = "Listed all accounts: \n";

    public static final String MESSAGE_USAGE = AccLevelParser.COMMAND_WORD + " " + COMMAND_WORD
            + ": lists all accounts\n" + "Example: " + AccLevelParser.COMMAND_WORD + " " + COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        String list = model.getAccountList().listAllName();
        return new CommandResult(MESSAGE + list);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || other instanceof AccListCommand;
    }
}
