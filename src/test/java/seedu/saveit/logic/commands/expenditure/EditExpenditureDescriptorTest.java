package seedu.saveit.logic.commands.expenditure;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static seedu.saveit.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.saveit.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_AMOUNT_MRT;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_DATE_MRT;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_INFO_MRT;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_TAG_BUS;

import org.junit.jupiter.api.Test;

import seedu.saveit.logic.commands.expenditure.ExpEditCommand.EditExpenditureDescriptor;
import seedu.saveit.testutil.EditExpenditureDescriptorBuilder;

public class EditExpenditureDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditExpenditureDescriptor descriptorWithSameValues = new EditExpenditureDescriptor(DESC_AMY);
        assertTrue(DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY.equals(DESC_AMY));

        // null -> returns false
        assertFalse(DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY.equals(DESC_BOB));

        // different info -> returns false
        EditExpenditureDescriptor editedAmy = new EditExpenditureDescriptorBuilder(DESC_AMY)
                .withInfo(VALID_INFO_MRT).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different amount -> returns false
        editedAmy = new EditExpenditureDescriptorBuilder(DESC_AMY).withAmount(VALID_AMOUNT_MRT).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different date -> returns false
        editedAmy = new EditExpenditureDescriptorBuilder(DESC_AMY).withDate(VALID_DATE_MRT).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditExpenditureDescriptorBuilder(DESC_AMY).withTag(VALID_TAG_BUS).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }
}
