package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.expenditure.Amount;
import seedu.address.model.expenditure.Date;
import seedu.address.model.expenditure.Expenditure;
import seedu.address.model.expenditure.Info;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Expenditure objects.
 */
public class ExpenditureBuilder {


    public static final String DEFAULT_INFO = "Alice Pauline";
    public static final double DEFAULT_AMOUNT = 3.14;
    public static final String DEFAULT_DATE = "2019-09-11";

    private Info info;
    private Amount amount;
    private Date date;
    private Set<Tag> tags;

    public ExpenditureBuilder() {
        info = new Info(DEFAULT_INFO);
        amount = new Amount(DEFAULT_AMOUNT);
        date = new Date(DEFAULT_DATE);
        tags = new HashSet<>();
    }

    /**
     * Initializes the ExpenditureBuilder with the data of {@code expenditureToCopy}.
     */

    public ExpenditureBuilder(Expenditure expenditureToCopy) {
        info = expenditureToCopy.getInfo();
        amount = expenditureToCopy.getAmount();
        date = expenditureToCopy.getDate();
        tags = new HashSet<>(expenditureToCopy.getTags());

    }

    /**
     * Sets the {@code Info} of the {@code Expenditure} that we are building.
     */
    public ExpenditureBuilder withInfo(String info) {
        this.info = new Info(info);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Expenditure} that we are building.
     */
    public ExpenditureBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Expenditure} that we are building.
     */
    public ExpenditureBuilder withDate(String date) {
        this.date = new Date(date);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Expenditure} that we are building.
     */
    public ExpenditureBuilder withAmount(double amount) {
        this.amount = new Amount(amount);
        return this;
    }

    public Expenditure build() {
        return new Expenditure(info, amount, date, tags);
    }

}
