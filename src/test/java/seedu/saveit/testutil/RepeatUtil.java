package seedu.saveit.testutil;

import static seedu.saveit.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_INFO;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_PERIOD;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.saveit.logic.commands.repeat.RepeatAddCommand;
import seedu.saveit.logic.commands.repeat.RepeatEditCommand;
import seedu.saveit.model.expenditure.Repeat;

/**
 * A utility class for RepeatUtil.
 */
public class RepeatUtil {
    /**
     * Returns an add command string for adding the {@code expenditure}.
     */
    public static String getAddCommand(Repeat repeat) {
        return RepeatAddCommand.COMMAND_WORD + " " + getRepeatDetails(repeat);
    }

    /**
     * Returns the part of command string for the given {@code repeat}'s details.
     */
    public static String getRepeatDetails(Repeat repeat) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_INFO + repeat.getInfo().fullInfo + " ");
        sb.append(PREFIX_AMOUNT + Double.toString(repeat.getAmount().value) + " ");
        sb.append(PREFIX_START_DATE + repeat.getStartDate().localDate.toString() + " ");
        sb.append(PREFIX_END_DATE + repeat.getEndDate().localDate.toString() + " ");
        sb.append(PREFIX_TAG + repeat.getTag().tagName + " ");
        sb.append(PREFIX_PERIOD + repeat.getPeriod().toString() + " ");

        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditExpenditureDescriptor}'s details.
     */
    public static String getEditRepeatDescriptorDetails(RepeatEditCommand.EditRepeatDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getInfo().ifPresent(info -> sb.append(PREFIX_INFO).append(info.fullInfo).append(" "));
        descriptor.getAmount().ifPresent(amount -> sb.append(PREFIX_AMOUNT).append(amount.value).append(" "));
        descriptor.getStartDate().ifPresent(startDate -> sb.append(PREFIX_START_DATE)
                .append(startDate.localDate.toString()).append(" "));
        descriptor.getEndDate().ifPresent(endDate -> sb.append(PREFIX_END_DATE)
                .append(endDate.localDate.toString()).append(" "));
        descriptor.getTag().ifPresent(tag -> sb.append(PREFIX_TAG).append(tag.tagName).append(" "));
        descriptor.getPeriod().ifPresent(period -> sb.append(PREFIX_PERIOD).append(period.toString()).append(" "));
        return sb.toString();
    }
}
