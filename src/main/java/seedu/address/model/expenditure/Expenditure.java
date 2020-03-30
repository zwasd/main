package seedu.address.model.expenditure;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import javafx.scene.layout.Region;
import seedu.address.model.HasUiCard;
import seedu.address.model.tag.Tag;
import seedu.address.ui.ExpenditureCard;
import seedu.address.ui.UiPart;

/**
 * Represents a Expenditure in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Expenditure extends HasUiCard {

    // Identity fields
    private final Info info;
    private final Amount amount;

    // Data fields
    private final Date date;
    private final Tag tag;

    /**
     * Every field must be present and not null.
     */

    public Expenditure(Info info, Amount amount, Date date, Tag tag) {
        requireAllNonNull(info, amount, date, tag);
        this.info = info;
        this.amount = amount;
        this.date = date;
        this.tag = tag;
    }

    public Info getInfo() {
        return info;
    }

    public Amount getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    /**
     * Returns a tag.
     */
    public Tag getTag() {
        return tag;
    }

    /**
     * Returns true if both expenditure refers to the same expenditure object.
     */
    public boolean isSameExpenditure(Expenditure otherExpenditure) {
        if (otherExpenditure == this) {
            return true;
        }

        return false;
    }

    /**
     * Returns true if both expenditures have all same fields.
     * Can be used for testing.
     */
    @Override
    public boolean equals(Object other) {

        if (!(other instanceof Expenditure)) { // short circuit if not same type
            return false;
        } else if (other == this) {
            return true;
        }

        Expenditure otherExpenditure = (Expenditure) other;
        boolean sameInfo = otherExpenditure.info.equals(this.info);
        boolean sameAmt = otherExpenditure.amount.equals(this.amount);
        boolean sameDate = otherExpenditure.date.equals(this.date);
        boolean sameTag = otherExpenditure.tag.equals(this.tag);


        return sameAmt && sameDate && sameInfo && sameTag;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(info, amount, date, tag);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getInfo())
                .append(" Amount: ")
                .append(getAmount())
                .append(" Date: ")
                .append(getDate())
                .append(" Tag: ")
                .append(getTag());
        return builder.toString();
    }

    @Override
    public UiPart<Region> getUiCard(int displayedNumber) {
        return new ExpenditureCard(this, displayedNumber);
    }
}
