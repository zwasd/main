package seedu.saveit.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.saveit.commons.core.LogsCenter;
import seedu.saveit.model.expenditure.BaseExp;

/**
 * Panel containing the list of expenditures.
 */
public class ExpenditureListPanel extends UiPart<Region> {
    private static final String FXML = "ExpenditureListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ExpenditureListPanel.class);

    @FXML
    private ListView<BaseExp> expenditureListView;

    public ExpenditureListPanel(ObservableList<BaseExp> expenditureList) {
        super(FXML);
        expenditureListView.setItems(expenditureList);
        expenditureListView.setCellFactory(listView -> new ExpenditureListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Expenditure} using a {@code ExpenditureCard}.
     */
    class ExpenditureListViewCell extends ListCell<BaseExp> {
        @Override
        protected void updateItem(BaseExp baseExp, boolean empty) {
            super.updateItem(baseExp, empty);

            if (empty || baseExp == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(baseExp.getUiCard(getIndex() + 1).getRoot());
            }
        }
    }

}
