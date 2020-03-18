package seedu.address.model.expenditure;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Represents a Expenditure's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class Date {

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_DATE;

    public static final String MESSAGE_CONSTRAINTS = "Date should be in a format of (YYYY-MM-DD), "
                                                        + "and it should not be blank";

    public final String value;

    public final LocalDate localDate;

    /**
     * Constructs an {@code Date}.
     *
     * @param date A valid date.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        value = date;
        localDate = LocalDate.parse(date, FORMATTER);
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidDate(String test) {
        try {
            LocalDate date = LocalDate.parse(test, FORMATTER);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                && value.equals(((Date) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * returns true if the first date is equal to or comes before the second date
     * @param d1 the first date
     * @param d2 the second date
     * @return true if the first date is equal to or comes before the second date
     */
    public static boolean isEqualOrBefore(Date d1, Date d2) {
        return d1.value.equals(d2.value) || d1.localDate.isBefore(d2.localDate);
    }

}
