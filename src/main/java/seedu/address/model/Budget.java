package seedu.address.model;

import java.time.YearMonth;

/**
 * A class for budget.
 */
public class Budget {

    private double budget;
    // The yearMonth is not going to change.
    // If need change yearMonth, just destroyed and create new one.
    private final YearMonth yearMonth;

    public Budget(YearMonth yearMonth, double budgetAmount) {
        this.budget = budgetAmount;
        this.yearMonth = yearMonth;
    }

    public Budget (double budgetAmount) {
        this.yearMonth = YearMonth.now();
        this.budget = budgetAmount;
    }

    public double getBudget() {
        return this.budget;
    }

    public void setBudget(double newBudget) {
        this.budget = newBudget;
    }

    public YearMonth getYearMonth() {
        return this.yearMonth;
    }


}
