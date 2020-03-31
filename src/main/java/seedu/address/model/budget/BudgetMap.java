package seedu.address.model.budget;

import java.time.YearMonth;
import java.util.HashMap;

/**
 * A hashmap of the budgets to be stored.
 */
public class BudgetMap {

    private HashMap<YearMonth, Double> budgets;

    public BudgetMap() {
        budgets = new HashMap<>();
    }

    public void setBudget(Budget budget) {
        budgets.put(budget.getYearMonth(), budget.getBudget().value);
    }

    public double get(YearMonth yearMonth) {
        return budgets.get(yearMonth);
    }

    public HashMap<YearMonth, Double> getBudgets() {
        return budgets;
    }
}
