package seedu.address.logic.parser.repeat;

import seedu.address.logic.commands.expenditure.ExpAddCommand;
import seedu.address.logic.commands.repeat.RepeatAddCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.expenditure.Amount;
import seedu.address.model.expenditure.Date;
import seedu.address.model.expenditure.Info;
import seedu.address.model.expenditure.Repeat;
import seedu.address.model.expenditure.Repeat.Period;
import seedu.address.model.tag.Tag;

import java.time.LocalDate;
import java.util.stream.Stream;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INFO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERIOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

/**
 * Parses input arguments and creates a new RepeatAddCommand object
 */
public class RepeatAddCommandParser implements Parser<RepeatAddCommand> {

    @Override
    public RepeatAddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_INFO, PREFIX_AMOUNT, PREFIX_START_DATE,
                        PREFIX_END_DATE, PREFIX_TAG, PREFIX_PERIOD);

        if (!arePrefixesPresent(argMultimap, PREFIX_INFO, PREFIX_AMOUNT, PREFIX_START_DATE, PREFIX_END_DATE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExpAddCommand.MESSAGE_USAGE));
        }


        Info info = ParserUtil.parseInfo(argMultimap.getValue(PREFIX_INFO).get());
        Amount amount = ParserUtil.parseAmount(argMultimap.getValue(PREFIX_AMOUNT).get());
        Date startDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_START_DATE).orElseGet(() -> LocalDate.now().toString()));
        Date endDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_END_DATE).orElseGet(() -> LocalDate.now().toString()));
        Tag tag = ParserUtil.parseTag(argMultimap.getValue(PREFIX_TAG).orElseGet(() -> "Others"));
        String period = ParserUtil.parsePeriod(argMultimap.getValue(PREFIX_PERIOD).orElseThrow());
        Repeat repeat = new Repeat(info, amount, startDate, endDate, tag, period);

        return new RepeatAddCommand(repeat);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
