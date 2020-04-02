package seedu.address.logic.parser.expenditure;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.YearMonth;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.expenditure.ExpSetBudgetCommand;
import seedu.address.logic.parser.CliSyntax;
import seedu.address.model.expenditure.Amount;

public class ExpSetBudgetCommandParserTest {
    private final ExpSetBudgetCommandParser parser = new ExpSetBudgetCommandParser();

    @Test
    public void parse_validArgs_returnSetBudgetCommand() {
        String amountValue = "123.3";
        String yearMonthValue = YearMonth.now().toString();

        Amount testAmount = new Amount(amountValue);
        YearMonth testYearMonth = YearMonth.now();

        //TODO I ALLOW NULL FOR YEARMONTH
        //assertParseSuccess(parser, "" + CliSyntax.PREFIX_AMOUNT.getPrefix() + amountValue,
        //        new ExpSetBudgetCommand(null, testAmount));

        assertParseSuccess(parser, " " + CliSyntax.PREFIX_AMOUNT.getPrefix() + amountValue + " "
                + CliSyntax.PREFIX_YEARMONTH.getPrefix() + yearMonthValue,
                new ExpSetBudgetCommand(testYearMonth, testAmount));
    }
}
