package seedu.saveit.testutil;

import static seedu.saveit.testutil.TypicalExpenditures.ALICE;
import static seedu.saveit.testutil.TypicalExpenditures.BENSON;
import static seedu.saveit.testutil.TypicalExpenditures.CARL;
import static seedu.saveit.testutil.TypicalExpenditures.DANIEL;
import static seedu.saveit.testutil.TypicalExpenditures.ELLE;
import static seedu.saveit.testutil.TypicalExpenditures.FIONA;
import static seedu.saveit.testutil.TypicalExpenditures.GEORGE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.saveit.model.Account;
import seedu.saveit.model.AccountList;

/**
 * A utility class containing a list of {@code Expenditure} objects to be used in tests.
 */
public class TypicalAccounts {
    public String accountName = "Test";
    public static final Account SCHOOL = new AccountBuilder("school").withExpenditure(ALICE)
            .withExpenditure(BENSON).build();
    public static final Account WORK = new AccountBuilder("work").withExpenditure(CARL).withExpenditure(DANIEL).build();
    public static final Account CCA = new AccountBuilder("cca").withExpenditure(ELLE)
            .withExpenditure(FIONA).withExpenditure(GEORGE).build();

    private TypicalAccounts() {} // prevents instantiation

    /**
     * Returns an {@code Account} with all the typical expenditures.
     */
    public static AccountList getTypicalAccountList() {
        AccountList ab = new AccountList(false);
        for (Account account : getTypicalAccounts()) {
            ab.addAccount(account);
        }
        ab.updateActiveAccount("school");
        return ab;
    }

    public static List<Account> getTypicalAccounts() {
        return new ArrayList<>(Arrays.asList(SCHOOL, WORK, CCA));
    }
}
