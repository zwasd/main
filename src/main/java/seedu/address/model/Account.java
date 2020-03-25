package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.expenditure.Date;
import seedu.address.model.expenditure.Expenditure;
import seedu.address.model.expenditure.Repeat;
import seedu.address.model.expenditure.UniqueExpenditureList;
import seedu.address.model.expenditure.exceptions.RepeatNotFoundException;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .equals comparison)
 */
public class Account implements ReadOnlyAccount, ReportableAccount {

    private final UniqueExpenditureList expenditures;
    private ObservableList<Repeat> repeats;
    private final String accountName;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        expenditures = new UniqueExpenditureList();
        repeats = FXCollections.observableArrayList();
    }

    public Account() {
        accountName = null;
    }

    public Account(String accountName) {
        this.accountName = accountName;
    }


    /**
     * Creates an Account using the Expenditures in the {@code toBeCopied}
     */
    public Account copyAccountWithNewName(String newName) {
        Account toBeCopied = new Account(newName);
        toBeCopied.resetData(this);
        return toBeCopied;
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
        this.expenditures.setExpenditures(expenditures);
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
        return expenditures.contains(expenditure);
    }


    /**
     * Adds a expenditure to the dayToDayExpenditure.
     * The expenditure must not already exist in the address book.
     */
    public void addExpenditure(Expenditure expenditure) {
        expenditures.add(expenditure);
    }

    /**
     * Adds a repeat to the repeatList.
     */
    public void addRepeat(Repeat repeat) {
        repeats.add(repeat);
    }

    /**
     * Replaces the given expenditure {@code target} in the list with {@code editedExpenditure}.
     * {@code target} must exist in the address book.
     * The expenditure identity of {@code editedExpenditure} must not be the same as another
     * existing expenditure in the address book.
     */
    public void setExpenditure(Expenditure target, Expenditure editedExpenditure) {
        requireNonNull(editedExpenditure);

        expenditures.setExpenditure(target, editedExpenditure);
    }

    /**
     * Removes {@code key} from this {@code Account}.
     * {@code key} must exist.
     */
    public void removeExpenditure(Expenditure key) {
        expenditures.remove(key);
    }

    /**
     * Removes {@code Repeat} from this {@code repeatItem}.
     */
    public void removeRepeat(Repeat repeat) {
        if (!repeats.remove(repeat)) {
            throw new RepeatNotFoundException();
        }
    }

    //// util methods

    @Override
    public String toString() {
        // return expenditures.asUnmodifiableObservableList().size() + " expenditures";
        // TODO: refine later
        return "Account: " + accountName;
    }

    @Override
    public ObservableList<Expenditure> getExpenditureList() {
        return expenditures.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Repeat> getRepeatList() {
        return FXCollections.unmodifiableObservableList(repeats);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Account // instanceof handles nulls
                && accountName.equals(((Account) other).accountName)
                && expenditures.equals(((Account) other).expenditures));
    }

    @Override
    public int hashCode() {
        return expenditures.hashCode();
    }

    @Override
    public ObservableList<Repeat> getRepeatByDate(LocalDate date) {
        return FXCollections.observableArrayList(
                repeats.stream().filter(repeat -> repeat.isOn(date)).collect(Collectors.toList()));
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
    public UniqueExpenditureList getExpByDate(LocalDate date) {
        return getExpByDate(date.format(DateTimeFormatter.ISO_DATE));
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
        return StreamSupport.stream(expenditures.spliterator(), false);
    }
}
