package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.HasUiCard;
import seedu.address.model.expenditure.Expenditure;

/**
 * Panel containing the list of expenditures.
 */
public class ExpenditureListPanel extends UiPart<Region> {
    private static final String FXML = "ExpenditureListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ExpenditureListPanel.class);

    @FXML
    private ListView<HasUiCard> expenditureListView;

    public ExpenditureListPanel(ObservableList<HasUiCard> expenditureList) {
        super(FXML);
        expenditureListView.setItems(expenditureList);
        expenditureListView.setCellFactory(listView -> new ExpenditureListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Expenditure} using a {@code ExpenditureCard}.
     */
    class ExpenditureListViewCell extends ListCell<HasUiCard> {
        @Override
        protected void updateItem(HasUiCard hasUiCard, boolean empty) {
            super.updateItem(hasUiCard, empty);

            if (empty || hasUiCard == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(hasUiCard.getUiCard(getIndex() + 1).getRoot());
            }
        }
    }

}
