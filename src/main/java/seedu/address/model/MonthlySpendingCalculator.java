package seedu.address.model;

import java.time.YearMonth;

import javafx.collections.ObservableList;

import seedu.address.model.expenditure.Repeat;
import seedu.address.model.expenditure.UniqueExpenditureList;

/**
 * Calculate monthly balance given the budget is set.
 * If budget is not set, it will ask user to set budget.
 */
public class MonthlySpendingCalculator {
    private Double budget;
    private UniqueExpenditureList expenditures;
    private ObservableList <Repeat> repeats;
    private YearMonth givenYearMonth;
    private double totalSpending;
    private double balance;


    public MonthlySpendingCalculator(Double budget, UniqueExpenditureList expenditures,
                                      ObservableList <Repeat> repeats, YearMonth givenYearMonth) {
        if (budget != null) {
            this.budget = budget;
            this.expenditures = expenditures;
            this.repeats = repeats;
            this.givenYearMonth = givenYearMonth;
            this.totalSpending = calculateTotalMonthlySpending();
            this.balance = this.budget - this.totalSpending;
        }
    }

    public Double getBudget() {
        return this.budget;
    }

    public double getTotalSpending() {
        return this.totalSpending;
    }

    /**
     * Calculate total expenditure amount for a given YearMonth.
     * @return a double which the total amount for all the expenditure.
     */
    private double calculateMonthlyExpenditure() {
        return this.expenditures.calculateExpenditureAmount(givenYearMonth);
    }

    /**
     * Calculate total repeat amount for a given YearMonth.
     * @return a double which the total amount for all the repeat.
     */
    private double calculateMonthlyRepeat() {
        double total = 0;
        for (int i = 0; i < repeats.size(); i++) {
            total += this.repeats.get(i).calculateForGivenYearMonth(givenYearMonth);
        }
        return total;
    }

    /**
     * Calculate total amount of a given YearMonth.
     * @return a double which the total amount.
     */
    private double calculateTotalMonthlySpending() {
        return calculateMonthlyExpenditure() + calculateMonthlyRepeat();
    }

}
