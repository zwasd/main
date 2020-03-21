package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INFO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.expenditure.ExpAddCommand;
import seedu.address.logic.commands.expenditure.ExpEditCommand.EditExpenditureDescriptor;
import seedu.address.model.expenditure.Expenditure;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Expenditure.
 */
public class ExpenditureUtil {

    /**
     * Returns an add command string for adding the {@code expenditure}.
     */
    public static String getAddCommand(Expenditure expenditure) {
        return ExpAddCommand.COMMAND_WORD + " " + getExpenditureDetails(expenditure);
    }

    /**
     * Returns the part of command string for the given {@code expenditure}'s details.
     */
    public static String getExpenditureDetails(Expenditure expenditure) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_INFO + expenditure.getInfo().fullInfo + " ");
        sb.append(PREFIX_AMOUNT + Double.toString(expenditure.getAmount().value) + " ");
        sb.append(PREFIX_DATE + expenditure.getDate().value + " ");
        sb.append(PREFIX_TAG + expenditure.getTag().tagName + " ");

        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditExpenditureDescriptor}'s details.
     */
    public static String getEditExpenditureDescriptorDetails(EditExpenditureDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getInfo().ifPresent(info -> sb.append(PREFIX_INFO).append(info.fullInfo).append(" "));
        descriptor.getAmount().ifPresent(amount -> sb.append(PREFIX_AMOUNT).append(amount.value).append(" "));
        descriptor.getDate().ifPresent(date -> sb.append(PREFIX_DATE).append(date.value).append(" "));
        descriptor.getTag().ifPresent(tag -> sb.append(PREFIX_TAG).append(tag.tagName).append(" "));
        return sb.toString();
    }
}
