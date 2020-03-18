package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.expenditure.ExpEditCommand.EditExpenditureDescriptor;

import seedu.address.model.expenditure.Amount;
import seedu.address.model.expenditure.Date;
import seedu.address.model.expenditure.Expenditure;
import seedu.address.model.expenditure.Info;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditExpenditureDescriptor objects.
 */
public class EditExpenditureDescriptorBuilder {

    private EditExpenditureDescriptor descriptor;

    public EditExpenditureDescriptorBuilder() {
        descriptor = new EditExpenditureDescriptor();
    }

    public EditExpenditureDescriptorBuilder(EditExpenditureDescriptor descriptor) {
        this.descriptor = new EditExpenditureDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditExpenditureDescriptor} with fields containing {@code expenditure}'s details
     */
    public EditExpenditureDescriptorBuilder(Expenditure expenditure) {
        descriptor = new EditExpenditureDescriptor();
        descriptor.setInfo(expenditure.getInfo());
        descriptor.setAmount(expenditure.getAmount());
        descriptor.setDate(expenditure.getDate());
        descriptor.setTags(expenditure.getTags());
    }

    /**
     * Sets the {@code Info} of the {@code EditExpenditureDescriptor} that we are building.
     */
    public EditExpenditureDescriptorBuilder withInfo(String info) {
        descriptor.setInfo(new Info(info));
        return this;
    }

    /**
     * Sets the {@code Amount} of the {@code EditExpenditureDescriptor} that we are building.
     */
    public EditExpenditureDescriptorBuilder withAmount(double amount) {
        descriptor.setAmount(new Amount(amount));
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code EditExpenditureDescriptor} that we are building.
     */
    public EditExpenditureDescriptorBuilder withDate(String date) {
        descriptor.setDate(new Date(date));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditExpenditureDescriptor}
     * that we are building.
     */
    public EditExpenditureDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditExpenditureDescriptor build() {
        return descriptor;
    }
}
