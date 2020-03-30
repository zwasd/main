package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.expenditure.Repeat;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

/**
 * An UI component that displays information of a {@code Repeat}.
 */
public class RepeatCard extends UiPart<Region> {

    private static final String FXML = "RepeatListCard.fxml";
    private static final DateTimeFormatter DTF = DateTimeFormatter.ISO_DATE;
    private static final DecimalFormat TWO_DP = new DecimalFormat("0.00");

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on Account level 4</a>
     */

    public final Repeat repeat;

    @FXML
    private HBox repeatCardPane;
    @FXML
    private Label repeatInfo;
    @FXML
    private Label repeatNumber;
    @FXML
    private Label repeatAmount;
    @FXML
    private Label repeatStartDate;
    @FXML
    private Label repeatEndDate;
    @FXML
    private FlowPane repeatTags;

    public RepeatCard(Repeat repeat, int displayedNumber) {
        super(FXML);

        this.repeat = repeat;
        repeatNumber.setText(displayedNumber + ". ");
        repeatInfo.setText(repeat.getInfo().fullInfo);
        repeatAmount.setText("$" + TWO_DP.format(repeat.getAmount().value));
        repeatStartDate.setText(repeat.getStartDate().localDate.format(DTF));
        repeatStartDate.setText(repeat.getStartDate().localDate.format(DTF));
        repeatTags.getChildren().add(new Label(repeat.getTag().tagName));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RepeatCard)) {
            return false;
        }

        // state check
        RepeatCard card = (RepeatCard) other;
        return repeatNumber.getText().equals(card.repeatNumber.getText())
                && repeat.equals(card.repeat);
    }
}
