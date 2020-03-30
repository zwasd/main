package seedu.address.model;

import java.time.YearMonth;
import java.util.ArrayList;

import seedu.address.model.expenditure.Expenditure;

/**
 * This class will be use for MonthlyExpenseCalculation.
 */
public class MonthlyExpenseCalculation {
    private YearMonth givenYearMonth;
    private Budget budget;
    private ArrayList <Expenditure> expendituresInGivenMonth;

}
