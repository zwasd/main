package seedu.address.logic.commands.repeat;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.expenditure.ExpAddCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAccounts.getTypicalAccountList;

public class RepeatDeleteCommandTest {

    private Model model = new ModelManager(getTypicalAccountList(), new UserPrefs());

    @Test
    public void constructor_nullExpenditure_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ExpAddCommand(null));
    }

    //TODO ADD MORE TEST CASE


}
