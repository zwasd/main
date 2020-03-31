package seedu.address.model.expenditure;

import java.time.LocalDate;

import javafx.scene.layout.Region;
import seedu.address.model.tag.Tag;
import seedu.address.ui.UiPart;

/**
 * A base class for the Expenditure and Repeat classes
 */
public abstract class BaseExp {
    protected Info info;
    protected Amount amount;
    protected Tag tag;

    public abstract UiPart<Region> getUiCard(int displayedNumber);

    public abstract boolean isOn(LocalDate localDate);

    public Info getInfo() {
        return info;
    }

    public Amount getAmount() {
        return amount;
    }

    public Tag getTag() {
        return tag;
    }

}
