package seedu.address.model.budget;

import java.time.YearMonth;

import seedu.address.model.expenditure.Amount;

/**
 * A class for budget.
 */
public class Budget {

    private final Amount budget;
    // The yearMonth is not going to change.
    // If need change yearMonth, just destroyed and create new one.
    private final YearMonth yearMonth;

    public Budget(YearMonth yearMonth, Amount budgetAmount) {
        this.budget = budgetAmount;
        this.yearMonth = yearMonth;
    }

    public Budget(Amount budgetAmount) {
        this.yearMonth = YearMonth.now();
        this.budget = budgetAmount;
    }

    public Amount getBudget() {
        return this.budget;
    }

    public YearMonth getYearMonth() {
        return this.yearMonth;
    }


}
