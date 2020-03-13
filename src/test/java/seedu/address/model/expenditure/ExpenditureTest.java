package seedu.address.model.expenditure;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INFO_BOB;
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
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // different amount -> returns false
        Expenditure editedAlice = new PersonBuilder(ALICE)
                .withAmount(3.00).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // different info -> returns false
        editedAlice = new PersonBuilder(ALICE).withInfo(VALID_INFO_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // same info, same id, different attributes -> returns true
        editedAlice = new PersonBuilder(ALICE).withAmount(VALID_AMOUNT_BOB).withDate(VALID_DATE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // same info, same amount, different attributes -> returns true
        // editedAlice = new PersonBuilder(ALICE).withId(VALID_ID_BOB).withAddress(VALID_ADDRESS_BOB)
        //         .withTags(VALID_TAG_HUSBAND).build();
        // assertTrue(ALICE.isSamePerson(editedAlice));

        // same info, same id, same amount, different attributes -> returns true
        editedAlice = new PersonBuilder(ALICE).withDate(VALID_DATE_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSamePerson(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
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

        // different info -> returns false
        Expenditure editedAlice = new PersonBuilder(ALICE).withInfo(VALID_INFO_BOB).build();
        assertFalse(ALICE.equals(editedAlice));


        // different email -> returns false
        // editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        // assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new PersonBuilder(ALICE).withDate(VALID_DATE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
