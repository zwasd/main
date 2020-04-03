package seedu.address.logic.commands.account;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.expenditure.ExpDeleteCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAccounts.getTypicalAccountList;


public class AccDeleteCommandTest {
    private Model model = new ModelManager(getTypicalAccountList(), new UserPrefs());

    @Test
    public void constructor_nullAccountName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AccCheckoutCommand(null));
    }

    @Test
    public void constructor_nonExistentAccountName_throwsCommandException() {
        assertThrows(CommandException.class, () -> new AccCheckoutCommand("nonExistingAccount")
                .execute(model));
    }

    @Test
    public void equals() {

        AccDeleteCommand deletePersonalAccountCommand = new AccDeleteCommand("PERSONAL");
        AccDeleteCommand deleteProjectAccountCommand = new AccDeleteCommand("PROJECT");

        // same object -> returns true
        assertTrue(deletePersonalAccountCommand.equals(deletePersonalAccountCommand));

        // same values -> returns true
        AccDeleteCommand deletePersonalAccountCommandCopy = new AccDeleteCommand("PERSONAL");
        assertTrue(deletePersonalAccountCommand.equals(deletePersonalAccountCommandCopy));

        // different types -> returns false
        assertFalse(deletePersonalAccountCommand.equals(1));

        // null -> returns false
        assertFalse(deletePersonalAccountCommand.equals(null));

        // different expenditure -> returns false
        assertFalse(deletePersonalAccountCommand.equals(deleteProjectAccountCommand));
    }



}
