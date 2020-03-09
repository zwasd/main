package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

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
        assertThrows(NullPointerException.class, () -> seedu.address.model.person.Date.isValidDate(null));

        // invalid date
        assertFalse(seedu.address.model.person.Date.isValidDate("")); // empty string
        assertFalse(seedu.address.model.person.Date.isValidDate(" ")); // spaces only

        // valid date
        assertTrue(seedu.address.model.person.Date.isValidDate("2019-09-11"));
        assertTrue(seedu.address.model.person.Date.isValidDate("2019-09-11"));
        assertTrue(seedu.address.model.person.Date.isValidDate("2019-09-11"));
    }
}
