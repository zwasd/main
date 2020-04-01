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

    @FXML
    private Label activeName;

    @FXML
    private Label activeDate;

    public ActiveNameAndDateView(String accountName, LocalDate activeDate) {
        super(FXML);
        setActiveAccountName(accountName);
        setActiveDate(activeDate);
    }

    public void setActiveAccountName(String activeAccountName) {
        this.activeName.setText(activeAccountName);
    }

    public void setActiveDate(LocalDate activeAccountDate) {
        this.activeDate.setText(activeAccountDate.toString());
    }

}
