package seedu.address.logic.commands.expenditure;

// import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
// import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAccounts.getTypicalAccountList;

import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
// import seedu.address.model.expenditure.Expenditure;
// import seedu.address.testutil.ExpenditureBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code ExpAddCommand}.
 */
public class ExpAddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAccountList(), new UserPrefs());
    }

    // TODO: update test case
    // @Test
    // public void execute_newExpenditure_success() {
    //     Expenditure validExpenditure = new ExpenditureBuilder().withAmount(3.00).withInfo("chicken").build();
    //     Model expectedModel = new ModelManager(model.getAccountList(), new UserPrefs());
    //     expectedModel.addExpenditure(validExpenditure);

    //     assertCommandSuccess(new ExpAddCommand(validExpenditure), model,
    //             String.format(ExpAddCommand.MESSAGE_SUCCESS, validExpenditure), expectedModel);
    // }

    // TODO: update test case
    // @Test
    // public void execute_duplicateExpenditure_throwsCommandException() {
    //     Expenditure expenditureInList = model.getAccountList().getExpenditureList().get(0);
    //     assertCommandFailure(new ExpAddCommand(expenditureInList), model,
    //             ExpAddCommand.MESSAGE_DUPLICATE_EXPENDITURE);
    // }

}
