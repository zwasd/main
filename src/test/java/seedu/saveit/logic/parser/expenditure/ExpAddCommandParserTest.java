package seedu.saveit.logic.parser.expenditure;

import static seedu.saveit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.saveit.logic.commands.CommandTestUtil.AMOUNT_DESC_BUS;
import static seedu.saveit.logic.commands.CommandTestUtil.AMOUNT_DESC_MRT;
import static seedu.saveit.logic.commands.CommandTestUtil.DATE_DESC_BUS;
import static seedu.saveit.logic.commands.CommandTestUtil.DATE_DESC_MRT;
import static seedu.saveit.logic.commands.CommandTestUtil.INFO_DESC_BUS;
import static seedu.saveit.logic.commands.CommandTestUtil.INFO_DESC_MRT;
import static seedu.saveit.logic.commands.CommandTestUtil.INVALID_AMOUNT_DESC;
import static seedu.saveit.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.saveit.logic.commands.CommandTestUtil.INVALID_INFO_DESC;
import static seedu.saveit.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.saveit.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.saveit.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.saveit.logic.commands.CommandTestUtil.TAG_DESC_BUS;
import static seedu.saveit.logic.commands.CommandTestUtil.TAG_DESC_TRANSPORT;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_AMOUNT_MRT;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_INFO_MRT;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_TAG_TRANSPORT;
import static seedu.saveit.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.saveit.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.saveit.testutil.TypicalExpenditures.AMY;
import static seedu.saveit.testutil.TypicalExpenditures.BOB;

import org.junit.jupiter.api.Test;

import seedu.saveit.logic.commands.expenditure.ExpAddCommand;

import seedu.saveit.model.expenditure.Amount;
import seedu.saveit.model.expenditure.Date;
import seedu.saveit.model.expenditure.Expenditure;
import seedu.saveit.model.expenditure.Info;

import seedu.saveit.model.expenditure.Tag;
import seedu.saveit.testutil.ExpenditureBuilder;

public class ExpAddCommandParserTest {
    private ExpAddCommandParser parser = new ExpAddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Expenditure expectedExpenditure = new ExpenditureBuilder(BOB).withTag(VALID_TAG_TRANSPORT).build();

        assertParseSuccess(parser, PREAMBLE_WHITESPACE + INFO_DESC_MRT + AMOUNT_DESC_MRT
                + DATE_DESC_MRT + TAG_DESC_TRANSPORT, new ExpAddCommand(expectedExpenditure));

        // multiple infos - last info accepted
        assertParseSuccess(parser, INFO_DESC_BUS + INFO_DESC_MRT + AMOUNT_DESC_MRT
                + DATE_DESC_MRT + TAG_DESC_TRANSPORT, new ExpAddCommand(expectedExpenditure));

        // multiple amounts - last amount accepted
        assertParseSuccess(parser, INFO_DESC_MRT + AMOUNT_DESC_BUS + AMOUNT_DESC_MRT
                + DATE_DESC_MRT + TAG_DESC_TRANSPORT, new ExpAddCommand(expectedExpenditure));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, INFO_DESC_MRT + AMOUNT_DESC_MRT + DATE_DESC_BUS
                + DATE_DESC_MRT + TAG_DESC_TRANSPORT, new ExpAddCommand(expectedExpenditure));

        // multiple tags - all accepted
        Expenditure expectedExpenditureMultipleTags = new ExpenditureBuilder(BOB)
                .withTag(VALID_TAG_TRANSPORT)
                .build();

        assertParseSuccess(parser, INFO_DESC_MRT + AMOUNT_DESC_MRT + DATE_DESC_MRT
                + TAG_DESC_BUS + TAG_DESC_TRANSPORT, new ExpAddCommand(expectedExpenditureMultipleTags));

    }


    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Expenditure expectedExpenditure = new ExpenditureBuilder(AMY).build();
        assertParseSuccess(parser, INFO_DESC_BUS + AMOUNT_DESC_BUS + DATE_DESC_BUS,
                new ExpAddCommand(expectedExpenditure));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExpAddCommand.MESSAGE_USAGE);

        // missing info prefix
        assertParseFailure(parser, VALID_INFO_MRT + AMOUNT_DESC_MRT + DATE_DESC_MRT,
                expectedMessage);

        // missing amount prefix
        assertParseFailure(parser, INFO_DESC_MRT + VALID_AMOUNT_MRT + DATE_DESC_MRT,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_INFO_MRT + VALID_AMOUNT_MRT + DATE_DESC_MRT,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid info
        assertParseFailure(parser, INVALID_INFO_DESC + AMOUNT_DESC_MRT + DATE_DESC_MRT
                + TAG_DESC_BUS + TAG_DESC_TRANSPORT, Info.MESSAGE_CONSTRAINTS);

        // invalid amount
        assertParseFailure(parser, INFO_DESC_MRT + INVALID_AMOUNT_DESC + DATE_DESC_MRT
                + TAG_DESC_BUS + TAG_DESC_TRANSPORT, Amount.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, INFO_DESC_MRT + AMOUNT_DESC_MRT + INVALID_DATE_DESC
                + TAG_DESC_BUS + TAG_DESC_TRANSPORT, Date.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, INFO_DESC_MRT + AMOUNT_DESC_MRT + DATE_DESC_MRT
                + INVALID_TAG_DESC + VALID_TAG_TRANSPORT, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_INFO_DESC + AMOUNT_DESC_MRT + INVALID_DATE_DESC,
                Info.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + INFO_DESC_MRT + AMOUNT_DESC_MRT
                        + DATE_DESC_MRT + TAG_DESC_BUS + TAG_DESC_TRANSPORT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExpAddCommand.MESSAGE_USAGE));
    }
}
