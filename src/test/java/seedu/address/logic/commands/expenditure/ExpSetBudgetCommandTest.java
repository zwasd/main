package seedu.address.logic.commands.expenditure;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ParserUtil;

public class ExpSetBudgetCommandTest {

    @Test
    public void constructor_nullArgs_throwsNullPointerException() {
        // both null
        assertThrows(NullPointerException.class, () -> new ExpSetBudgetCommand(null, null));

        // yearMonth null TODO THIS PART NEED CHANGE, I ALLOW THE YEARMONTH TO BE NULL.
        //assertThrows(NullPointerException.class, () -> new ExpSetBudgetCommand(null, new Amount(1)));

        // amount null
        assertThrows(NullPointerException.class, () ->
                new ExpSetBudgetCommand(ParserUtil.parseYearMonth("2020-03"), null));
    }
}
