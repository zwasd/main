package seedu.saveit.logic.parser.repeat;


import org.junit.jupiter.api.Test;
import seedu.saveit.logic.commands.repeat.RepeatAddCommand;
import seedu.saveit.model.expenditure.Amount;
import seedu.saveit.model.expenditure.Date;
import seedu.saveit.model.expenditure.Info;
import seedu.saveit.model.expenditure.Repeat;
import seedu.saveit.model.expenditure.Tag;
import seedu.saveit.testutil.RepeatBuilder;

import static seedu.saveit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.saveit.logic.commands.CommandTestUtil.AMOUNT_DESC_BUS;
import static seedu.saveit.logic.commands.CommandTestUtil.AMOUNT_DESC_MRT;
import static seedu.saveit.logic.commands.CommandTestUtil.END_DATE_DESC_BUS;
import static seedu.saveit.logic.commands.CommandTestUtil.END_DATE_DESC_MRT;
import static seedu.saveit.logic.commands.CommandTestUtil.INFO_DESC_BUS;
import static seedu.saveit.logic.commands.CommandTestUtil.INFO_DESC_MRT;
import static seedu.saveit.logic.commands.CommandTestUtil.INVALID_AMOUNT_DESC;
import static seedu.saveit.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.saveit.logic.commands.CommandTestUtil.INVALID_END_DATE_DESC;
import static seedu.saveit.logic.commands.CommandTestUtil.INVALID_INFO_DESC;
import static seedu.saveit.logic.commands.CommandTestUtil.INVALID_PERIOD_DESC;
import static seedu.saveit.logic.commands.CommandTestUtil.INVALID_START_DATE_DESC;
import static seedu.saveit.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.saveit.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.saveit.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.saveit.logic.commands.CommandTestUtil.START_DATE_DESC_BUS;
import static seedu.saveit.logic.commands.CommandTestUtil.START_DATE_DESC_MRT;
import static seedu.saveit.logic.commands.CommandTestUtil.TAG_DESC_TRANSPORT;
import static seedu.saveit.logic.commands.CommandTestUtil.TAG_DESC_BUS;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_AMOUNT_MRT;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_DAILY_PERIOD;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_DAILY_PERIOD_DESC;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_END_DATE_MRT;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_INFO_MRT;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_START_DATE_MRT;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_TAG_TRANSPORT;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_WEEKLY_PERIOD_DESC;
import static seedu.saveit.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.saveit.logic.parser.CommandParserTestUtil.assertParseSuccess;


public class RepeatAddCommandParserTest {
    private final RepeatAddCommandParser parser = new RepeatAddCommandParser();


    @Test
    public void parse_allFieldsPresent_success() {
        Repeat expectedRepeat = new RepeatBuilder().withInfo(VALID_INFO_MRT)
                .withAmount(VALID_AMOUNT_MRT)
                .withStartDate(VALID_START_DATE_MRT)
                .withEndDate(VALID_END_DATE_MRT)
                .withTag(VALID_TAG_TRANSPORT)
                .withPeriod(VALID_DAILY_PERIOD)
                .build();


        assertParseSuccess(parser, PREAMBLE_WHITESPACE + INFO_DESC_MRT + AMOUNT_DESC_MRT
                + START_DATE_DESC_MRT + END_DATE_DESC_MRT + TAG_DESC_TRANSPORT + VALID_DAILY_PERIOD_DESC,
                new RepeatAddCommand(expectedRepeat));

        // multiple infos - last info accepted
        assertParseSuccess(parser, INFO_DESC_BUS + INFO_DESC_MRT + AMOUNT_DESC_MRT
                + START_DATE_DESC_MRT + END_DATE_DESC_MRT + TAG_DESC_TRANSPORT + VALID_DAILY_PERIOD_DESC,
                new RepeatAddCommand(expectedRepeat));

        // multiple amounts - last amount accepted
        assertParseSuccess(parser, INFO_DESC_MRT + AMOUNT_DESC_BUS + AMOUNT_DESC_MRT
                + START_DATE_DESC_MRT + END_DATE_DESC_MRT + TAG_DESC_TRANSPORT + VALID_DAILY_PERIOD_DESC,
                new RepeatAddCommand(expectedRepeat));

        // multiple start date - last start date accepted
        assertParseSuccess(parser, INFO_DESC_MRT + AMOUNT_DESC_MRT + START_DATE_DESC_BUS
                        + START_DATE_DESC_MRT + END_DATE_DESC_MRT + TAG_DESC_TRANSPORT + VALID_DAILY_PERIOD_DESC,
                new RepeatAddCommand(expectedRepeat));


        // multiple end date - last end date accepted
        assertParseSuccess(parser, INFO_DESC_MRT + AMOUNT_DESC_MRT + START_DATE_DESC_MRT + END_DATE_DESC_BUS
                        + END_DATE_DESC_MRT + TAG_DESC_TRANSPORT + VALID_DAILY_PERIOD_DESC,
                new RepeatAddCommand(expectedRepeat));

        // multiple tags - only last tag accepted
        assertParseSuccess(parser, INFO_DESC_MRT + AMOUNT_DESC_MRT + START_DATE_DESC_MRT + END_DATE_DESC_MRT
                + TAG_DESC_BUS + TAG_DESC_TRANSPORT + VALID_DAILY_PERIOD_DESC, new RepeatAddCommand(expectedRepeat));

        // multiple period - only last period accepted
        assertParseSuccess(parser, INFO_DESC_MRT + AMOUNT_DESC_MRT + START_DATE_DESC_MRT + END_DATE_DESC_MRT
                + TAG_DESC_TRANSPORT + VALID_WEEKLY_PERIOD_DESC + VALID_DAILY_PERIOD_DESC,
                new RepeatAddCommand(expectedRepeat));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Repeat expectedRepeat = new RepeatBuilder().withTag("Others").build();
        assertParseSuccess(parser, INFO_DESC_MRT + AMOUNT_DESC_MRT + START_DATE_DESC_MRT + END_DATE_DESC_MRT
                + VALID_DAILY_PERIOD_DESC, new RepeatAddCommand(expectedRepeat));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, RepeatAddCommand.MESSAGE_USAGE);

