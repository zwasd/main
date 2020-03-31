package seedu.address.model.expenditure;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AmountTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Amount(null));
    }

    @Test
    public void constructor_invalidAmount_throwsIllegalArgumentException() {
        String invalidAmount = "";
        assertThrows(IllegalArgumentException.class, () -> new Amount(invalidAmount));
    }

    @Test
    public void isValidAmount() {
        // null amount
        assertThrows(NullPointerException.class, () -> Amount.isValidAmount(null));

        // blank amount
        assertFalse(Amount.isValidAmount("")); // empty string
        assertFalse(Amount.isValidAmount(" ")); // spaces only

        // random letters
        assertFalse(Amount.isValidAmount("fjksjd"));

        // not double
        assertFalse(Amount.isValidAmount("3.2.1"));

        // negative values
        assertFalse(Amount.isValidAmount("-2"));
        assertFalse(Amount.isValidAmount(-3));

        // more than 2 decimal point
        assertFalse(Amount.isValidAmount(123.333));
        assertFalse(Amount.isValidAmount("123.112"));

        // valid
        assertTrue(Amount.isValidAmount(123.33));
        assertTrue(Amount.isValidAmount("123.1"));
        assertTrue(Amount.isValidAmount("0"));
    }
}
