package seedu.saveit.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.saveit.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.saveit.testutil.Assert.assertThrows;
import static seedu.saveit.testutil.TypicalIndexes.INDEX_FIRST_EXPENDITURE;

import org.junit.jupiter.api.Test;

import seedu.saveit.logic.parser.exceptions.ParseException;

import seedu.saveit.model.expenditure.Amount;
import seedu.saveit.model.expenditure.Date;
import seedu.saveit.model.expenditure.Info;
import seedu.saveit.model.expenditure.Tag;

public class ParserUtilTest {
    private static final String INVALID_INFO = "";
    private static final String INVALID_DATE = " ";
    private static final double INVALID_AMOUNT = -1;
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_INFO = "Rachel Walker";
    private static final String VALID_DATE = "2019-09-11";
    private static final double VALID_AMOUNT = 3.14;
    private static final String EMPTY_TAG = "";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_EXPENDITURE, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_EXPENDITURE, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseInfo_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseInfo((String) null));
    }

    @Test
    public void parseInfo_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseInfo(INVALID_INFO));
    }

    @Test
    public void parseInfo_validValueWithoutWhitespace_returnsInfo() throws Exception {
        Info expectedInfo = new Info(VALID_INFO);
        assertEquals(expectedInfo, ParserUtil.parseInfo(VALID_INFO));
    }

    @Test
    public void parseInfo_validValueWithWhitespace_returnsTrimmedInfo() throws Exception {
        String infoWithWhitespace = WHITESPACE + VALID_INFO + WHITESPACE;
        Info expectedInfo = new Info(VALID_INFO);
        assertEquals(expectedInfo, ParserUtil.parseInfo(infoWithWhitespace));
    }


    @Test
    public void parseDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDate((String) null));
    }

    @Test
    public void parseDate_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDate(INVALID_DATE));
    }

    @Test
    public void parseDate_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Date expectedDate = new Date(VALID_DATE);
        assertEquals(expectedDate, ParserUtil.parseDate(VALID_DATE));
    }

    @Test
    public void parseDate_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String dateWithWhitespace = WHITESPACE + VALID_DATE + WHITESPACE;
        Date expectedDate = new Date(VALID_DATE);
        assertEquals(expectedDate, ParserUtil.parseDate(dateWithWhitespace));
    }

    @Test
    public void parseAmount_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAmount((String) null));
    }

    @Test
    public void parseAmount_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAmount(Double.toString(INVALID_AMOUNT)));
    }

    @Test
    public void parseAmount_validValueWithoutWhitespace_returnsAmount() throws Exception {
        Amount expectedAmount = new Amount(VALID_AMOUNT);
        assertEquals(expectedAmount, ParserUtil.parseAmount(Double.toString(VALID_AMOUNT)));
    }

    @Test
    public void parseAmount_validValueWithWhitespace_returnsTrimmedAmount() throws Exception {
        String amountWithWhitespace = WHITESPACE + VALID_AMOUNT + WHITESPACE;
        Amount expectedAmount = new Amount(VALID_AMOUNT);
        assertEquals(expectedAmount, ParserUtil.parseAmount(amountWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }
}
