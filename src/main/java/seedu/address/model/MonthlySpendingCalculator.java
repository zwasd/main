package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.expenditure.Repeat;
import seedu.address.model.expenditure.UniqueExpenditureList;

import java.time.YearMonth;

public class MonthlySpendingCalculator {
    private double budget;
    private UniqueExpenditureList expenditures;
    private ObservableList <Repeat> repeats;
    private YearMonth givenYearMonth;
    private double totalSpending;
    private double balance;


    public MonthlySpendingCalculator(double budget, UniqueExpenditureList expenditures,
                                      ObservableList <Repeat> repeats, YearMonth givenYearMonth) {
        if ((Double) budget != null) {
            this.budget = budget;
            this.expenditures = expenditures;
            this.repeats = repeats;
            this.givenYearMonth = givenYearMonth;
            this.totalSpending = calculateTotalMonthlySpending();
            this.balance = this.budget - this.totalSpending;
        }
    }

    public double getBudget() {
        return this.budget;
    }

    public double getTotalSpending() {
        return this.totalSpending;
    }

    public double getBalance() {
        return this.balance;
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
