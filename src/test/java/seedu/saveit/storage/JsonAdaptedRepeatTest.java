package seedu.saveit.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.saveit.testutil.Assert.assertThrows;
import static seedu.saveit.testutil.TypicalRepeats.BENSON;

import org.junit.jupiter.api.Test;

import seedu.saveit.commons.exceptions.IllegalValueException;
import seedu.saveit.model.expenditure.Amount;
import seedu.saveit.model.expenditure.Date;
import seedu.saveit.model.expenditure.Info;
import seedu.saveit.model.expenditure.Repeat;
import seedu.saveit.model.expenditure.Tag;
import seedu.saveit.testutil.TypicalRepeats;

public class JsonAdaptedRepeatTest {
    private static final String INVALID_INFO = "F@od";
    private static final String INVALID_START_DATE = " ";
    private static final String INVALID_END_DATE = "2020-02-31";
    private static final double INVALID_AMOUNT = -1;
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_PERIOD = "hourly";

    private static final String VALID_INFO = BENSON.getInfo().toString();
    private static final double VALID_AMOUNT = BENSON.getAmount().value;
    private static final String VALID_START_DATE = BENSON.getStartDate().toString();
    private static final String VALID_END_DATE = BENSON.getEndDate().toString();
    private static final JsonAdaptedTag VALID_TAG = new JsonAdaptedTag(BENSON.getTag().getTagName());
    private static final String VALID_PERIOD = BENSON.getPeriod().toString();

    @Test
    public void toModelType_validRepeatDetails_returnsRepeat() throws Exception {
        JsonAdaptedRepeat repeat = new JsonAdaptedRepeat(TypicalRepeats.BENSON);
        assertEquals(TypicalRepeats.BENSON, repeat.toModelType());
    }


    @Test
    public void toModelType_invalidInfo_throwsIllegalValueException() {
        JsonAdaptedRepeat repeat =
                new JsonAdaptedRepeat(INVALID_INFO, VALID_AMOUNT, VALID_START_DATE, VALID_END_DATE,
                        VALID_PERIOD, VALID_TAG);
        String expectedMessage = Info.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, repeat::toModelType);
    }

    @Test
    public void toModelType_nullInfo_throwsIllegalValueException() {
        JsonAdaptedRepeat repeat =
                new JsonAdaptedRepeat(null, VALID_AMOUNT, VALID_START_DATE, VALID_END_DATE,
                        VALID_PERIOD, VALID_TAG);
        assertThrows(IllegalValueException.class, repeat::toModelType);
    }

    @Test
    public void toModelType_invalidAmount_throwsIllegalValueException() {
        JsonAdaptedRepeat repeat =
                new JsonAdaptedRepeat(VALID_INFO, INVALID_AMOUNT, VALID_START_DATE, VALID_END_DATE,
                        VALID_PERIOD, VALID_TAG);
        String expectedMessage = Amount.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, repeat::toModelType);
    }

    @Test
    public void toModelType_nullAmount_throwsIllegalValueException() {
        JsonAdaptedRepeat repeat =
                new JsonAdaptedRepeat(VALID_INFO, INVALID_AMOUNT, VALID_START_DATE, VALID_END_DATE,
                        VALID_PERIOD, VALID_TAG);
        assertThrows(IllegalValueException.class, repeat::toModelType);
    }

    @Test
    public void toModelType_invalidStartDate_throwsIllegalValueException() {
        JsonAdaptedRepeat repeat =
                new JsonAdaptedRepeat(VALID_INFO, VALID_AMOUNT, INVALID_START_DATE, VALID_END_DATE,
                        VALID_PERIOD, VALID_TAG);
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, repeat::toModelType);
    }

    @Test
    public void toModelType_nullStartDate_throwsIllegalValueException() {
        JsonAdaptedRepeat repeat =
                new JsonAdaptedRepeat(VALID_INFO, VALID_AMOUNT, null, VALID_END_DATE,
                        VALID_PERIOD, VALID_TAG);
        assertThrows(IllegalValueException.class, repeat::toModelType);
    }

    @Test
    public void toModelType_invalidEndDate_throwsIllegalValueException() {
        JsonAdaptedRepeat repeat =
                new JsonAdaptedRepeat(VALID_INFO, VALID_AMOUNT, VALID_START_DATE, INVALID_END_DATE,
                        VALID_PERIOD, VALID_TAG);
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, repeat::toModelType);
    }

    @Test
    public void toModelType_nullEndDate_throwsIllegalValueException() {
        JsonAdaptedRepeat repeat =
                new JsonAdaptedRepeat(VALID_INFO, VALID_AMOUNT, VALID_START_DATE, null,
                        VALID_PERIOD, VALID_TAG);
        assertThrows(IllegalValueException.class, repeat::toModelType);
    }

    @Test
    public void toModelType_invalidTag_throwsIllegalValueException() {
        JsonAdaptedRepeat repeat =
                new JsonAdaptedRepeat(VALID_INFO, VALID_AMOUNT, VALID_START_DATE, VALID_END_DATE,
                        VALID_PERIOD, new JsonAdaptedTag(INVALID_TAG));
        String expectedMessage = Tag.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, repeat::toModelType);
    }

    @Test
    public void toModelType_invalidPeriod_throwsIllegalValueException() {
        JsonAdaptedRepeat repeat =
                new JsonAdaptedRepeat(VALID_INFO, VALID_AMOUNT, VALID_START_DATE, VALID_END_DATE,
                        INVALID_PERIOD, VALID_TAG);
        String expectedMessage = Repeat.PERIOD_MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, repeat::toModelType);
    }

    @Test
    public void toModelType_nullPeriod_throwsIllegalValueException() {
        JsonAdaptedRepeat repeat =
                new JsonAdaptedRepeat(VALID_INFO, VALID_AMOUNT, VALID_START_DATE, VALID_END_DATE,
                        null, VALID_TAG);
        assertThrows(IllegalValueException.class, repeat::toModelType);
    }


}
