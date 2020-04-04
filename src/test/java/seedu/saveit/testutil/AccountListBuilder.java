package seedu.saveit.testutil;

import seedu.saveit.model.Account;
import seedu.saveit.model.AccountList;


/**
 * A utility class to help with building AccountList objects.
 * Example usage: <br>
 *     {@code Account ab = new AccountBuilder().withExpenditure("John", "Doe").build();}
 */
public class AccountListBuilder {

    private AccountList accountList;

    public AccountListBuilder() {
        accountList = new AccountList(false);
    }

    public AccountListBuilder(AccountList accountList) {
        this.accountList = accountList;
    }

    /**
     * Adds a new {@code Expenditure} to the {@code Account} that we are building.
     */
    public AccountListBuilder withAccount(Account account) {
        accountList.addAccount(account);
        return this;
    }

    public AccountList build() {
        return accountList;
    }
}
