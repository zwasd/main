package seedu.address.model.expenditure;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class ExpenditureTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Expenditure expenditure = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> expenditure.getTags().remove(0));
    }

    @Test
    public void isSameExpenditure() {
        Expenditure aliceCopy = new PersonBuilder(ALICE).build();
        assertTrue(ALICE.isSameExpenditure(aliceCopy));
    }


    @Test
    public void equals() {
        // different object but same fields -> true
        Expenditure aliceCopy = new PersonBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different expenditure -> returns false
        assertFalse(ALICE.equals(BOB));

        // different object and fields-> returns false
        Expenditure editedAlice = new PersonBuilder(ALICE).withAmount(3.00).build();
        assertFalse(ALICE.equals(editedAlice));

        // different object and fields -> returns false
        editedAlice = new PersonBuilder(ALICE).withDate(VALID_DATE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different object and fields-> returns false
        editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
