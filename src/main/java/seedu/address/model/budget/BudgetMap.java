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

    public Double get(YearMonth yearMonth) {
        return budgets.getOrDefault(yearMonth, null);
    }


    public HashMap<YearMonth, Double> getBudgets() {
        return budgets;
    }
}
