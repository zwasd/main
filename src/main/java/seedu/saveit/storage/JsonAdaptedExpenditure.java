package seedu.saveit.storage;

import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.saveit.commons.exceptions.IllegalValueException;

import seedu.saveit.model.expenditure.Amount;
import seedu.saveit.model.expenditure.Date;
import seedu.saveit.model.expenditure.Expenditure;
import seedu.saveit.model.expenditure.Info;

import seedu.saveit.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Expenditure}.
 */
class JsonAdaptedExpenditure {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Expenditure's %s field is missing!";


    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_DATE;

    private final String date;
    private final String info;
    private final double amount;

    private JsonAdaptedTag tag;

    /**
     * Constructs a {@code JsonAdaptedExpenditure} with the given expenditure details.
     */
    @JsonCreator

    public JsonAdaptedExpenditure(@JsonProperty("info") String info, @JsonProperty("amount") double amount,
                                  @JsonProperty("date") String date, @JsonProperty("tag") JsonAdaptedTag tag) {

        this.info = info;
        this.amount = amount;
        this.date = date;
        if (tag != null) {
            this.tag = tag;
        }
    }

    /**
     * Converts a given {@code Expenditure} into this class for Jackson use.
     */

    public JsonAdaptedExpenditure(Expenditure source) {
        info = source.getInfo().fullInfo;
        amount = source.getAmount().value;
        date = source.getDate().value;
        tag = new JsonAdaptedTag(source.getTag().tagName);
    }

    /**
     * Converts this Jackson-friendly adapted expenditure object into the model's {@code Expenditure} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted expenditure.
     */
    public Expenditure toModelType() throws IllegalValueException {

        if (info == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Info.class.getSimpleName()));
        }
        if (!Info.isValidInfo(info)) {
            throw new IllegalValueException(Info.MESSAGE_CONSTRAINTS);
        }
        final Info modelInfo = new Info(info);

        if (!Amount.isValidAmount(amount)) {
            throw new IllegalValueException(Amount.MESSAGE_CONSTRAINTS);
        }
        final Amount modelAmount = new Amount(amount);

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName()));
        }
        if (!seedu.saveit.model.expenditure.Date.isValidDate(date)) {
            throw new IllegalValueException(seedu.saveit.model.expenditure.Date.MESSAGE_CONSTRAINTS);
        }
        final Date modelDate = new Date(date);

        if (tag == null) {
            tag = new JsonAdaptedTag("Others");
        }

        if (!seedu.saveit.model.tag.Tag.isValidTagName(tag.getTagName())) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }

        return new Expenditure(modelInfo, modelAmount, modelDate, tag.toModelType());

    }

}
