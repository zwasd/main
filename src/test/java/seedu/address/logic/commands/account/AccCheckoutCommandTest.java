package seedu.address.logic.commands.account;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Account;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAccounts.getTypicalAccountList;

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


