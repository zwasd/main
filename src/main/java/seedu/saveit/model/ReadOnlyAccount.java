package seedu.saveit.model;

import javafx.collections.ObservableList;
import seedu.saveit.model.expenditure.Expenditure;
import seedu.saveit.model.expenditure.Repeat;

/**
 * Unmodifiable view of an account.
 */
public interface ReadOnlyAccount {

    /**
     * Returns an unmodifiable view of the expenditures list.
     * This list will not contain any duplicate expenditures.
     */
    ObservableList<Expenditure> getExpenditureList();

    /**
     * Returns an unmodifiable view of the repeats list.
     */
    ObservableList<Repeat> getRepeatList();
}
