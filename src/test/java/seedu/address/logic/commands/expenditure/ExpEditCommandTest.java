package seedu.address.logic.commands.expenditure;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INFO_BOB;
// import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
// import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
// import static seedu.address.logic.commands.CommandTestUtil.showExpenditureAtIndex;
import static seedu.address.testutil.TypicalAccounts.getTypicalAccountList;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EXPENDITURE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EXPENDITURE;
// import static seedu.address.testutil.TypicalExpenditures.getTypicalAccount;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.account.AccClearCommand;
import seedu.address.logic.commands.expenditure.ExpEditCommand.EditExpenditureDescriptor;
// import seedu.address.model.AccountList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
// import seedu.address.model.expenditure.Expenditure;
import seedu.address.testutil.EditExpenditureDescriptorBuilder;
// import seedu.address.testutil.ExpenditureBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand)
 * and unit tests for ExpEditCommand.
 */
public class ExpEditCommandTest {

    private Model model = new ModelManager(getTypicalAccountList(), new UserPrefs());

    // TODO: update test case
    // @Test
    // public void execute_allFieldsSpecifiedUnfilteredList_success() {
    //     Expenditure editedExpenditure = new ExpenditureBuilder().build();
    //     EditExpenditureDescriptor descriptor =
    //             new EditExpenditureDescriptorBuilder(editedExpenditure).build();
    //     ExpEditCommand expEditCommand = new ExpEditCommand(INDEX_FIRST_EXPENDITURE, descriptor);

    //     String expectedMessage = String.format(ExpEditCommand.MESSAGE_EDIT_EXPENDITURE_SUCCESS, editedExpenditure);

    //     Model expectedModel = new ModelManager(new AccountList(model.getAccountList()), new UserPrefs());
    //     expectedModel.setExpenditure(model.getFilteredExpenditureList().get(0), editedExpenditure);

    //     assertCommandSuccess(expEditCommand, model, expectedMessage, expectedModel);
    // }

    // TODO: update test case
    // @Test
    // public void execute_someFieldsSpecifiedUnfilteredList_success() {
    //     Index indexLastExpenditure = Index.fromOneBased(model.getFilteredExpenditureList().size());
    //     Expenditure lastExpenditure = model.getFilteredExpenditureList().get(indexLastExpenditure.getZeroBased());

    //     ExpenditureBuilder expenditureInList = new ExpenditureBuilder(lastExpenditure);
    //     Expenditure editedExpenditure = expenditureInList.withInfo(VALID_INFO_BOB)
    //             .withTags(VALID_TAG_HUSBAND).build();

    //     EditExpenditureDescriptor descriptor = new EditExpenditureDescriptorBuilder().withInfo(VALID_INFO_BOB)
    //             .withTags(VALID_TAG_HUSBAND).build();
    //     ExpEditCommand expEditCommand = new ExpEditCommand(indexLastExpenditure, descriptor);

    //     String expectedMessage = String.format(ExpEditCommand.MESSAGE_EDIT_EXPENDITURE_SUCCESS, editedExpenditure);

    //     Model expectedModel = new ModelManager(new AccountList(model.getAccountList()), new UserPrefs());
    //     expectedModel.setExpenditure(lastExpenditure, editedExpenditure);

    //     assertCommandSuccess(expEditCommand, model, expectedMessage, expectedModel);
    // }

    // TODO: update test case
    // @Test
    // public void execute_noFieldSpecifiedUnfilteredList_success() {

    //     ExpEditCommand expEditCommand = new ExpEditCommand(INDEX_FIRST_EXPENDITURE,
    //             new EditExpenditureDescriptor());
    //     Expenditure editedExpenditure = model.getFilteredExpenditureList()
    //             .get(INDEX_FIRST_EXPENDITURE.getZeroBased());

    //     String expectedMessage = String.format(ExpEditCommand.MESSAGE_EDIT_EXPENDITURE_SUCCESS, editedExpenditure);

    //     Model expectedModel = new ModelManager(new AccountList(model.getAccountList()), new UserPrefs());

    //     assertCommandSuccess(expEditCommand, model, expectedMessage, expectedModel);
    // }


    // TODO; update test case
    // @Test
    // public void execute_filteredList_success() {
    //     showExpenditureAtIndex(model, INDEX_FIRST_EXPENDITURE);

