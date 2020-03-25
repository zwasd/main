package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javafx.collections.ObservableList;
import seedu.address.model.expenditure.Expenditure;
import seedu.address.model.expenditure.Repeat;
import seedu.address.model.expenditure.UniqueExpenditureList;
import seedu.address.model.expenditure.exceptions.ExpenditureNotFoundException;

/**
 * Manages all accounts of the user.
 */
public class AccountList implements ReadOnlyAccountList, ReadOnlyAccount {
    private Map<String, Account> accounts = new HashMap<>();
    private Account activeAccount; //TODO: make it static ?? (XP)
    private final UniqueExpenditureList internalList = new UniqueExpenditureList();
    private String initialAccountName = "default"; // TODO
    private LocalDate activeDate;

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
        internalList.setExpenditures(activeAccount.getExpByDate(activeDate));
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
     */
    public void renameAccount(String oldName, String newName) {
        requireAllNonNull(oldName, newName);
        //TODO: THIS EXCEPTION HAS TO CHANGE.
        if (!accounts.containsKey(oldName) || accounts.containsKey(newName)) {
            throw new ExpenditureNotFoundException();
        }
        Account targetAccount = accounts.get(oldName);
        Account replaceAccount = targetAccount.copyAccountWithNewName(newName);
        this.accounts.put(newName, replaceAccount);
        this.accounts.remove(oldName, targetAccount);
    }

    /**
     * Delete an account for the accounts base on the input name.
     * @param accName the target account's name
     */
    public void deleteAccount(String accName) {
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
        } else {
            if (this.activeAccount.getAccountName().equals(accName)) {
                String firstKey = (String) (this.accounts.keySet().toArray())[0];
                updateActiveAccount(this.accounts.get(firstKey).getAccountName());
            }
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
        internalList.setExpenditures(new ArrayList<>());
    }

    //// expenditure-level operations

    /**
     * Returns true if a expenditure with the same identity as {@code expenditure} exists in the internal list.
     */
    public boolean hasExpenditure(Expenditure expenditure) {
        requireNonNull(expenditure);
        return internalList.contains(expenditure);
    }

    /**
     * Deletes the given expenditure.
     * The expenditure must exist in the internal list.
     */
    public void removeExpenditure(Expenditure target) {
        activeAccount.removeExpenditure(target);
        internalList.remove(target);
    }

    /**
     * Adds the given expenditure.
     */
    public void addExpenditure(Expenditure expenditure) {
        activeAccount.addExpenditure(expenditure);
        if (expenditure.getDate().localDate.equals(activeDate)) {
            internalList.add(expenditure);
        }
    }

    /**
     * Adds the given repeat.
     */
    public void addRepeat(Repeat repeat) {
        activeAccount.addRepeat(repeat);
        //TODO: ADD INTO THE INTERNAL LIST
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
        internalList.setExpenditure(target, editedExpenditure);
    }

    //// util methods

    /**
     * Updates the date at which the expenditures will be shown in the UI
     * @param date the new active date
     */
    public void updateActiveDate(LocalDate date) {
        internalList.setExpenditures(activeAccount.getExpByDate(date));
        activeDate = date;
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
            internalList.setExpenditures(activeAccount.getExpByDate(activeDate));
            return true;
        }
    }

    public Account getActiveAccount() {
        return activeAccount;
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

    @Override
    public ObservableList<Expenditure> getExpenditureList() {
        return internalList.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AccountList // instanceof handles nulls
                && accounts.equals(((AccountList) other).accounts));
    }

    @Override
    public String toString() {
        return "AccountList: " + internalList.toString();
    }

}
