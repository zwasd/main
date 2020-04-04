package seedu.saveit.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.saveit.commons.core.GuiSettings;
import seedu.saveit.logic.commands.CommandResult;
import seedu.saveit.logic.commands.ReportCommandResult;
import seedu.saveit.logic.commands.exceptions.CommandException;
import seedu.saveit.logic.parser.exceptions.ParseException;

import seedu.saveit.model.ReadOnlyAccountList;
import seedu.saveit.model.expenditure.BaseExp;
import seedu.saveit.model.expenditure.Expenditure;
import seedu.saveit.model.expenditure.Repeat;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    ReportCommandResult executeReportWindowCommand(String commandText) throws CommandException, ParseException;

    /**
     * Returns the Account.
     *
     * @see seedu.saveit.model.Model#getAccountList()
     */
    ReadOnlyAccountList getAddressBook();

    /** Returns an unmodifiable view of the filtered list of expenditures */
    ObservableList<Expenditure> getFilteredExpenditureList();

    /** Returns an unmodifiable view of the filtered list of expenditures */
    ObservableList<Repeat> getFilteredRepeatList();

    ObservableList<BaseExp> getFilteredBaseExpList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
