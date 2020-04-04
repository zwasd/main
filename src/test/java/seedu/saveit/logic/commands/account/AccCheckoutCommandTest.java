package seedu.saveit.logic.commands.account;

import static seedu.saveit.testutil.Assert.assertThrows;
import static seedu.saveit.testutil.TypicalAccounts.getTypicalAccountList;

import org.junit.jupiter.api.Test;

import seedu.saveit.logic.commands.exceptions.CommandException;
import seedu.saveit.model.Model;
import seedu.saveit.model.ModelManager;
import seedu.saveit.model.UserPrefs;


public class AccCheckoutCommandTest {

    private Model model = new ModelManager(getTypicalAccountList(), new UserPrefs());

    @Test
    public void constructor_nullAccountName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AccCheckoutCommand(null));
    }

    @Test
    public void constructor_nonExistentAccountName_throwsCommandException() {
        assertThrows(CommandException.class, () -> new AccCheckoutCommand(".").execute(model));
    }


}


