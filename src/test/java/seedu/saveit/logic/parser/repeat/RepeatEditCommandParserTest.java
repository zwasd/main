package seedu.saveit.logic.parser.repeat;

import static seedu.saveit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.saveit.logic.commands.CommandTestUtil.AMOUNT_DESC_BUS;
import static seedu.saveit.logic.commands.CommandTestUtil.AMOUNT_DESC_MRT;
import static seedu.saveit.logic.commands.CommandTestUtil.END_DATE_DESC_BUS;
import static seedu.saveit.logic.commands.CommandTestUtil.END_DATE_DESC_MRT;
import static seedu.saveit.logic.commands.CommandTestUtil.INFO_DESC_BUS;
import static seedu.saveit.logic.commands.CommandTestUtil.INVALID_AMOUNT_DESC;
import static seedu.saveit.logic.commands.CommandTestUtil.INVALID_END_DATE_DESC;
import static seedu.saveit.logic.commands.CommandTestUtil.INVALID_INFO_DESC;
import static seedu.saveit.logic.commands.CommandTestUtil.INVALID_PERIOD_DESC;
import static seedu.saveit.logic.commands.CommandTestUtil.INVALID_START_DATE_DESC;
import static seedu.saveit.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.saveit.logic.commands.CommandTestUtil.START_DATE_DESC_BUS;
import static seedu.saveit.logic.commands.CommandTestUtil.START_DATE_DESC_MRT;
import static seedu.saveit.logic.commands.CommandTestUtil.TAG_DESC_BUS;
import static seedu.saveit.logic.commands.CommandTestUtil.TAG_DESC_TRANSPORT;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_AMOUNT_BUS;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_DAILY_PERIOD;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_DAILY_PERIOD_DESC;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_DATE_BUS;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_END_DATE_BUS;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_INFO_BUS;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_MONTHLY_PERIOD;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_MONTHLY_PERIOD_DESC;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_START_DATE_BUS;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_TAG_BUS;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_TAG_TRANSPORT;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_WEEKLY_PERIOD_DESC;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.saveit.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.saveit.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.saveit.testutil.TypicalIndexes.INDEX_FIRST_EXPENDITURE;
import static seedu.saveit.testutil.TypicalIndexes.INDEX_SECOND_EXPENDITURE;
import static seedu.saveit.testutil.TypicalIndexes.INDEX_THIRD_EXPENDITURE;

import org.junit.jupiter.api.Test;

import seedu.saveit.commons.core.index.Index;
import seedu.saveit.logic.commands.repeat.RepeatEditCommand;
import seedu.saveit.model.expenditure.Amount;
import seedu.saveit.model.expenditure.Date;
import seedu.saveit.model.expenditure.Info;
import seedu.saveit.model.expenditure.Repeat;
import seedu.saveit.model.expenditure.Tag;
import seedu.saveit.testutil.EditRepeatDescriptorBuilder;

public class RepeatEditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, RepeatEditCommand.MESSAGE_USAGE);

    private final RepeatEditCommandParser parser = new RepeatEditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_INFO_BUS, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", RepeatEditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + INFO_DESC_BUS, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + INFO_DESC_BUS, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_INFO_DESC, Info.MESSAGE_CONSTRAINTS); // invalid info
        assertParseFailure(parser, "1" + INVALID_AMOUNT_DESC, Amount.MESSAGE_CONSTRAINTS); // invalid amount
        assertParseFailure(parser, "1" + INVALID_START_DATE_DESC,
                Date.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1" + INVALID_END_DATE_DESC, Date.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag
        assertParseFailure(parser, "1" + INVALID_PERIOD_DESC,
                Repeat.PERIOD_MESSAGE_CONSTRAINTS); // invalid address


        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Expenditure} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_TRANSPORT + TAG_DESC_BUS + TAG_EMPTY,
                Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_INFO_DESC + INVALID_AMOUNT_DESC + VALID_DATE_BUS,
                Info.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_EXPENDITURE;
        String userInput = targetIndex.getOneBased() + TAG_DESC_BUS + VALID_DAILY_PERIOD_DESC
                + AMOUNT_DESC_BUS + START_DATE_DESC_BUS + END_DATE_DESC_BUS + INFO_DESC_BUS;

        RepeatEditCommand.EditRepeatDescriptor descriptor = new EditRepeatDescriptorBuilder().withInfo(VALID_INFO_BUS)
                .withAmount(VALID_AMOUNT_BUS).withStartDate(VALID_START_DATE_BUS).withEndDate(VALID_END_DATE_BUS)
                .withTag(VALID_TAG_BUS).withPeriod(VALID_DAILY_PERIOD).build();
        RepeatEditCommand expectedCommand = new RepeatEditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_EXPENDITURE;
        String userInput = targetIndex.getOneBased() + AMOUNT_DESC_BUS;

        RepeatEditCommand.EditRepeatDescriptor descriptor = new EditRepeatDescriptorBuilder()
                .withAmount(VALID_AMOUNT_BUS).build();
        RepeatEditCommand expectedCommand = new RepeatEditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }


    @Test
    public void parse_oneFieldSpecified_success() {
        // info
        Index targetIndex = INDEX_THIRD_EXPENDITURE;
        String userInput = targetIndex.getOneBased() + INFO_DESC_BUS;
        RepeatEditCommand.EditRepeatDescriptor descriptor = new EditRepeatDescriptorBuilder()
                .withInfo(VALID_INFO_BUS).build();
        RepeatEditCommand expectedCommand = new RepeatEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // amount
        userInput = targetIndex.getOneBased() + AMOUNT_DESC_BUS;
        descriptor = new EditRepeatDescriptorBuilder().withAmount(VALID_AMOUNT_BUS).build();
        expectedCommand = new RepeatEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // start date
        userInput = targetIndex.getOneBased() + START_DATE_DESC_BUS;
        descriptor = new EditRepeatDescriptorBuilder().withStartDate(VALID_START_DATE_BUS).build();
        expectedCommand = new RepeatEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // end date
        userInput = targetIndex.getOneBased() + END_DATE_DESC_BUS;
        descriptor = new EditRepeatDescriptorBuilder().withEndDate(VALID_END_DATE_BUS).build();
        expectedCommand = new RepeatEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_TRANSPORT;
        descriptor = new EditRepeatDescriptorBuilder().withTag(VALID_TAG_TRANSPORT).build();
        expectedCommand = new RepeatEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // period
        userInput = targetIndex.getOneBased() + VALID_MONTHLY_PERIOD_DESC;
        descriptor = new EditRepeatDescriptorBuilder().withPeriod(VALID_MONTHLY_PERIOD).build();
        expectedCommand = new RepeatEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_EXPENDITURE;
        String userInput = targetIndex.getOneBased() + START_DATE_DESC_MRT + START_DATE_DESC_BUS + AMOUNT_DESC_MRT
                + TAG_DESC_TRANSPORT + END_DATE_DESC_MRT + END_DATE_DESC_BUS + AMOUNT_DESC_BUS + TAG_DESC_TRANSPORT
                + TAG_DESC_BUS + VALID_WEEKLY_PERIOD_DESC + VALID_DAILY_PERIOD_DESC;

        RepeatEditCommand.EditRepeatDescriptor descriptor = new EditRepeatDescriptorBuilder()
                .withAmount(VALID_AMOUNT_BUS).withStartDate(VALID_START_DATE_BUS).withEndDate(VALID_END_DATE_BUS)
                .withTag(VALID_TAG_BUS).withPeriod(VALID_DAILY_PERIOD)
                .build();
        RepeatEditCommand expectedCommand = new RepeatEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }


}
