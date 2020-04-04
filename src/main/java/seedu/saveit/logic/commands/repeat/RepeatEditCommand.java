package seedu.saveit.logic.commands.repeat;

import static java.util.Objects.requireNonNull;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_INFO;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_PERIOD;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;
import java.util.Optional;

import seedu.saveit.commons.core.Messages;
import seedu.saveit.commons.core.index.Index;
import seedu.saveit.commons.util.CollectionUtil;
import seedu.saveit.logic.commands.Command;
import seedu.saveit.logic.commands.CommandResult;
import seedu.saveit.logic.commands.exceptions.CommandException;
import seedu.saveit.logic.parser.repeat.RepeatLevelParser;
import seedu.saveit.model.Model;
import seedu.saveit.model.MonthlySpendingCalculator;
import seedu.saveit.model.expenditure.Amount;
import seedu.saveit.model.expenditure.BaseExp;
import seedu.saveit.model.expenditure.Date;
import seedu.saveit.model.expenditure.Info;
import seedu.saveit.model.expenditure.Repeat;
import seedu.saveit.model.expenditure.Repeat.Period;
import seedu.saveit.model.expenditure.Tag;

/**
 * Edit repeat object.
 * TODO: NEED MODIFY
 */
public class RepeatEditCommand extends Command {
    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = RepeatLevelParser.COMMAND_WORD + " " + COMMAND_WORD
            + ": Edits the details of the repeat identified "
            + "by the index number used in the displayed list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_INFO + "INFO] "
            + "[" + PREFIX_AMOUNT + "AMOUNT] "
            + "[" + PREFIX_START_DATE + "STARTDATE] "
            + "[" + PREFIX_END_DATE + "ENDDATE] "
            + "[" + PREFIX_PERIOD + "[daily|monthly|weekly|annually] ]"
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + RepeatLevelParser.COMMAND_WORD + " " + COMMAND_WORD + " 1 "
            + PREFIX_AMOUNT + "4.3";

    public static final String MESSAGE_EDIT_REPEAT_SUCCESS = "Edited Repeat: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";


    private final Index index;
    private final RepeatEditCommand.EditRepeatDescriptor editRepeatDescriptor;

    public RepeatEditCommand(Index index, RepeatEditCommand.EditRepeatDescriptor editRepeatDescriptor) {
        requireNonNull(index);
        requireNonNull(editRepeatDescriptor);

        this.index = index;
        this.editRepeatDescriptor = new RepeatEditCommand.EditRepeatDescriptor(editRepeatDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<BaseExp> lastShownList = model.getFilteredBaseExpList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EXPENDITURE_DISPLAYED_INDEX);
        }

        BaseExp baseExp = lastShownList.get(index.getZeroBased());
        if (!(baseExp instanceof Repeat)) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_TYPE_AT_INDEX,
                    Repeat.class.getSimpleName()));
        }
        Repeat repeatToEdit = (Repeat) baseExp;
        Repeat editedRepeat = createEditedRepeat(repeatToEdit, editRepeatDescriptor);

        model.setRepeat(repeatToEdit, editedRepeat);
        MonthlySpendingCalculator monthlyCalculator = model.getMonthlySpending();
        return new CommandResult(String.format(MESSAGE_EDIT_REPEAT_SUCCESS, editedRepeat),
                monthlyCalculator.getBudget(), monthlyCalculator.getTotalSpending());

    }

    /**
     * Creates and returns a {@code Repeat} with the details of {@code repeatToEdit}
     * edited with {@code editRepeatDescriptor}.
     */
    private static Repeat createEditedRepeat(Repeat repeatToEdit,
                                                       RepeatEditCommand.EditRepeatDescriptor editRepeatDescriptor) {
        assert repeatToEdit != null;

        Info updatedInfo = editRepeatDescriptor.getInfo().orElse(repeatToEdit.getInfo());
        Amount updatedAmount = editRepeatDescriptor.getAmount().orElse(repeatToEdit.getAmount());
        Date updatedStartDate = editRepeatDescriptor.getStartDate().orElse(repeatToEdit.getStartDate());
        Date updatedEndDate = editRepeatDescriptor.getEndDate().orElse(repeatToEdit.getEndDate());
        Tag updatedTags = editRepeatDescriptor.getTag().orElse(repeatToEdit.getTag());
        Period updatedPeriod = editRepeatDescriptor.getPeriod().orElse(repeatToEdit.getPeriod());
        return new Repeat(updatedInfo, updatedAmount, updatedStartDate, updatedEndDate, updatedTags,
                updatedPeriod.toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RepeatEditCommand)) {
            return false;
        }

        // state check
        RepeatEditCommand e = (RepeatEditCommand) other;
        return index.equals(e.index)
                && editRepeatDescriptor.equals(e.editRepeatDescriptor);
    }

    /**
     * Stores the details to edit the Repeat with. Each non-empty field value will replace the
     * corresponding field value of the repeat.
     */
    public static class EditRepeatDescriptor {
        private Info info;
        private Amount amount;
        private Date startDate;
        private Date endDate;
        private Tag tag;
        private Period period;

        public EditRepeatDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditRepeatDescriptor(RepeatEditCommand.EditRepeatDescriptor toCopy) {
            setInfo(toCopy.info);
            setAmount(toCopy.amount);
            setStartDate(toCopy.startDate);
            setEndDate(toCopy.endDate);
            setTag(toCopy.tag);
            setPeriod(toCopy.period);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(info, amount, startDate, endDate, period, tag);
        }

        public void setInfo(Info info) {
            this.info = info;
        }

        public Optional<Info> getInfo() {
            return Optional.ofNullable(info);
        }

        public void setAmount(Amount amount) {
            this.amount = amount;
        }

        public Optional<Amount> getAmount() {
            return Optional.ofNullable(amount);
        }

        public void setStartDate(Date date) {
            this.startDate = date;
        }

        public void setEndDate(Date date) {
            this.endDate = date;
        }

        public void setPeriod(Period period) {
            this.period = period;
        }

        public void setPeriod(String duration) {
            if (duration.equalsIgnoreCase("daily")) {
                this.period = Period.DAILY;
            } else if (duration.equalsIgnoreCase("monthly")) {
                this.period = Period.MONTHLY;
            } else if (duration.equalsIgnoreCase("weekly")) {
                this.period = Period.WEEKLY;
            } else if (duration.equalsIgnoreCase("annually")) {
                this.period = Period.ANNUALLY;
            }
        }

        public Optional<Period> getPeriod() {
            return Optional.ofNullable(period);
        }

        public Optional<Date> getStartDate() {
            return Optional.ofNullable(startDate);
        }

        public Optional<Date> getEndDate() {
            return Optional.ofNullable(endDate);
        }

        public Optional<Tag> getTag() {
            return Optional.ofNullable(tag);
        }

        public void setTag(Tag tag) {
            this.tag = tag;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof RepeatEditCommand.EditRepeatDescriptor)) {
                return false;
            }

            // state check
            RepeatEditCommand.EditRepeatDescriptor e = (RepeatEditCommand.EditRepeatDescriptor) other;

            return getInfo().equals(e.getInfo())
                    && getAmount().equals(e.getAmount())
                    && getStartDate().equals(e.getStartDate())
                    && getEndDate().equals(e.getEndDate())
                    && getPeriod().equals(e.getPeriod())
                    && getTag().equals(e.getTag());

        }
    }

}
