package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class InfoTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Info(null));
    }

    @Test
    public void constructor_invalidInfo_throwsIllegalArgumentException() {
        String invalidInfo = "";
        assertThrows(IllegalArgumentException.class, () -> new Info(invalidInfo));
    }

    @Test
    public void isValidInfo() {
        // null info
        assertThrows(NullPointerException.class, () -> Info.isValidInfo(null));

        // invalid info
        assertFalse(Info.isValidInfo("")); // empty string
        assertFalse(Info.isValidInfo(" ")); // spaces only
        assertFalse(Info.isValidInfo("^")); // only non-alphanumeric characters
        assertFalse(Info.isValidInfo("peter*")); // contains non-alphanumeric characters

        // valid info
        assertTrue(Info.isValidInfo("peter jack")); // alphabets only
        assertTrue(Info.isValidInfo("12345")); // numbers only
        assertTrue(Info.isValidInfo("peter the 2nd")); // alphanumeric characters
        assertTrue(Info.isValidInfo("Capital Tan")); // with capital letters
        assertTrue(Info.isValidInfo("David Roger Jackson Ray Jr 2nd")); // long names
    }
}

