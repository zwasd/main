package seedu.saveit.model.expenditure;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.saveit.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Date(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDate));
    }

    @Test
    public void isValidDate() {
        // null date
        assertThrows(NullPointerException.class, () -> seedu.saveit.model.expenditure.Date.isValidDate(null));

        // invalid date
        assertFalse(seedu.saveit.model.expenditure.Date.isValidDate("")); // empty string
        assertFalse(seedu.saveit.model.expenditure.Date.isValidDate(" ")); // spaces only

        // valid date
        assertTrue(seedu.saveit.model.expenditure.Date.isValidDate("2019-09-11"));
        assertTrue(seedu.saveit.model.expenditure.Date.isValidDate("2019-09-11"));
        assertTrue(seedu.saveit.model.expenditure.Date.isValidDate("2019-09-11"));
    }
}
