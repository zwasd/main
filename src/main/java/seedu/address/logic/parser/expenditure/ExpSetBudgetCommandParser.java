package seedu.address.logic.parser.expenditure;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEARMONTH;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.stream.Stream;

import seedu.address.logic.commands.expenditure.ExpSetBudgetCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.expenditure.Amount;

/**
 * Parse set budget.
 */
public class ExpSetBudgetCommandParser implements Parser<ExpSetBudgetCommand> {
    public ExpSetBudgetCommandParser() {
    }

    @Override
    public ExpSetBudgetCommand parse(String userInput) throws ParseException {

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_AMOUNT, PREFIX_YEARMONTH);

        if (!arePrefixesPresent(argMultimap, PREFIX_AMOUNT)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExpSetBudgetCommand.MESSAGE_FAIL));
        }

        Amount amount = ParserUtil.parseAmount(argMultimap.getValue(PREFIX_AMOUNT).get());
        YearMonth yearMonth = ParserUtil.parseYearMonth(argMultimap.getValue(PREFIX_YEARMONTH)
                .orElseGet(() -> new StringBuilder()
                        .append(LocalDate.now().getYear())
                        .append("-")
                        .append(LocalDate.now().getMonthValue())
                        .toString()));
        return new ExpSetBudgetCommand(yearMonth, amount.value);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
