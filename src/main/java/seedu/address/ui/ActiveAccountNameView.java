package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * This is for active account name label.
 */
public class ActiveAccountNameView extends UiPart<Region> {

    private static final String FXML = "ActiveAccountNameView.fxml";

    //TODO: I need to store this active account name;
    private String activeAccountName;

    @FXML
    private Label activeName;

    public ActiveAccountNameView(String name) {
        super(FXML);
        setActiveAccountName(name);
    }

    public void setActiveAccountName(String activeAccountName) {
        this.activeAccountName = activeAccountName;
        this.activeName.setText(activeAccountName);
    }

    public String getActiveAccountName() {
        return activeAccountName;
    }
}
