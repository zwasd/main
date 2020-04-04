package seedu.saveit.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.saveit.storage.JsonAdaptedExpenditure.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.saveit.testutil.Assert.assertThrows;
import static seedu.saveit.testutil.TypicalExpenditures.BENSON;

import org.junit.jupiter.api.Test;

import seedu.saveit.commons.exceptions.IllegalValueException;

import seedu.saveit.model.expenditure.Amount;
import seedu.saveit.model.expenditure.Date;
import seedu.saveit.model.expenditure.Info;

public class JsonAdaptedExpenditureTest {
    private static final String INVALID_INFO = "R@chel";
    private static final String INVALID_DATE = " ";
    private static final double INVALID_AMOUNT = -1;
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_INFO = BENSON.getInfo().toString();
    private static final double VALID_AMOUNT = BENSON.getAmount().value;
    private static final String VALID_DATE = BENSON.getDate().toString();
    private static final JsonAdaptedTag VALID_TAG = new JsonAdaptedTag(BENSON.getTag().getTagName());

    @Test
    public void toModelType_validExpenditureDetails_returnsExpenditure() throws Exception {
        JsonAdaptedExpenditure expenditure = new JsonAdaptedExpenditure(BENSON);
        assertEquals(BENSON, expenditure.toModelType());
    }

    @Test
    public void toModelType_invalidInfo_throwsIllegalValueException() {
        JsonAdaptedExpenditure expenditure =
                new JsonAdaptedExpenditure(INVALID_INFO, VALID_AMOUNT, VALID_DATE, VALID_TAG);
        String expectedMessage = Info.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, expenditure::toModelType);
    }

    @Test
    public void toModelType_nullInfo_throwsIllegalValueException() {
        JsonAdaptedExpenditure expenditure = new JsonAdaptedExpenditure(null, VALID_AMOUNT, VALID_DATE, VALID_TAG);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Info.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, expenditure::toModelType);
    }


    @Test
    public void toModelType_invalidAmount_throwsIllegalValueException() {
        JsonAdaptedExpenditure expenditure =
                new JsonAdaptedExpenditure(VALID_INFO, INVALID_AMOUNT, VALID_DATE, VALID_TAG);
        String expectedMessage = Amount.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, expenditure::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedExpenditure expenditure =
                new JsonAdaptedExpenditure(VALID_INFO, INVALID_AMOUNT, VALID_DATE, VALID_TAG);
        String expectedMessage = Amount.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, expenditure::toModelType);
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedExpenditure expenditure =
                new JsonAdaptedExpenditure(VALID_INFO, VALID_AMOUNT, INVALID_DATE, VALID_TAG);
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, expenditure::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedExpenditure expenditure = new JsonAdaptedExpenditure(VALID_INFO, VALID_AMOUNT, null, VALID_TAG);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, expenditure::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        JsonAdaptedTag invalidTag = new JsonAdaptedTag(INVALID_TAG);
        JsonAdaptedExpenditure expenditure =
                new JsonAdaptedExpenditure(VALID_INFO, VALID_AMOUNT, VALID_DATE, invalidTag);
        assertThrows(IllegalValueException.class, expenditure::toModelType);
    }

}
