package seedu.saveit.model;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import seedu.saveit.logic.parser.ParserUtil;
import seedu.saveit.logic.parser.exceptions.ParseException;
import seedu.saveit.model.budget.Budget;
import seedu.saveit.model.budget.BudgetMap;
import seedu.saveit.model.expenditure.Amount;
import seedu.saveit.model.expenditure.Date;
import seedu.saveit.model.expenditure.Expenditure;
import seedu.saveit.model.expenditure.Repeat;
import seedu.saveit.model.expenditure.UniqueExpenditureList;
import seedu.saveit.model.expenditure.exceptions.RepeatNotFoundException;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .equals comparison)
 */
public class Account implements ReadOnlyAccount, ReportableAccount {

    private final UniqueExpenditureList expenditures;
    private final BudgetMap budgetList;
    private ObservableList<Repeat> repeats;
    private final String accountName;
    private MonthlySpendingCalculator calculator;

     /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */ {
        expenditures = new UniqueExpenditureList();
        repeats = FXCollections.observableArrayList();
        budgetList = new BudgetMap();
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
        repeats.setAll(new ArrayList<>());
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
     * Replaces the given expenditure {@code target} in the list with {@code editedExpenditure}.
     * {@code target} must exist in the address book.
     * The expenditure identity of {@code editedExpenditure} must not be the same as another
     * existing expenditure in the address book.
     */
    public void setRepeat(Repeat target, Repeat editedRepeat) {
        requireNonNull(editedRepeat);
        repeats.set(repeats.indexOf(target), editedRepeat);
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

    /**
     * Add or reset a budget to the budgetList.
     *
     * @param budget contains amount and the yearMonth.
     */
    public void setBudget(Budget budget) {
        requireNonNull(budget);
        //This can use to reset the budget too.
        this.budgetList.setBudget(budget);
    }

    public void setBudget(YearMonth yearMonth, Amount amount) {
        requireNonNull(yearMonth);
        requireNonNull(amount);
        this.budgetList.setBudget(new Budget(yearMonth, amount));
    }

    /**
     * Obtain the budget object for a given yearMonth.
     *
     * @param yearMonth the target month you are looking for.
     * @return If the budget is within the [@code budgetList], return it.
     * Else return a budget object with 0 amount.
     */
    public Double getBudget(YearMonth yearMonth) {
        requireNonNull(yearMonth);
        return budgetList.get(yearMonth);
    }

    /**
     * Obtain the budget object for a given yearMonth.
     *
     * @param yearMonth the target month you are looking for.
     * @return If the budget is within the [@code budgetList], return it.
     * Else return a budget object with 0 amount.
     */
    public double getBudget(String yearMonth) throws ParseException {
        requireNonNull(yearMonth);
        try {
            YearMonth targetYearMonth = ParserUtil.parseYearMonth(yearMonth);
            return getBudget(targetYearMonth);
        } catch (Exception e) {
            throw new ParseException("Year Month need to be in a format of : YYYY-MM");
        }
    }

    public BudgetMap getBudgetList() {
        return budgetList;
    }


    private void setCalculator(YearMonth givenYearMonth) {
        this.calculator = new MonthlySpendingCalculator(getBudget(givenYearMonth), expenditures, repeats,
                givenYearMonth);
    }


    /**
     * Calculate total expenditure amount for a given YearMonth.
     *
     * @param givenYearMonth target YearMonth.
     * @return a double which the total amount for all the expenditure.
     */
    private double calculateMonthlyExpenditure(YearMonth givenYearMonth) {
        return this.expenditures.calculateExpenditureAmount(givenYearMonth);
    }

    /**
     * Calculate total repeat amount for a given YearMonth.
     *
     * @param givenYearMonth target YearMonth.
     * @return a double which the total amount for all the repeat.
     */
    private double calculateMonthlyRepeat(YearMonth givenYearMonth) {
        double total = 0;
        for (int i = 0; i < repeats.size(); i++) {
            total += this.repeats.get(i).calculateForGivenYearMonth(givenYearMonth);
        }
        return total;
    }

    /**
     * Calculate total amount of a given YearMonth.
     *
     * @param givenYearMonth target YearMonth.
     * @return a double which the total amount.
     */
    public MonthlySpendingCalculator calculateMonthly(YearMonth givenYearMonth) {
        setCalculator(givenYearMonth);
        return this.calculator;
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
                && expenditures.equals(((Account) other).expenditures)
                && repeats.equals(((Account) other).repeats));
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
    public Map<Repeat, Double> getRepeatExpFromToInclusiveByRepeat(Date startDate, Date endDate) {
        HashMap repMap = new HashMap();

        repeats.stream().filter(repeat -> Date.isEqualOrAfter(repeat.getEndDate(), startDate)
                && Date.isEqualOrBefore(repeat.getStartDate(), endDate)
        ).forEach(repeat -> {

            System.out.println(repeat);

                    if (repeat.getPeriod() == Repeat.Period.DAILY) {
                        double amt = repeat.calculateDaily(startDate, endDate);
                        repMap.put(repeat, amt);

                    } else if (repeat.getPeriod() == Repeat.Period.WEEKLY
                            || repeat.getPeriod() == Repeat.Period.MONTHLY
                            || repeat.getPeriod() == Repeat.Period.ANNUALLY) {
                        double amt = repeat.calculateWkOrMthOrYr(startDate, endDate);
                        repMap.put(repeat, amt);
                    }
                }
        );


        return repMap;
    }

    @Override
    public Map<String, Double> getRepeatExpFromToInclusiveByMonth(Date startDate, Date endDate) {
        HashMap<String, Double> repMap = new HashMap();
        repeats.stream().filter(repeat -> Date.isEqualOrAfter(repeat.getEndDate(), startDate)
                && Date.isEqualOrBefore(repeat.getStartDate(), endDate)
        ).forEach(repeat -> {

                    HashMap<String, Double> monthlyExpenditures = null;

                    if (repeat.getPeriod() == Repeat.Period.DAILY) {
                        monthlyExpenditures = repeat.calculateDailyRepeatMonth(startDate, endDate);

                    } else if (repeat.getPeriod() == Repeat.Period.WEEKLY
                            || repeat.getPeriod() == Repeat.Period.MONTHLY
                            || repeat.getPeriod() == Repeat.Period.ANNUALLY) {
                        monthlyExpenditures = repeat.calculateWkOrMthOrYrMonth(startDate, endDate);
                    }

                    assert monthlyExpenditures != null;
                    for (String month : monthlyExpenditures.keySet()) {

                        if (repMap.containsKey(month)) {
                            repMap.put(month, monthlyExpenditures.get(month) + repMap.get(month));
                        } else {
                            repMap.put(month, monthlyExpenditures.get(month));
                        }

                    }


                }
        );

        return repMap;
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
    public Map<Date, UniqueExpenditureList> getExpFromToInclusive(String startDate, String endDate) {
        return getExpFromToInclusive(new Date(startDate), new Date(endDate));
    }

    @Override
    public Map<Date, UniqueExpenditureList> getExpFromToInclusive(Date start, Date end) {
        Map<Date, UniqueExpenditureList> expMap = new HashMap<>();
        getExpenditureStream()
                .filter(exp -> Date.isEqualOrBefore(start, exp.getDate())
                        && Date.isEqualOrBefore(exp.getDate(), end))
                .forEach(exp -> {

                    Date date = exp.getDate();

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
