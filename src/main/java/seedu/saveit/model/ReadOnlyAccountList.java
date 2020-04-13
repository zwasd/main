package seedu.saveit.model;

import java.time.LocalDate;
import java.util.Map;

/**
 * Unmodifiable view of an account list.
 */
public interface ReadOnlyAccountList extends ReadOnlyAccount {
    /**
     * Returns a list of accounts.
     */
    Map<String, Account> getAccounts();

    /**
     * @return a string which consists of all account name
     */
    String listAllNames();

    /**
     * Gets the date of expenditures shown.
     * @return The LocalDate date.
     */
    LocalDate getActiveDate();

    /**
     * Returns the name of the active account in the account list.
     */
    String getActiveAccountName();
}
