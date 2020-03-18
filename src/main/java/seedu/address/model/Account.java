package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javafx.collections.ObservableList;
import seedu.address.model.expenditure.Date;
import seedu.address.model.expenditure.Expenditure;
import seedu.address.model.expenditure.UniqueExpenditureList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .equals comparison)
 */
public class Account implements ReadOnlyAccount, ReportableAccount {

    private final UniqueExpenditureList persons;
    private final String accountName;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniqueExpenditureList();
    }

    public Account() {
        accountName = null;
    }

    public Account(String accountName) {
        this.accountName = accountName;
    }

    /**
     * Creates an Account using the Persons in the {@code toBeCopied}
     */
    public Account(Account toBeCopied) {
        this(toBeCopied.accountName);
        resetData(toBeCopied);
    }

    public String getAccountName() {
        return accountName;
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the expenditure list with {@code expenditures}.
     * {@code expenditures} must not contain duplicate expenditures.
     */

    public void setExpenditures(List<Expenditure> expenditures) {
        this.persons.setExpenditures(expenditures);
    }

    /**
     * Resets the existing data of this {@code Account} with {@code newData}.
     */
    public void resetData(ReadOnlyAccount newData) {
        requireNonNull(newData);
        setExpenditures(newData.getExpenditureList());
    }

    //// expenditure-level operations

    /**
     * Returns true if a expenditure with the same identity as {@code expenditure} exists in the address book.
     */

    public boolean hasExpenditure(Expenditure expenditure) {
        requireNonNull(expenditure);
        return persons.contains(expenditure);
    }


    /**
     * Adds a expenditure to the address book.
     * The expenditure must not already exist in the address book.
     */
    public void addExpenditure(Expenditure expenditure) {
        persons.add(expenditure);
    }

    /**
     * Replaces the given expenditure {@code target} in the list with {@code editedExpenditure}.
     * {@code target} must exist in the address book.
     * The expenditure identity of {@code editedExpenditure} must not be the same as another
     * existing expenditure in the address book.
     */
    public void setExpenditure(Expenditure target, Expenditure editedExpenditure) {
        requireNonNull(editedExpenditure);

        persons.setExpenditure(target, editedExpenditure);
    }

    /**
     * Removes {@code key} from this {@code Account}.
     * {@code key} must exist in the address book.
     */
    public void removeExpenditure(Expenditure key) {
        persons.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        // return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
        return "Account: " + accountName;
    }

    @Override
    public ObservableList<Expenditure> getExpenditureList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Account // instanceof handles nulls
                && accountName.equals(((Account) other).accountName)
                && persons.equals(((Account) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }

    @Override
    public UniqueExpenditureList getExpByDate(String date) {
        return new UniqueExpenditureList(
                getExpenditureStream()
                    .filter(exp -> exp.getDate().toString().equals(date))
                    .collect(Collectors.toList())
        );
    }

    @Override
    public Map<String, UniqueExpenditureList> getExpFromToInclusive(String startDate, String endDate) {
        return getExpFromToInclusive(new Date(startDate), new Date(endDate));
    }

    @Override
    public Map<String, UniqueExpenditureList> getExpFromToInclusive(Date start, Date end) {
        Map<String, UniqueExpenditureList> expMap = new HashMap<>();
        getExpenditureStream()
                .filter(exp -> Date.isEqualOrBefore(start, exp.getDate())
                            && Date.isEqualOrBefore(exp.getDate(), end))
                .forEach(exp -> {
                    String date = exp.getDate().toString();
                    if (!expMap.containsKey(date)) {
                        UniqueExpenditureList expList = new UniqueExpenditureList();
                        expList.add(exp);
                        expMap.put(date, expList);
                    } else {
                        expMap.get(date).add(exp);
                    }
                });
        return expMap;
    }

    private Stream<Expenditure> getExpenditureStream() {
        return StreamSupport.stream(persons.spliterator(), false);
    }
}
