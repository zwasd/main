package seedu.address.model.expenditure;

import javafx.scene.layout.Region;
import seedu.address.model.tag.Tag;
import seedu.address.ui.UiPart;

import java.time.LocalDate;

public abstract class BaseExp {
    Info info;
    Amount amount;
    Tag tag;

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
