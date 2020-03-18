package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.expenditure.Expenditure;

/**
 * Unmodifiable view of an account.
 */
public interface ReadOnlyAccount {

    /**
     * Returns an unmodifiable view of the expenditures list.
     * This list will not contain any duplicate expenditures.
     */
    ObservableList<Expenditure> getExpenditureList();

}
