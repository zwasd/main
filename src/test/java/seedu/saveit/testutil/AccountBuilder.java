package seedu.saveit.testutil;

import seedu.saveit.model.Account;

import seedu.saveit.model.expenditure.Expenditure;


/**
 * A utility class to help with building Account objects.
 * Example usage: <br>
 *     {@code Account ab = new AccountBuilder().withExpenditure("John", "Doe").build();}
 */
public class AccountBuilder {


    public static final String DEFAULT_ACCOUNT_NAME = "PERSONAL";


    private Account account;

    public AccountBuilder(String accountName) {
        account = new Account(accountName);
    }

    public AccountBuilder(Account account) {
        this.account = account;
    }

    /**
     * Adds a new {@code Expenditure} to the {@code Account} that we are building.
     */
    public AccountBuilder withExpenditure(Expenditure expenditure) {
        account.addExpenditure(expenditure);
        return this;
    }

    /**
     * build an new account with the given account name and expenditure list.
     * @return a new account.
     */
    public Account build() {
        Account acc = new Account(this.account.getAccountName());
        acc.setExpenditures(this.account.getExpenditureList());
        return acc;
    }
}
