package seedu.saveit.testutil;

import seedu.saveit.logic.commands.repeat.RepeatEditCommand;
import seedu.saveit.model.expenditure.Amount;
import seedu.saveit.model.expenditure.Date;
import seedu.saveit.model.expenditure.Info;
import seedu.saveit.model.expenditure.Repeat;
import seedu.saveit.model.expenditure.Tag;

/**
 * A utility class to help with building EditRepeatDescriptor objects.
 */
public class EditRepeatDescriptorBuilder {
    private RepeatEditCommand.EditRepeatDescriptor descriptor;

    public EditRepeatDescriptorBuilder() {
        descriptor = new RepeatEditCommand.EditRepeatDescriptor();
    }

    public EditRepeatDescriptorBuilder(RepeatEditCommand.EditRepeatDescriptor descriptor) {
        this.descriptor = new RepeatEditCommand.EditRepeatDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditRepeatDescriptor} with fields containing {@code repeat}'s details
     */
    public EditRepeatDescriptorBuilder(Repeat repeat) {
        descriptor = new RepeatEditCommand.EditRepeatDescriptor();
        descriptor.setInfo(repeat.getInfo());
        descriptor.setAmount(repeat.getAmount());
        descriptor.setStartDate(repeat.getStartDate());
        descriptor.setEndDate(repeat.getEndDate());
        descriptor.setTag(repeat.getTag());
        descriptor.setPeriod(repeat.getPeriod());
    }


    /**
     * Sets the {@code Info} of the {@code EditRepeatDescriptor} that we are building.
     */
    public EditRepeatDescriptorBuilder withInfo(String info) {
        descriptor.setInfo(new Info(info));
        return this;
    }

    /**
     * Sets the {@code Amount} of the {@code EditRepeatDescriptor} that we are building.
     */
    public EditRepeatDescriptorBuilder withAmount(double amount) {
        descriptor.setAmount(new Amount(amount));
        return this;
    }

    /**
     * Sets the {@code startDate} of the {@code EditRepeatDescriptor} that we are building.
     */
    public EditRepeatDescriptorBuilder withStartDate(String startDate) {
        descriptor.setStartDate(new Date(startDate));
        return this;
    }

    /**
     * Sets the {@code endDate} of the {@code EditRepeatDescriptor} that we are building.
     */
    public EditRepeatDescriptorBuilder withEndDate(String endDate) {
        descriptor.setEndDate(new Date(endDate));
        return this;
    }


    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditRepeatDescriptor}
     * that we are building.
     */
    public EditRepeatDescriptorBuilder withTag(String tag) {
        descriptor.setTag(new Tag(tag));
        return this;
    }


    /**
     * Parses the {@code period} into a {@code period} and set it to the {@code EditRepeatDescriptor}
     * that we are building.
     */
    public EditRepeatDescriptorBuilder withPeriod(String period) {
        descriptor.setPeriod(Repeat.Period.valueOf(period));
        return this;
    }

    public RepeatEditCommand.EditRepeatDescriptor build() {
        return descriptor;
    }
}
