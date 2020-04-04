package seedu.saveit.logic.commands.repeat;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.saveit.testutil.Assert.assertThrows;
import static seedu.saveit.testutil.TypicalAccounts.getTypicalAccountList;

import org.junit.jupiter.api.Test;

import seedu.saveit.logic.commands.expenditure.ExpAddCommand;
import seedu.saveit.model.Model;
import seedu.saveit.model.ModelManager;
import seedu.saveit.model.UserPrefs;
import seedu.saveit.model.expenditure.Expenditure;
import seedu.saveit.testutil.ExpenditureBuilder;


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
