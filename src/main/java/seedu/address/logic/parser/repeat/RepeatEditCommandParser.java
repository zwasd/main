package seedu.address.logic.parser.repeat;

import static java.util.Objects.requireNonNull;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INFO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERIOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.expenditure.ExpEditCommand;
import seedu.address.logic.commands.repeat.RepeatEditCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new RepeatEditCommand object
 */
public class RepeatEditCommandParser implements Parser<RepeatEditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RepeatEditCommand
     * and returns an RepeatEditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public RepeatEditCommand parse(String args) throws ParseException {

        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_INFO, PREFIX_AMOUNT, PREFIX_START_DATE,
                        PREFIX_END_DATE, PREFIX_TAG, PREFIX_PERIOD);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExpEditCommand.MESSAGE_USAGE), pe);
        }

        RepeatEditCommand.EditRepeatDescriptor editRepeatDescriptor = new RepeatEditCommand.EditRepeatDescriptor();
        if (argMultimap.getValue(PREFIX_INFO).isPresent()) {
            editRepeatDescriptor.setInfo(ParserUtil.parseInfo(argMultimap.getValue(PREFIX_INFO).get()));
        }
        if (argMultimap.getValue(PREFIX_AMOUNT).isPresent()) {
            editRepeatDescriptor.setAmount(ParserUtil.parseAmount(argMultimap.getValue(PREFIX_AMOUNT).get()));
        }
        if (argMultimap.getValue(PREFIX_START_DATE).isPresent()) {
            editRepeatDescriptor.setStartDate(ParserUtil.parseDate(argMultimap.getValue(PREFIX_START_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_END_DATE).isPresent()) {
            editRepeatDescriptor.setStartDate(ParserUtil.parseDate(argMultimap.getValue(PREFIX_END_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            editRepeatDescriptor.setTag(ParserUtil.parseTag(argMultimap.getValue(PREFIX_TAG).get()));
        }
        if (argMultimap.getValue(PREFIX_PERIOD).isPresent()) {
            editRepeatDescriptor.setPeriod(ParserUtil.parsePeriod(argMultimap.getValue(PREFIX_PERIOD).get()));
        }

        if (!editRepeatDescriptor.isAnyFieldEdited()) {
            throw new ParseException(RepeatEditCommand.MESSAGE_NOT_EDITED);
        }

        return new RepeatEditCommand(index, editRepeatDescriptor);
    }
}
