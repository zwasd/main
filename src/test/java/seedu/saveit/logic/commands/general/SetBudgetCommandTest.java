package seedu.saveit.logic.commands.general;

import static seedu.saveit.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.saveit.logic.parser.ParserUtil;

public class SetBudgetCommandTest {

    @Test
    public void constructor_nullArgs_throwsNullPointerException() {
        // both null
        assertThrows(NullPointerException.class, () -> new SetBudgetCommand(null, null));

        // amount null
        assertThrows(NullPointerException.class, () ->
                new SetBudgetCommand(ParserUtil.parseYearMonth("2020-03"), null));
    }


}
