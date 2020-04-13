package seedu.saveit.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.saveit.testutil.Assert.assertThrows;
import static seedu.saveit.testutil.TypicalAccounts.getTypicalAccountList;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.saveit.logic.commands.exceptions.CommandException;

public class AccountListTest {

    private final AccountList accountListWithDefault = new AccountList(true);
    private final AccountList accountList = new AccountList(false);

    @Test
    public void constructor() {
        assertEquals(accountListWithDefault.getAccounts().size(), 1);
        assertEquals(accountListWithDefault.getAccounts().get("default"), new Account("default"));
        assertEquals(accountList.getAccounts().size(), 0);
    }


    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> accountList.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAccountList_replacesData() {
        AccountList newData = getTypicalAccountList();
        accountList.resetData(newData);
        assertEquals(newData, accountList);
    }

    @Test
    public void hasAccount_nullAccount_throwsNullPointerException() {
        Account nullAccount = null;
        assertThrows(NullPointerException.class, () -> accountList.hasAccount(nullAccount));
    }

    @Test
    public void hasAccount_accountNotInAccountList_returnsFalse() {
        assertFalse(accountList.hasAccount(new Account("default")));
    }

    @Test
    public void hasAccount_accountInAccountList_returnsTrue() {
        Account account = new Account("temp");
        accountList.addAccount(account);
        assertTrue(accountList.hasAccount(account));
    }

    @Test
    public void addAccount_nullAccount_throwsNullPointerException() {
        Account nullAccount = null;
        assertThrows(NullPointerException.class, () -> accountList.addAccount(nullAccount));
    }

    @Test
    public void addAccount_duplicateAccount_throwsDuplicateAccountException() {
        Account account = new Account("temp");
        AccountList accountList = getTypicalAccountList();
        accountList.addAccount(account);
        assertThrows(DuplicateAccountException.class, () -> accountList.addAccount(account));
        assertThrows(DuplicateAccountException.class, () -> accountList.addAccount(new Account("temp")));
    }

    @Test
    public void clearActiveAccount_dataEqualsEmptyAccount() {
        AccountList accountList = getTypicalAccountList();
        accountList.clearActiveAccount();
        Account empty = new Account(accountList.getActiveAccountName());
        assertEquals(accountList.getReportableAccount(), empty);
    }

    @Test
    public void renameAccount_success() throws CommandException {
        AccountList accountList = getTypicalAccountList();
        assertTrue(accountList.hasAccount("school"));
        assertFalse(accountList.hasAccount("hmm"));

        Account school = accountList.getAccounts().get("school");
        accountList.renameAccount("school", "hmm");

        assertFalse(accountList.hasAccount("school"));
        assertTrue(accountList.hasAccount("hmm"));

        Account hmm = accountList.getAccounts().get("hmm");
        assertEquals(school.getRepeatList(), hmm.getRepeatList());
        assertEquals(school.getExpenditureList(), hmm.getExpenditureList());
    }

    @Test
    public void renameAccount_nullOldName_success() throws CommandException {
        AccountList accountList = getTypicalAccountList();
        assertTrue(accountList.hasAccount("school"));
        assertFalse(accountList.hasAccount("hmm"));
        accountList.updateActiveAccount("school");

        Account school = accountList.getAccounts().get("school");
        accountList.renameAccount(null, "hmm");

        assertFalse(accountList.hasAccount("school"));
        assertTrue(accountList.hasAccount("hmm"));

        Account hmm = accountList.getAccounts().get("hmm");
        assertEquals(school.getRepeatList(), hmm.getRepeatList());
        assertEquals(school.getExpenditureList(), hmm.getExpenditureList());
    }

    @Test
    public void renameAccount_nullNewName_throwsNullPointerException() {
        AccountList accountList = getTypicalAccountList();
        assertThrows(NullPointerException.class, () -> accountList.renameAccount(null, null));
    }

    @Test
    public void deleteAccount_nullAccount_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> accountList.deleteAccount(null));
    }

    @Test
    public void deleteAccount_doesNotContainAccount_throwsCommandException() {
        AccountList accountList = getTypicalAccountList();
        assertThrows(CommandException.class, () -> accountList.deleteAccount("default"));
    }

    @Test
    public void deleteAccount_deletingLastAccount_createsDefaultAccount() throws CommandException {
        AccountList accountList = new AccountList(true);
        assertEquals(accountList.getAccounts().size(), 1);
        accountList.deleteAccount("default");

        assertEquals(accountList.getAccounts().size(), 1);
        assertEquals(accountList.getActiveAccountName(), "default");
    }

    @Test
    public void deleteAccount_deletingActiveAccount_activeAccountChanges() throws CommandException {
        AccountList accountList = getTypicalAccountList();
        accountList.updateActiveAccount("school");
        accountList.deleteAccount("school");

        assertNotEquals(accountList.getActiveAccountName(), "school");
        assertNotEquals(accountList.getActiveAccountName(), null);
    }

    @Test
    public void updateActiveDate_success() {
        AccountList accountList = new AccountList(true);
        accountList.updateActiveDate(LocalDate.now());
        assertEquals(accountList.getActiveDate(), LocalDate.now());
    }

    @Test
    public void updateActiveAccount_accountExists_success() {
        AccountList accountList = getTypicalAccountList();
        assertTrue(accountList.updateActiveAccount("work"));
        assertEquals(accountList.getActiveAccountName(), "work");
    }

    @Test
    public void updateActiveAccount_accountNotExists_failure() {
        AccountList accountList = new AccountList(true);
        assertFalse(accountList.updateActiveAccount("hmm"));
    }
}
