package seedu.saveit.logic.parser.general;

import static seedu.saveit.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.YearMonth;

import org.junit.jupiter.api.Test;

import seedu.saveit.logic.commands.general.SetBudgetCommand;
import seedu.saveit.logic.parser.CliSyntax;
import seedu.saveit.model.expenditure.Amount;

public class SetBudgetCommandParserTest {
    private final SetBudgetCommandParser parser = new SetBudgetCommandParser();

    @Test
    public void parse_validArgs_returnSetBudgetCommand() {
        String amountValue = "123.3";
        String yearMonthValue = YearMonth.now().toString();

        Amount testAmount = new Amount(amountValue);
        YearMonth testYearMonth = YearMonth.now();

        //TODO I ALLOW NULL FOR YEARMONTH
        //assertParseSuccess(parser, "" + CliSyntax.PREFIX_AMOUNT.getPrefix() + amountValue,
        //        new SetBudgetCommand(null, testAmount));

        assertParseSuccess(parser, " " + CliSyntax.PREFIX_AMOUNT.getPrefix() + amountValue + " "
                + CliSyntax.PREFIX_YEARMONTH.getPrefix() + yearMonthValue,
                new SetBudgetCommand(testYearMonth, testAmount));
    }
}
