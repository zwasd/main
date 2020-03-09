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
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> Date.isValidAddress(null));

        // invalid addresses
        assertFalse(Date.isValidAddress("")); // empty string
        assertFalse(Date.isValidAddress(" ")); // spaces only

        // valid addresses
        assertTrue(Date.isValidAddress("Blk 456, Den Road, #01-355"));
        assertTrue(Date.isValidAddress("-")); // one character
        assertTrue(Date.isValidAddress("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long address
    }
}
