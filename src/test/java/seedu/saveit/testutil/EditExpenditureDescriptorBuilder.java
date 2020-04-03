package seedu.saveit.testutil;

import seedu.saveit.logic.commands.expenditure.ExpEditCommand.EditExpenditureDescriptor;

import seedu.saveit.model.expenditure.Amount;
import seedu.saveit.model.expenditure.Date;
import seedu.saveit.model.expenditure.Expenditure;
import seedu.saveit.model.expenditure.Info;
import seedu.saveit.model.expenditure.Tag;

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
        descriptor.setTag(expenditure.getTag());
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
    public EditExpenditureDescriptorBuilder withTag(String tag) {
        descriptor.setTag(new Tag(tag));
        return this;
    }

    public EditExpenditureDescriptor build() {
        return descriptor;
    }
}
