package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.budget.Budget;
import seedu.address.model.budget.BudgetMap;
import seedu.address.model.expenditure.BaseExp;
import seedu.address.model.expenditure.Expenditure;
import seedu.address.model.expenditure.Repeat;
import seedu.address.model.expenditure.exceptions.ExpenditureNotFoundException;

/**
 * Manages all accounts of the user.
 */
public class AccountList implements ReadOnlyAccountList, ReadOnlyAccount {
    private Map<String, Account> accounts = new HashMap<>();
    private Account activeAccount; //TODO: make it static ?? (XP)
    private final ObservableList<BaseExp> displayedBaseExpList = FXCollections.observableArrayList();
    private String initialAccountName = "default"; // TODO
    private LocalDate activeDate;
    private int expAddIndex = 0;

    /**
     * Creates an AccountList using the accounts in the {@code toBeCopied}
     */
    public AccountList(ReadOnlyAccountList toBeCopied) {
        resetData(toBeCopied);
        activeAccount = accounts.get(initialAccountName);
        if (accounts.size() == 0) {
            activeAccount = new Account("default");
            accounts.put("default", activeAccount);
        } else if (!accounts.containsKey(initialAccountName)) {
            activeAccount = accounts.values().iterator().next();
        } else {
            activeAccount = accounts.get(initialAccountName);
        }
        activeDate = LocalDate.now();
        resetFromActiveAccount();
    }

    public AccountList(boolean createDefaultAccount) {
        if (createDefaultAccount) {
            activeAccount = new Account("default");
            addAccount(activeAccount);
        }
        activeDate = LocalDate.now();
    }

    //// list overwrite operations


    /**
     * Resets the existing data of this {@code Account} with {@code newData}.
     */
    public void resetData(ReadOnlyAccountList newData) {
        requireNonNull(newData);
        setAccounts(newData.getAccounts());
    }

    private void setAccounts(Map<String, Account> accountHashMap) {
        requireAllNonNull(accountHashMap);
        accounts = new HashMap<>();
        for (Map.Entry<String, Account> entry : accountHashMap.entrySet()) {
            accounts.put(entry.getKey(), entry.getValue());
        }
    }

    //// account-level operations

    public ReportableAccount getReportableAccount() {
        return activeAccount;
    }

    /**
     * Returns true if a account with the same identity as {@code account} exists in the account list.
     */
    public boolean hasAccount(Account account) {
        requireNonNull(account);
        return accounts.containsKey(account.getAccountName());
    }

    /**
     * Returns true if a account with the same account name exists in the account list.
     */
    public boolean hasAccount(String accountName) {
        requireNonNull(accountName);
        return accounts.containsKey(accountName);
    }

    /**
     * Renames account by copying the account data.
     * @param oldName The old account name.
     * @param newName The new account name to be renamed to.
     * @String a string to denote the current active account name.
     */
    public String renameAccount(String oldName, String newName) {
        requireAllNonNull(oldName, newName);
        //TODO: THIS EXCEPTION HAS TO CHANGE.
        if (!accounts.containsKey(oldName) || accounts.containsKey(newName)) {
            throw new ExpenditureNotFoundException();
        }
        Account targetAccount = accounts.get(oldName);
        Account replaceAccount = targetAccount.copyAccountWithNewName(newName);
        this.accounts.put(newName, replaceAccount);
        this.accounts.remove(oldName, targetAccount);
        if (this.activeAccount.getAccountName().equals(oldName)) {
            return newName;
        } else {
            return this.activeAccount.getAccountName();
        }
    }

    /**
     * Delete an account for the accounts base on the input name.
     * @param accName the target account's name
     * @return a new account name which is to replace
     */
    public String deleteAccount(String accName) {
        requireAllNonNull(accName);
        //TODO: THIS EXCEPTION HAS TO CHANGE.
        if (!this.accounts.containsKey(accName)) {
            throw new ExpenditureNotFoundException();
        }
        Account target = this.accounts.get(accName);
        this.accounts.remove(accName, target);
        if (this.accounts.size() == 0) {
            Account defaultAccount = new Account("default");
            addAccount(defaultAccount);
            updateActiveAccount(defaultAccount.getAccountName());
            return defaultAccount.getAccountName();
        } else {
            if (this.activeAccount.getAccountName().equals(accName)) {
                String firstKey = (String) (this.accounts.keySet().toArray())[0];
                updateActiveAccount(this.accounts.get(firstKey).getAccountName());
                return this.accounts.get(firstKey).getAccountName();
            }
            //no change
            return this.activeAccount.getAccountName();
        }
    }


    /**
     * Adds an account to the account list.
     * The account must not already exist in the account list.
     */
    public void addAccount(Account account) {
        requireNonNull(account);
        if (accounts.containsKey(account.getAccountName())) {
            throw new DuplicateAccountException();
        }
        accounts.put(account.getAccountName(), account);
    }

    /**
     * Clears all expenditures of the active account.
     */
    public void clearActiveAccount() {
        activeAccount.resetData(new Account());
        displayedBaseExpList.setAll(new ArrayList<>());
    }

    //// expenditure-level operations

    /**
     * Returns true if a expenditure with the same identity as {@code expenditure} exists in the internal list.
     */
    public boolean hasExpenditure(Expenditure expenditure) {
        requireNonNull(expenditure);
        return displayedBaseExpList.contains(expenditure);
    }

