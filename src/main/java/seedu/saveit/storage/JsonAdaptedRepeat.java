package seedu.saveit.storage;

import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.saveit.commons.exceptions.IllegalValueException;
import seedu.saveit.model.expenditure.Amount;
import seedu.saveit.model.expenditure.Date;
import seedu.saveit.model.expenditure.Info;
import seedu.saveit.model.expenditure.Repeat;
import seedu.saveit.model.expenditure.Tag;

/**
 * Jackson-friendly version of {@link Repeat}.
 */
class JsonAdaptedRepeat {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Repeat's %s field is missing!";

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_DATE;

    private final String startDate;
    private final String endDate;
    private final String info;
    private final double amount;
    private JsonAdaptedTag tag;
    private String period;

    /**
     * Constructs a {@code JsonAdaptedRepeat} with the given repeat details.
     */
    @JsonCreator

    public JsonAdaptedRepeat(@JsonProperty("info") String info, @JsonProperty("amount") double amount,
                             @JsonProperty("startDate") String startDate, @JsonProperty("endDate") String endDate,
                             @JsonProperty("period") String period, @JsonProperty("tag") JsonAdaptedTag tag) {

        this.info = info;
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tag = tag;
        this.period = period;
    }

    /**
     * Converts a given {@code Repeat} into this class for Jackson use.
     */

    public JsonAdaptedRepeat(Repeat source) {
        info = source.getInfo().fullInfo;
        amount = source.getAmount().value;
        startDate = source.getStartDate().value;
        endDate = source.getEndDate().value;
        tag = new JsonAdaptedTag(source.getTag().tagName);
        period = source.getPeriod().toString();
    }

    /**
     * Converts this Jackson-friendly adapted repeat object into the model's {@code Repeat} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted repeat.
     */
    public Repeat toModelType() throws IllegalValueException {

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

        if (startDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "startDate"));
        }
        if (!Date.isValidDate(startDate)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        final Date modelStartDate = new Date(startDate);

        if (endDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "endDate"));
        }
        if (!Date.isValidDate(endDate)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        final Date modelEndDate = new Date(endDate);

        if (tag == null) {
            tag = new JsonAdaptedTag("Others");
        }

        if (!Tag.isValidTagName(tag.getTagName())) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }

        if (period == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Repeat.Period.class.getSimpleName()));
        }
        if (!Repeat.Period.isValidPeriod(period)) {
            throw new IllegalValueException(Repeat.PERIOD_MESSAGE_CONSTRAINTS);
        }

        return new Repeat(modelInfo, modelAmount, modelStartDate, modelEndDate, tag.toModelType(), period);

    }

}
