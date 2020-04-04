package seedu.saveit.logic.commands.account;

import org.junit.jupiter.api.Test;
import seedu.saveit.logic.commands.exceptions.CommandException;
import seedu.saveit.model.Account;
import seedu.saveit.model.Model;
import seedu.saveit.model.ModelManager;
import seedu.saveit.model.UserPrefs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.saveit.testutil.Assert.assertThrows;
import static seedu.saveit.testutil.TypicalAccounts.getTypicalAccountList;

public class AccRenameCommandTest {
    private Model model = new ModelManager(getTypicalAccountList(), new UserPrefs());

    @Test
    public void constructor_nullAccountRename_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AccRenameCommand("default", null));
    }

    @Test
    public void constructor_existentAccountName_throwsCommandException() throws CommandException {
        model.addAccount(new Account("testing123"));
        model.addAccount(new Account("123testing"));
        assertThrows(CommandException.class, () -> new AccRenameCommand("testing123", "123testing")
                .execute(model));
        model.deleteAccount("testing123");
        model.deleteAccount("123testing");
    }
    @Test
    public void equals() {

        AccRenameCommand renamePersonalAccountCommand = new AccRenameCommand("PERSONAL", "12345");
        AccRenameCommand renameProjectAccountCommand = new AccRenameCommand("PROJECT", "54321");

        // same object -> returns true
        assertTrue(renamePersonalAccountCommand.equals(renamePersonalAccountCommand));

        // same values -> returns true
        AccRenameCommand renamePersonalAccountCommandCopy = new AccRenameCommand("PERSONAL",
                "12345");
        assertTrue(renamePersonalAccountCommand.equals(renamePersonalAccountCommandCopy));

        // different types -> returns false
        assertFalse(renamePersonalAccountCommand.equals(1));

        // null -> returns false
        assertFalse(renamePersonalAccountCommand.equals(null));

        // different expenditure -> returns false
        assertFalse(renamePersonalAccountCommand.equals(renameProjectAccountCommand));
    }
}
