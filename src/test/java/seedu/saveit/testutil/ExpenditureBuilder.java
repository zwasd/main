package seedu.saveit.testutil;

import seedu.saveit.model.expenditure.Amount;
import seedu.saveit.model.expenditure.Date;
import seedu.saveit.model.expenditure.Expenditure;
import seedu.saveit.model.expenditure.Info;
import seedu.saveit.model.tag.Tag;

/**
 * A utility class to help with building Expenditure objects.
 */
public class ExpenditureBuilder {


    public static final String DEFAULT_INFO = "Alice Pauline";
    public static final double DEFAULT_AMOUNT = 3.14;
    public static final String DEFAULT_DATE = "2019-09-11";
    public static final String DEFAULT_TAG = "Others";

    private Info info;
    private Amount amount;
    private Date date;
    private Tag tag;

    public ExpenditureBuilder() {
        info = new Info(DEFAULT_INFO);
        amount = new Amount(DEFAULT_AMOUNT);
        date = new Date(DEFAULT_DATE);
        tag = new Tag(DEFAULT_TAG);
    }

    /**
     * Initializes the ExpenditureBuilder with the data of {@code expenditureToCopy}.
     */

    public ExpenditureBuilder(Expenditure expenditureToCopy) {
        info = expenditureToCopy.getInfo();
        amount = expenditureToCopy.getAmount();
        date = expenditureToCopy.getDate();
        tag = expenditureToCopy.getTag();

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
    public ExpenditureBuilder withTag(String tag) {
        this.tag = new Tag(tag);
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
        return new Expenditure(info, amount, date, tag);
    }

}
