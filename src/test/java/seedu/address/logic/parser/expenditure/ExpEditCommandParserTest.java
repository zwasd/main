package seedu.address.logic.parser.expenditure;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import static seedu.address.logic.commands.CommandTestUtil.AMOUNT_DESC_BUS;
import static seedu.address.logic.commands.CommandTestUtil.AMOUNT_DESC_MRT;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_BUS;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_MRT;
import static seedu.address.logic.commands.CommandTestUtil.INFO_DESC_BUS;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_AMOUNT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_INFO_DESC;

import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_TRANSPORT;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_BUS;

import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_BUS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_MRT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_BUS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_MRT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INFO_BUS;

import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_TRANSPORT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_BUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EXPENDITURE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EXPENDITURE;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_EXPENDITURE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.expenditure.ExpEditCommand;
import seedu.address.logic.commands.expenditure.ExpEditCommand.EditExpenditureDescriptor;

import seedu.address.model.expenditure.Amount;
import seedu.address.model.expenditure.Date;
import seedu.address.model.expenditure.Info;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditExpenditureDescriptorBuilder;

public class ExpEditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExpEditCommand.MESSAGE_USAGE);

    private ExpEditCommandParser parser = new ExpEditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_INFO_BUS, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", ExpEditCommand.MESSAGE_NOT_EDITED);

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
        assertParseFailure(parser, "1" + INVALID_DATE_DESC, Date.MESSAGE_CONSTRAINTS); // invalid address

        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag


        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Expenditure} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_TRANSPORT + TAG_DESC_BUS + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_INFO_DESC + INVALID_AMOUNT_DESC + VALID_DATE_BUS,
                Info.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_EXPENDITURE;
        String userInput = targetIndex.getOneBased() + TAG_DESC_BUS
                + AMOUNT_DESC_BUS + DATE_DESC_BUS + INFO_DESC_BUS;

        EditExpenditureDescriptor descriptor = new EditExpenditureDescriptorBuilder().withInfo(VALID_INFO_BUS)
                .withAmount(VALID_AMOUNT_BUS).withDate(VALID_DATE_BUS)
                .withTag(VALID_TAG_BUS).build();
        ExpEditCommand expectedCommand = new ExpEditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_EXPENDITURE;
        String userInput = targetIndex.getOneBased() + AMOUNT_DESC_BUS;

        EditExpenditureDescriptor descriptor = new EditExpenditureDescriptorBuilder()
                .withAmount(VALID_AMOUNT_BUS).build();
        ExpEditCommand expectedCommand = new ExpEditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // info
        Index targetIndex = INDEX_THIRD_EXPENDITURE;
        String userInput = targetIndex.getOneBased() + INFO_DESC_BUS;
        EditExpenditureDescriptor descriptor = new EditExpenditureDescriptorBuilder()
                .withInfo(VALID_INFO_BUS).build();
        ExpEditCommand expectedCommand = new ExpEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // amount
        userInput = targetIndex.getOneBased() + AMOUNT_DESC_BUS;
        descriptor = new EditExpenditureDescriptorBuilder().withAmount(VALID_AMOUNT_BUS).build();
        expectedCommand = new ExpEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + DATE_DESC_BUS;
        descriptor = new EditExpenditureDescriptorBuilder().withDate(VALID_DATE_BUS).build();
        expectedCommand = new ExpEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_TRANSPORT;
        descriptor = new EditExpenditureDescriptorBuilder().withTag(VALID_TAG_TRANSPORT).build();
        expectedCommand = new ExpEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_EXPENDITURE;
        String userInput = targetIndex.getOneBased() + DATE_DESC_BUS + AMOUNT_DESC_BUS
                + TAG_DESC_TRANSPORT + DATE_DESC_BUS + AMOUNT_DESC_BUS + TAG_DESC_TRANSPORT
                + DATE_DESC_MRT + AMOUNT_DESC_MRT + TAG_DESC_BUS;

        EditExpenditureDescriptor descriptor = new EditExpenditureDescriptorBuilder()
                .withAmount(VALID_AMOUNT_MRT).withDate(VALID_DATE_MRT)
                .withTag(VALID_TAG_BUS)
                .build();
        ExpEditCommand expectedCommand = new ExpEditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
