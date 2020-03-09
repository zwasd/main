package seedu.address.testutil;

import seedu.address.model.Account;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code Account ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private Account account;

    public AddressBookBuilder() {
        account = new Account();
    }

    public AddressBookBuilder(Account account) {
        this.account = account;
    }

    /**
     * Adds a new {@code Person} to the {@code Account} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        account.addAccount(person);
        return this;
    }

    public Account build() {
        return account;
    }
}
