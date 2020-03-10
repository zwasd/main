package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.expenditure.Address;
import seedu.address.model.expenditure.Amount;
import seedu.address.model.expenditure.Expenditure;
import seedu.address.model.expenditure.Id;
import seedu.address.model.expenditure.Info;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Expenditure objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_INFO = "Alice Pauline";
    public static final String DEFAULT_ID = "85355255";
    public static final double DEFAULT_AMOUNT = 3.14;
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Info info;
    private Id id;
    private Amount amount;
    private Address address;
    private Set<Tag> tags;

    public PersonBuilder() {
        info = new Info(DEFAULT_INFO);
        id = new Id(DEFAULT_ID);
        amount = new Amount(DEFAULT_AMOUNT);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code expenditureToCopy}.
     */
    public PersonBuilder(Expenditure expenditureToCopy) {
        info = expenditureToCopy.getInfo();
        id = expenditureToCopy.getId();
        amount = expenditureToCopy.getAmount();
        address = expenditureToCopy.getAddress();
        tags = new HashSet<>(expenditureToCopy.getTags());
    }

    /**
     * Sets the {@code Info} of the {@code Expenditure} that we are building.
     */
    public PersonBuilder withInfo(String info) {
        this.info = new Info(info);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Expenditure} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Expenditure} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Id} of the {@code Expenditure} that we are building.
     */
    public PersonBuilder withId(String id) {
        this.id = new Id(id);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Expenditure} that we are building.
     */
    public PersonBuilder withAmount(double amount) {
        this.amount = new Amount(amount);
        return this;
    }

    public Expenditure build() {
        return new Expenditure(info, id, amount, address, tags);
    }

}
