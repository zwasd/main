package seedu.address.ui;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * This is for active account name label.
 */
public class ActiveNameAndDateView extends UiPart<Region> {

    private static final String FXML = "ActiveNameAndDate.fxml";

    //TODO: store this active account name in jason.
    // need change.
    private String activeAccountName = "whatever";

    private String activeAccountDate;

    @FXML
    private Label activeName;

    @FXML
    private Label activeDate;

    public ActiveNameAndDateView() {
        super(FXML);
        setActiveAccountName(this.activeAccountName);
        setActiveDate(LocalDate.now().toString());
    }

    public void setActiveAccountName(String activeAccountName) {
        this.activeAccountName = activeAccountName;
        this.activeName.setText(activeAccountName);
    }

    public void setActiveDate(String activeAccountDate) {
        this.activeAccountDate = activeAccountDate;
        this.activeDate.setText(activeAccountDate);
    }

}