        // missing info prefix
        assertParseFailure(parser, VALID_INFO_MRT + AMOUNT_DESC_MRT + START_DATE_DESC_MRT + END_DATE_DESC_MRT
                + VALID_DAILY_PERIOD_DESC, expectedMessage);

        // missing amount prefix
        assertParseFailure(parser, INFO_DESC_MRT + VALID_AMOUNT_MRT + START_DATE_DESC_MRT + END_DATE_DESC_MRT
                        + VALID_DAILY_PERIOD_DESC, expectedMessage);

        // missing start date prefix
        assertParseFailure(parser, INFO_DESC_MRT + AMOUNT_DESC_MRT + VALID_START_DATE_MRT + END_DATE_DESC_MRT
                + VALID_DAILY_PERIOD_DESC, expectedMessage);

        // missing end date prefix
        assertParseFailure(parser, INFO_DESC_MRT + AMOUNT_DESC_MRT + START_DATE_DESC_MRT + VALID_END_DATE_MRT
                + VALID_DAILY_PERIOD_DESC, expectedMessage);

        // missing period prefix
        assertParseFailure(parser, INFO_DESC_MRT + AMOUNT_DESC_MRT + START_DATE_DESC_MRT + END_DATE_DESC_MRT
                + VALID_DAILY_PERIOD, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_INFO_MRT + VALID_AMOUNT_MRT + VALID_START_DATE_MRT
                + VALID_END_DATE_MRT + VALID_DAILY_PERIOD, expectedMessage);

    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid info
        assertParseFailure(parser, INVALID_INFO_DESC + AMOUNT_DESC_MRT + START_DATE_DESC_MRT
                + END_DATE_DESC_MRT + TAG_DESC_TRANSPORT + VALID_DAILY_PERIOD_DESC, Info.MESSAGE_CONSTRAINTS);

        // invalid amount
        assertParseFailure(parser, INFO_DESC_MRT + INVALID_AMOUNT_DESC + START_DATE_DESC_MRT
                + END_DATE_DESC_MRT + TAG_DESC_TRANSPORT + VALID_DAILY_PERIOD_DESC, Amount.MESSAGE_CONSTRAINTS);

        // invalid start date
        assertParseFailure(parser, INFO_DESC_MRT + AMOUNT_DESC_MRT + INVALID_START_DATE_DESC
                + END_DATE_DESC_MRT + TAG_DESC_TRANSPORT + VALID_DAILY_PERIOD_DESC, Date.MESSAGE_CONSTRAINTS);

        // invalid end date
        assertParseFailure(parser, INFO_DESC_MRT + AMOUNT_DESC_MRT + START_DATE_DESC_MRT
                + INVALID_END_DATE_DESC + TAG_DESC_TRANSPORT + VALID_DAILY_PERIOD_DESC, Date.MESSAGE_CONSTRAINTS);


        // invalid tag
        assertParseFailure(parser, INFO_DESC_MRT + AMOUNT_DESC_MRT + START_DATE_DESC_MRT
                + END_DATE_DESC_MRT + INVALID_TAG_DESC + VALID_DAILY_PERIOD_DESC, Tag.MESSAGE_CONSTRAINTS);


        // invalid period
        assertParseFailure(parser, INFO_DESC_MRT + AMOUNT_DESC_MRT + START_DATE_DESC_MRT
                + END_DATE_DESC_MRT + TAG_DESC_TRANSPORT + INVALID_PERIOD_DESC, Repeat.PERIOD_MESSAGE_CONSTRAINTS);


        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_INFO_DESC + AMOUNT_DESC_MRT + INVALID_DATE_DESC
                        + START_DATE_DESC_MRT + END_DATE_DESC_MRT + TAG_DESC_TRANSPORT + VALID_DAILY_PERIOD_DESC,
                Info.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + INFO_DESC_MRT + AMOUNT_DESC_MRT
                        + START_DATE_DESC_MRT + END_DATE_DESC_MRT + TAG_DESC_TRANSPORT + VALID_DAILY_PERIOD_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RepeatAddCommand.MESSAGE_USAGE));
    }


}
