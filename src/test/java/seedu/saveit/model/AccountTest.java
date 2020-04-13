package seedu.saveit.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_DATE_MRT;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_TAG_BUS;
import static seedu.saveit.testutil.Assert.assertThrows;
import static seedu.saveit.testutil.TypicalAccounts.getTypicalAccounts;
import static seedu.saveit.testutil.TypicalExpenditures.ALICE;

import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.saveit.model.expenditure.Expenditure;
import seedu.saveit.model.expenditure.Repeat;
import seedu.saveit.testutil.ExpenditureBuilder;

public class AccountTest {

    private final Account account = new Account();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), account.getExpenditureList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> account.resetData(null));
    }

     @Test
     public void resetData_withValidReadOnlyAccount_replacesData() {
         Account newData = getTypicalAccounts();
         account.resetData(newData);
         assertEquals(newData.getExpenditureList(), account.getExpenditureList());
         assertEquals(newData.getRepeatList(), account.getRepeatList());
     }

    @Test
    public void hasExpenditure_nullExpenditure_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> account.hasExpenditure(null));
    }

    @Test
    public void hasExpenditure_expenditureNotInAddressBook_returnsFalse() {
        assertFalse(account.hasExpenditure(ALICE));
    }

    @Test
    public void hasExpenditure_expenditureInAddressBook_returnsTrue() {
        account.addExpenditure(ALICE);
        assertTrue(account.hasExpenditure(ALICE));
    }

    @Test
    public void hasExpenditure_expenditureWithSameDifferentDate_returnsFalse() {
        account.addExpenditure(ALICE);
        Expenditure editedAlice = new ExpenditureBuilder(ALICE).withDate(VALID_DATE_MRT).withTag(VALID_TAG_BUS)
                .build();
        assertFalse(account.hasExpenditure(editedAlice));
    }

    @Test
    public void getExpenditureList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> account.getExpenditureList().remove(0));
    }

    @Test
    public void copyAccountWithNewName_sameData() {
        Account personal = new Account("personal");
        Account personal1 = personal.copyAccountWithNewName("personal1");

        assertEquals(personal1.getExpenditureList(), personal.getExpenditureList());
        assertEquals(personal1.getRepeatList(), personal.getRepeatList());
    }

    @Test
    public void copyAccountWithNewName_notEquals() {
        Account personal = new Account("personal");
        Account personal1 = personal.copyAccountWithNewName("personal1");

        assertNotEquals(personal, personal1);
    }

    @Test
    public void copyAccountWithNewName_oldNameUnchanged() {
        Account personal = new Account("personal");
        Account personal1 = personal.copyAccountWithNewName("personal1");

        assertEquals(personal1.getAccountName(), "personal1");
        assertEquals(personal.getAccountName(), "personal");
    }

        /**
         * A stub ReadOnlyAccount whose expenditures list can violate interface constraints.
         */
    private static class AccountStub implements ReadOnlyAccount {
        private final ObservableList<Expenditure> expenditures = FXCollections.observableArrayList();
        private final ObservableList<Repeat> repeats = FXCollections.observableArrayList();

        AccountStub(Collection<Expenditure> expenditures) {
            this.expenditures.setAll(expenditures);
        }

        @Override
        public ObservableList<Expenditure> getExpenditureList() {
            return expenditures;
        }

        @Override
        public ObservableList<Repeat> getRepeatList() {
            return repeats;
        }
    }

}