    //     Expenditure expenditureInFilteredList = model.getFilteredExpenditureList()
    //             .get(INDEX_FIRST_EXPENDITURE.getZeroBased());
    //     Expenditure editedExpenditure = new ExpenditureBuilder(expenditureInFilteredList)
    //             .withInfo(VALID_INFO_BOB).build();
    //     ExpEditCommand expEditCommand = new ExpEditCommand(INDEX_FIRST_EXPENDITURE,
    //             new EditExpenditureDescriptorBuilder().withInfo(VALID_INFO_BOB).build());

    //     String expectedMessage = String.format(ExpEditCommand.MESSAGE_EDIT_EXPENDITURE_SUCCESS, editedExpenditure);

    //     Model expectedModel = new ModelManager(new AccountList(model.getAccountList()), new UserPrefs());
    //     expectedModel.setExpenditure(model.getFilteredExpenditureList().get(0), editedExpenditure);

    //     assertCommandSuccess(expEditCommand, model, expectedMessage, expectedModel);
    // }

    // TODO: update test case
    // @Test
    // public void execute_duplicateExpenditureUnfilteredList_failure() {
    //     Expenditure firstExpenditure = model.getFilteredExpenditureList()
    //             .get(INDEX_FIRST_EXPENDITURE.getZeroBased());
    //     EditExpenditureDescriptor descriptor = new EditExpenditureDescriptorBuilder(firstExpenditure).build();
    //     ExpEditCommand expEditCommand = new ExpEditCommand(INDEX_SECOND_EXPENDITURE, descriptor);
    //     assertCommandFailure(expEditCommand, model, ExpEditCommand.MESSAGE_DUPLICATE_EXPENDITURE);
    // }

    // TODO: update test case
    // @Test
    // public void execute_duplicateExpenditureFilteredList_failure() {
    //     showExpenditureAtIndex(model, INDEX_FIRST_EXPENDITURE);

    //     // edit expenditure in filtered list into a duplicate in address book
    //     Expenditure expenditureInList = model.getAccountList().getExpenditureList()
    //             .get(INDEX_SECOND_EXPENDITURE.getZeroBased());
    //     ExpEditCommand expEditCommand = new ExpEditCommand(INDEX_FIRST_EXPENDITURE,
    //             new EditExpenditureDescriptorBuilder(expenditureInList).build());

    //     assertCommandFailure(expEditCommand, model, ExpEditCommand.MESSAGE_DUPLICATE_EXPENDITURE);
    // }

    @Test
    public void execute_invalidExpenditureIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredExpenditureList().size() + 1);
        EditExpenditureDescriptor descriptor = new EditExpenditureDescriptorBuilder()
                .withInfo(VALID_INFO_BOB).build();
        ExpEditCommand expEditCommand = new ExpEditCommand(outOfBoundIndex, descriptor);
        assertCommandFailure(expEditCommand, model, Messages.MESSAGE_INVALID_EXPENDITURE_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    // TODO: update test case
    // @Test
    // public void execute_invalidExpenditureIndexFilteredList_failure() {
    //     showExpenditureAtIndex(model, INDEX_FIRST_EXPENDITURE);
    //     Index outOfBoundIndex = INDEX_SECOND_EXPENDITURE;
    //     // ensures that outOfBoundIndex is still in bounds of address book list
    //     assertTrue(outOfBoundIndex.getZeroBased() < model.getAccountList().getExpenditureList().size());

    //     ExpEditCommand expEditCommand = new ExpEditCommand(outOfBoundIndex,
    //             new EditExpenditureDescriptorBuilder().withInfo(VALID_INFO_BOB).build());

    //     assertCommandFailure(expEditCommand, model, Messages.MESSAGE_INVALID_EXPENDITURE_DISPLAYED_INDEX);
    // }

    @Test
    public void equals() {
        final ExpEditCommand standardCommand = new ExpEditCommand(INDEX_FIRST_EXPENDITURE, DESC_AMY);

        // same values -> returns true
        EditExpenditureDescriptor copyDescriptor = new ExpEditCommand
                .EditExpenditureDescriptor(DESC_AMY);
        ExpEditCommand commandWithSameValues = new ExpEditCommand(INDEX_FIRST_EXPENDITURE, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new AccClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new ExpEditCommand(INDEX_SECOND_EXPENDITURE, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new ExpEditCommand(INDEX_FIRST_EXPENDITURE, DESC_BOB)));
    }

}