    /**
     * Deletes the given expenditure.
     * The expenditure must exist in the internal list.
     */
    public void removeExpenditure(Expenditure target) {
        activeAccount.removeExpenditure(target);
        displayedBaseExpList.remove(target);
        expAddIndex--;
    }

    /**
     * Deletes the given repeat.
     * The repeat must exist in the internal list.
     */
    public void removeRepeat(Repeat target) {
        activeAccount.removeRepeat(target);
        displayedBaseExpList.remove(target);
    }

    /**
     * Adds the given expenditure.
     */
    public void addExpenditure(Expenditure expenditure) {
        activeAccount.addExpenditure(expenditure);
        if (expenditure.getDate().localDate.equals(activeDate)) {
            displayedBaseExpList.add(expAddIndex, expenditure);
            expAddIndex++;
        }
    }

    /**
     * Adds the given repeat.
     */
    public void addRepeat(Repeat repeat) {
        activeAccount.addRepeat(repeat);
        if (repeat.isOn(activeDate)) {
            displayedBaseExpList.add(repeat);
        }
    }

    /**
     * Replaces the given expenditure {@code target} with {@code editedExpenditure}.
     * {@code target} must exist in the internal list.
     * The expenditure identity of {@code editedExpenditure} must not be the same as
     * another existing expenditure in the internal list.
     */
    public void setExpenditure(Expenditure target, Expenditure editedExpenditure) {
        requireAllNonNull(target, editedExpenditure);
        activeAccount.setExpenditure(target, editedExpenditure);
        if (editedExpenditure.getDate().localDate.equals(activeDate)) {
            displayedBaseExpList.set(displayedBaseExpList.indexOf(target), editedExpenditure);
        } else {
            displayedBaseExpList.remove(target);
        }
    }

    /**
     * Replaces the given expenditure {@code target} with {@code editedExpenditure}.
     * {@code target} must exist in the internal list.
     * The expenditure identity of {@code editedExpenditure} must not be the same as
     * another existing expenditure in the internal list.
     */
    public void setRepeat(Repeat target, Repeat editedRepeat) {
        requireAllNonNull(target, editedRepeat);
        activeAccount.setRepeat(target, editedRepeat);
        if (editedRepeat.isOn(activeDate)) {
            displayedBaseExpList.set(displayedBaseExpList.indexOf(target), editedRepeat);
        } else {
            displayedBaseExpList.remove(target);
        }
    }

    public void setBudget(Budget budget) {
        activeAccount.setBudget(budget);
        //TODO need to add in UI.
    }

    public BudgetMap getBudgets() {
        return activeAccount.getBudgetList();
    }

    //// util methods

    /**
     * Updates the date at which the expenditures will be shown in the UI
     * @param date the new active date
     */
    public void updateActiveDate(LocalDate date) {
        activeDate = date;
        resetFromActiveAccount();
    }

    /**
     * Updates the active account to the one with the specified accountName.
     * @param accountName the name of the account
     * @return if the update was successful
     */
    public boolean updateActiveAccount(String accountName) {
        if (!accounts.containsKey(accountName)) {
            return false;
        } else {
            activeAccount = accounts.get(accountName);
            resetFromActiveAccount();
            return true;
        }
    }

    @Override
    public Map<String, Account> getAccounts() {
        return Collections.unmodifiableMap(accounts);
    }

    @Override
    public String listAllName() {
        StringBuilder list = new StringBuilder();
        this.accounts.keySet().iterator().forEachRemaining(accName -> list.append(accName + " "));
        //this is too ugly, so i turn into an array then reformat to look better.
        //return list.toString().trim();
        String [] allName = list.toString().trim().split(" ");
        StringBuilder output = new StringBuilder();
        for (int i = 1; i <= allName.length; i++) {
            if (i % 8 == 0) {
                output.append("\n");
            }
            output.append(i + ". " + allName[i - 1] + "      ");
        }
        return output.toString().trim();
    }

    /**
     * resets the data in the displayed list and gets data from the active account
     */
    private void resetFromActiveAccount() {
        displayedBaseExpList.setAll(new ArrayList<>());
        displayedBaseExpList.addAll(activeAccount.getExpByDate(activeDate).asUnmodifiableObservableList());
        expAddIndex = displayedBaseExpList.size();
        displayedBaseExpList.addAll(activeAccount.getRepeatByDate(activeDate));
    }

    @Override
    public ObservableList<Expenditure> getExpenditureList() {
        return FXCollections.unmodifiableObservableList(
                FXCollections.observableArrayList(displayedBaseExpList.stream()
                .filter(baseExp -> baseExp instanceof Expenditure)
                .map(exp -> (Expenditure) exp).collect(Collectors.toList())));
    }

    @Override
    public ObservableList<Repeat> getRepeatList() {
        return FXCollections.unmodifiableObservableList(
                FXCollections.observableArrayList(displayedBaseExpList.stream()
                .filter(baseExp -> baseExp instanceof Repeat)
                .map(exp -> (Repeat) exp).collect(Collectors.toList())));
    }

    public ObservableList<BaseExp> getBaseExpList() {
        return FXCollections.unmodifiableObservableList(displayedBaseExpList);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AccountList // instanceof handles nulls
                && accounts.equals(((AccountList) other).accounts));
    }

    @Override
    public String toString() {
        return "AccountList: " + displayedBaseExpList.toString();
    }

}
