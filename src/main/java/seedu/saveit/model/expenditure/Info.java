package seedu.saveit.model.expenditure;

import static java.util.Objects.requireNonNull;
import static seedu.saveit.commons.util.AppUtil.checkArgument;

import seedu.saveit.commons.util.StringUtil;

/**
 * Represents a Expenditure's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidInfo(String)}
 */
public class Info {

    public static final String MESSAGE_CONSTRAINTS =
            "Infos should only contain alphanumeric characters and spaces, and it should not be blank";


    public final String fullInfo;

    /**
     * Constructs a {@code Info}.
     *
     * @param info A valid name.
     */
    public Info(String info) {
        requireNonNull(info);
        checkArgument(isValidInfo(info), MESSAGE_CONSTRAINTS);
        fullInfo = info;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidInfo(String test) {
        requireNonNull(test);
        return !test.trim().equals("") && StringUtil.isAlphanumeric(test);
    }


    @Override
    public String toString() {
        return fullInfo;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Info // instanceof handles nulls
                && fullInfo.equals(((Info) other).fullInfo)); // state check
    }

    @Override
    public int hashCode() {
        return fullInfo.hashCode();
    }

}
