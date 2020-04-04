package seedu.saveit.logic.commands.account;

import static seedu.saveit.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.saveit.testutil.TypicalAccounts.getTypicalAccountList;
// import static seedu.saveit.testutil.TypicalExpenditures.getTypicalAccount;

import org.junit.jupiter.api.Test;

import seedu.saveit.model.Model;
import seedu.saveit.model.ModelManager;
import seedu.saveit.model.UserPrefs;

public class AccClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new AccClearCommand(), model, AccClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalAccountList(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAccountList(), new UserPrefs());
        expectedModel.clearActiveAccount();

        assertCommandSuccess(new AccClearCommand(), model, AccClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
