package seedu.address.model;

import javafx.scene.layout.Region;
import seedu.address.ui.UiPart;

public abstract class HasUiCard {
    public abstract UiPart<Region> getUiCard(int displayedNumber);
}
