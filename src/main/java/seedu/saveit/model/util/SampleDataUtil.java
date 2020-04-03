package seedu.saveit.model.util;

import seedu.saveit.model.Account;
import seedu.saveit.model.ReadOnlyAccount;
import seedu.saveit.model.expenditure.Amount;
import seedu.saveit.model.expenditure.Date;
import seedu.saveit.model.expenditure.Expenditure;
import seedu.saveit.model.expenditure.Info;
import seedu.saveit.model.tag.Tag;

/**
 * Contains utility methods for populating {@code Account} with sample data.
 */
public class SampleDataUtil {

    public static Expenditure[] getSampleExpenditures() {
        return new Expenditure[] {
            new Expenditure(new Info("Alex Yeoh"), new Amount(3.1),
                    new Date("2019-09-11"), new Tag("friends")),
            new Expenditure(new Info("Bernice Yu"), new Amount(3.1),
                    new Date("2019-09-12"), new Tag("colleagues")),
            new Expenditure(new Info("Charlotte Oliveiro"), new Amount(3.1),
                    new Date("2019-09-13"), new Tag("neighbours")),
            new Expenditure(new Info("David Li"), new Amount(3.1),
                    new Date("2019-09-14"), new Tag("family")),
            new Expenditure(new Info("Irfan Ibrahim"), new Amount(3.1),
                    new Date("2019-09-15"), new Tag("classmates")),
            new Expenditure(new Info("Roy Balakrishnan"), new Amount(3.1),
                    new Date("2019-09-11"), new Tag("colleagues"))
        };
    }


    public static ReadOnlyAccount getSampleAddressBook() {
        Account sampleAb = new Account();
        for (Expenditure sampleExpenditure : getSampleExpenditures()) {
            sampleAb.addExpenditure(sampleExpenditure);

        }
        return sampleAb;
    }

}
