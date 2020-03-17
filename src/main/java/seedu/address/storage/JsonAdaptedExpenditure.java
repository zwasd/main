package seedu.address.storage;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;

import seedu.address.model.expenditure.Amount;
import seedu.address.model.expenditure.Date;
import seedu.address.model.expenditure.Expenditure;
import seedu.address.model.expenditure.Info;

import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Expenditure}.
 */
class JsonAdaptedExpenditure {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Expenditure's %s field is missing!";


    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_DATE;

    private final String date;
    private final String info;
    private final double amount;

    private final List<JsonAdaptedTag> tag = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedExpenditure} with the given expenditure details.
     */
    @JsonCreator

    public JsonAdaptedExpenditure(@JsonProperty("info") String info, @JsonProperty("amount") double amount,
                                  @JsonProperty("date") String date, @JsonProperty("tag") List<JsonAdaptedTag> tagged) {

        this.info = info;
        this.amount = amount;
        this.date = date;
        if (tagged != null) {
            this.tag.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Expenditure} into this class for Jackson use.
     */

    public JsonAdaptedExpenditure(Expenditure source) {
        info = source.getInfo().fullInfo;
        amount = source.getAmount().value;
        date = source.getDate().value;

        tag.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted expenditure object into the model's {@code Expenditure} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted expenditure.
     */
    public Expenditure toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tag) {
            personTags.add(tag.toModelType());
        }

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
        if (!seedu.address.model.expenditure.Date.isValidDate(date)) {
            throw new IllegalValueException(seedu.address.model.expenditure.Date.MESSAGE_CONSTRAINTS);
        }
        final Date modelDate = new Date(date);

        final Set<Tag> modelTags = new HashSet<>(personTags);

        return new Expenditure(modelInfo, modelAmount, modelDate, modelTags);

    }

}
