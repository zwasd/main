package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.YearMonth;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Report;
import seedu.address.model.expenditure.Amount;
import seedu.address.model.expenditure.Date;
import seedu.address.model.expenditure.Info;
import seedu.address.model.expenditure.Repeat;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String info} into a {@code Info}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code info} is invalid.
     */
    public static Info parseInfo(String info) throws ParseException {
        requireNonNull(info);
        String trimmedInfo = info.trim();
        if (!Info.isValidInfo(trimmedInfo)) {
            throw new ParseException(Info.MESSAGE_CONSTRAINTS);
        }
        return new Info(trimmedInfo);
    }


    /**
     * Parses a {@code String address} into an {@code Date}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Date parseDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        if (!seedu.address.model.expenditure.Date.isValidDate(trimmedDate)) {
            throw new ParseException(seedu.address.model.expenditure.Date.MESSAGE_CONSTRAINTS);
        }
        return new Date(trimmedDate);
    }

    /**
     * Parses a {@code String amount} into an {@code Amount}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code amount} is invalid.
     */
    public static Amount parseAmount(String amount) throws ParseException {
        requireNonNull(amount);
        String trimmedAmount = amount.trim();
        if (!Amount.isValidAmount(trimmedAmount)) {
            throw new ParseException(Amount.MESSAGE_CONSTRAINTS);
        }
        return new Amount(trimmedAmount);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses a {@code String period} into a {@code Period}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code period} is invalid.
     */
    public static String parsePeriod(String period) throws ParseException {
        requireNonNull(period);
        String trimmedPeriod = period.trim();
        if (!Repeat.Period.isValidPeriod(trimmedPeriod)) {
            throw new ParseException(Repeat.PERIOD_MESSAGE_CONSTRAINTS);
        }
        return trimmedPeriod;
    }

    /**
     * Parses a {@code String yearMonth} into a {@code YearMonth}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code yearMonth} is invalid.
     */
    public static YearMonth parseYearMonth(String yearMonth) throws ParseException {
        requireNonNull(yearMonth);
        if (yearMonth.equalsIgnoreCase("")) {
            return null;
        }
        String[] trimmedYearMonth = yearMonth.trim().split("-");
        try {
            if (trimmedYearMonth.length >= 3) {
                throw new ParseException("YearMonth should be in a format of YYYY-MM: E.g 2019-02");
            }
            int year = Integer.parseInt(trimmedYearMonth[0]);
            int month = Integer.parseInt(trimmedYearMonth[1]);
            return YearMonth.of(year, month);
        } catch (Exception e) {
            throw new ParseException("YearMonth should be in a format of YYYY-MM: E.g 2019-02");
        }
    }

    /**
     * Parses a {@code String graph} into a {@code Report.GraphType}/.
     * Trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the give {@code graph} is invalid.
     */
    public static Report.GraphType parseGraph(String graph) throws ParseException {
        requireNonNull(graph);
        String trimmedGraph = graph.trim();
        if (!Report.GraphType.isValidGraph(graph)) {
            throw new ParseException(Report.GraphType.GRAPH_TYPE_MESSAGE_CONSTRAINT);
        }
        return Report.GraphType.mapToGraphType(graph);
    }

}
