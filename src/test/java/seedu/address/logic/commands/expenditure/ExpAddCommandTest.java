package seedu.address.logic.commands.expenditure;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
// import seedu.address.model.Account;
// import seedu.address.model.AccountList;
import seedu.address.model.Account;
import seedu.address.model.Budget;
import seedu.address.model.Model;
// import seedu.address.model.ReadOnlyAccount;
import seedu.address.model.ReadOnlyAccountList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.ReportableAccount;
import seedu.address.model.expenditure.Expenditure;
import seedu.address.model.expenditure.Repeat;
import seedu.address.testutil.ExpenditureBuilder;

public class ExpAddCommandTest {

    @Test
    public void constructor_nullExpenditure_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ExpAddCommand(null));
    }

    @Test
    public void execute_expenditureAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingExpenditureAdded modelStub = new ModelStubAcceptingExpenditureAdded();
        Expenditure validExpenditure = new ExpenditureBuilder().build();

        CommandResult commandResult = new ExpAddCommand(validExpenditure).execute(modelStub);

        assertEquals(String.format(ExpAddCommand.MESSAGE_SUCCESS, validExpenditure), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validExpenditure), modelStub.expendituresAdded);
    }

    @Test
    public void execute_duplicateExpenditure_throwsCommandException() {
        Expenditure validExpenditure = new ExpenditureBuilder().build();
        ExpAddCommand expAddCommand = new ExpAddCommand(validExpenditure);
        ModelStub modelStub = new ModelStubWithExpenditure(validExpenditure);

        assertThrows(CommandException.class, ExpAddCommand.MESSAGE_DUPLICATE_EXPENDITURE, () ->
                expAddCommand.execute(modelStub));
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

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addExpenditure(Expenditure expenditure) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addRepeat(Repeat repeat) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void deleteRepeat(Repeat target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAccountList(ReadOnlyAccountList newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAccountList getAccountList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasExpenditure(Expenditure expenditure) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteExpenditure(Expenditure target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setExpenditure(Expenditure target, Expenditure editedExpenditure) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setRepeat(Repeat target, Repeat editedRepeat) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Expenditure> getFilteredExpenditureList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Repeat> getFilteredRepeatList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredExpenditureList(Predicate<Expenditure> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean updateActiveAccount(String accountName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void clearActiveAccount() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String renameAccount(String oldName, String newName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String deleteAccount(String name) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void addAccount(Account account) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public ReportableAccount getReportableAccount() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateActiveDate(LocalDate date) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setBudget(Budget budget) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single expenditure.
     */
    private class ModelStubWithExpenditure extends ModelStub {
        private final Expenditure expenditure;

        ModelStubWithExpenditure(Expenditure expenditure) {
            requireNonNull(expenditure);
            this.expenditure = expenditure;
        }

        @Override
        public boolean hasExpenditure(Expenditure expenditure) {
            requireNonNull(expenditure);
            return this.expenditure.equals(expenditure);
        }
    }

    /**
     * A Model stub that always accept the expenditure being added.
     */
    private class ModelStubAcceptingExpenditureAdded extends ModelStub {
        final ArrayList<Expenditure> expendituresAdded = new ArrayList<>();

        @Override
        public boolean hasExpenditure(Expenditure expenditure) {
            requireNonNull(expenditure);
            return expendituresAdded.stream().anyMatch(expenditure::equals);
        }

        @Override
        public void addExpenditure(Expenditure expenditure) {
            requireNonNull(expenditure);
            expendituresAdded.add(expenditure);
        }

        @Override
        public ReadOnlyAccountList getAccountList() {
            // return new AccountList();
            return null;
        }
    }

}
