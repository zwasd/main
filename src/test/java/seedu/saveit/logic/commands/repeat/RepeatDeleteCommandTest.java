package seedu.saveit.logic.commands.repeat;

import org.junit.jupiter.api.Test;
import seedu.saveit.logic.commands.expenditure.ExpAddCommand;
import seedu.saveit.model.Model;
import seedu.saveit.model.ModelManager;
import seedu.saveit.model.UserPrefs;

import static seedu.saveit.testutil.Assert.assertThrows;
import static seedu.saveit.testutil.TypicalAccounts.getTypicalAccountList;

public class RepeatDeleteCommandTest {

    private Model model = new ModelManager(getTypicalAccountList(), new UserPrefs());

    @Test
    public void constructor_nullExpenditure_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ExpAddCommand(null));
    }

    //TODO ADD MORE TEST CASE


}
