package seedu.address.logic.commands.account;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Account;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.AccountBuilder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAccounts.getTypicalAccountList;

public class AccAddCommandTest {

    private Model model = new ModelManager(getTypicalAccountList(), new UserPrefs());

    @Test
    public void constructor_nullAccount_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AccAddCommand(null));
    }

    @Test
    public void add_duplicate_account() throws CommandException {
        model.addAccount(new Account("duplicate123"));
        assertThrows(CommandException.class, () ->new AccAddCommand(new Account("duplicate123"))
                .execute(model));
        model.deleteAccount("duplicate123");
    }

    @Test
    public void equals() {

        Account personal = new AccountBuilder("Personal").build();
        Account project = new AccountBuilder("Project").build();
        AccAddCommand addPersonalCommand = new AccAddCommand(personal);
        AccAddCommand addProjectCommand = new AccAddCommand(project);



        // same object -> returns true
        assertTrue(addPersonalCommand.equals(addPersonalCommand));

        // same values -> returns true
        AccAddCommand addPersonalCommandCopy = new AccAddCommand(personal);
        assertTrue(addPersonalCommand.equals(addPersonalCommandCopy));

        // different types -> returns false
        assertFalse(addPersonalCommand.equals(1));

        // null -> returns false
        assertFalse(addPersonalCommand.equals(null));

        // different expenditure -> returns false
        assertFalse(addPersonalCommand.equals(addProjectCommand));
    }
}
