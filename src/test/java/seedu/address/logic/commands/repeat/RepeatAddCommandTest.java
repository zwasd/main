package seedu.address.logic.commands.repeat;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.expenditure.ExpAddCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.expenditure.Expenditure;
import seedu.address.testutil.ExpenditureBuilder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAccounts.getTypicalAccountList;

public class RepeatAddCommandTest {
    private Model model = new ModelManager(getTypicalAccountList(), new UserPrefs());


    @Test
    public void constructor_nullRepeat_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RepeatAddCommand(null));
    }

    @Test
    public void equals() {
        Expenditure alice = new ExpenditureBuilder().withInfo("Alice").build();
        Expenditure bob = new ExpenditureBuilder().withInfo("Bob").build();
        ExpAddCommand addAliceCommand = new ExpAddCommand(alice);
        ExpAddCommand addBobCommand = new ExpAddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        ExpAddCommand addAliceCommandCopy = new ExpAddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different expenditure -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

}
