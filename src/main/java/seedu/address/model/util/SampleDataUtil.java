package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.Account;
import seedu.address.model.ReadOnlyAccount;
import seedu.address.model.expenditure.Amount;
import seedu.address.model.expenditure.Date;
import seedu.address.model.expenditure.Expenditure;
import seedu.address.model.expenditure.Info;
import seedu.address.model.tag.Tag;

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
                    new Date("2019-09-15"),new Tag("classmates")),
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
