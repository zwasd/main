package seedu.address.model;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.expenditure.Expenditure;
import seedu.address.model.expenditure.Repeat;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Expenditure> PREDICATE_SHOW_ALL_EXPENDITURES = unused -> true;
    Predicate<Repeat> PREDICATE_SHOW_ALL_REPEATS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code account}.
     */
    void setAccountList(ReadOnlyAccountList accountList);

    /** Returns the Account */
    ReadOnlyAccountList getAccountList();

    /**
     * Returns true if a expenditure with the same identity as {@code expenditure} exists in the internal list.
     */
    boolean hasExpenditure(Expenditure expenditure);

    /**
     * Deletes the given expenditure.
     * The expenditure must exist in the internal list.
     */
    void deleteExpenditure(Expenditure target);

    /**
     * Adds the given expenditure.
     * {@code expenditure} must not already exist in the internal list.
     */
    void addExpenditure(Expenditure expenditure);

    /**
     * Adds the given repeat.
     */
    void addRepeat(Repeat repeat);

    /**
     * Replaces the given expenditure {@code target} with {@code editedExpenditure}.
     * {@code target} must exist in the internal list.
     * The expenditure identity of {@code editedExpenditure} must not be the same as
     * another existing expenditure in the internal list.
     */
    void setExpenditure(Expenditure target, Expenditure editedExpenditure);

    /** Returns an unmodifiable view of the filtered expenditure list */
    ObservableList<Expenditure> getFilteredExpenditureList();

    /** Returns an unmodifiable view of the filtered repeat list */
    ObservableList<Repeat> getFilteredRepeatList();

    /**
     * Updates the filter of the filtered expenditure list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredExpenditureList(Predicate<Expenditure> predicate);

    /**
     * Rename the account's name.
     * @param oldName target account's current name
     * @param newName target account's new name
     */
    void renameAccount(String oldName, String newName);

    /**
     * Delete an account from the accountList.
     * @param name the target account name
     */
    void deleteAccount(String name);

    void addAccount(Account account) throws CommandException;

    boolean updateActiveAccount(String accountName);

    void clearActiveAccount();

    ReportableAccount getReportableAccount();

    void updateActiveDate(LocalDate date);

}
