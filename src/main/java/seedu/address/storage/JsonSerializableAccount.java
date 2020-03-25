package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;

import seedu.address.model.Account;
import seedu.address.model.expenditure.Expenditure;
import seedu.address.model.expenditure.Repeat;

/**
 * An Immutable Account that is serializable to JSON format.
 */
@JsonRootName(value = "saveit")
class JsonSerializableAccount {

    public static final String MESSAGE_DUPLICATE_EXPENDITURE = "Expenditures list contains duplicate expenditure(s).";

    private final List<JsonAdaptedExpenditure> expenditures = new ArrayList<>();
    private final List<JsonAdaptedRepeat> repeats = new ArrayList<>();
    private final String accountName;

    /**
     * Constructs a {@code JsonSerializableAccount} with the given expenditures and accountName.
     */
    @JsonCreator
    public JsonSerializableAccount(@JsonProperty("accountName") String accountName,
                                   @JsonProperty("expenditures") List<JsonAdaptedExpenditure> expenditures,
                                   @JsonProperty("repeats") List<JsonAdaptedRepeat> repeats) {
        this.accountName = accountName;
        this.expenditures.addAll(expenditures);
        this.repeats.addAll(repeats);
    }

    /**
     * Converts a given {@code Account} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAccount}.
     */
    public JsonSerializableAccount(Account source) {
        expenditures.addAll(source.getExpenditureList().stream().map(JsonAdaptedExpenditure::new)
                .collect(Collectors.toList()));
        repeats.addAll(source.getRepeatList().stream().map(JsonAdaptedRepeat::new).collect(Collectors.toList()));
        accountName = source.getAccountName();
    }

    /**
     * Converts this address book into the model's {@code Account} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Account toModelType() throws IllegalValueException {
        Account account = new Account(accountName);
        for (JsonAdaptedExpenditure jsonAdaptedExpenditure : expenditures) {
            Expenditure expenditure = jsonAdaptedExpenditure.toModelType();
            account.addExpenditure(expenditure);
        }
        for (JsonAdaptedRepeat jsonAdaptedRepeat : repeats) {
            Repeat repeat = jsonAdaptedRepeat.toModelType();
            account.addRepeat(repeat);
        }
        return account;
    }

}
