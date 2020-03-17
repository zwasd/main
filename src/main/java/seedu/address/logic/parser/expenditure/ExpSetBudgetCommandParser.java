package seedu.address.logic.parser.expenditure;

import seedu.address.logic.commands.expenditure.ExpAddCommand;
import seedu.address.logic.commands.expenditure.ExpSetBudgetCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.expenditure.Amount;
import seedu.address.model.expenditure.Date;
import seedu.address.model.expenditure.Info;
import seedu.address.model.tag.Tag;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Set;
import java.util.stream.Stream;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

/**
 * Parse set budget.
 */
public class ExpSetBudgetCommandParser implements Parser<ExpSetBudgetCommand> {
    public ExpSetBudgetCommandParser() {
    }

    @Override
    public ExpSetBudgetCommand parse(String userInput) throws ParseException {

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_AMOUNT, PREFIX_DATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_AMOUNT, PREFIX_AMOUNT)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExpSetBudgetCommand.MESSAGE_FAIL));
        }

        Amount amount = ParserUtil.parseAmount(argMultimap.getValue(PREFIX_AMOUNT).get());
        Date date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).orElseGet(() -> LocalDate.now().toString()));

        return new ExpSetBudgetCommand(date, amount);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
