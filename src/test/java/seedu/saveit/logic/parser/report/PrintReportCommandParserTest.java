package seedu.saveit.logic.parser.report;

import static seedu.saveit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.saveit.logic.commands.CommandTestUtil.END_DATE_DESC_BUS;
import static seedu.saveit.logic.commands.CommandTestUtil.END_DATE_DESC_MRT;
import static seedu.saveit.logic.commands.CommandTestUtil.INVALID_END_DATE_DESC;
import static seedu.saveit.logic.commands.CommandTestUtil.INVALID_GRAPH_DESC;
import static seedu.saveit.logic.commands.CommandTestUtil.INVALID_ORGANISATION_DESC;
import static seedu.saveit.logic.commands.CommandTestUtil.INVALID_START_DATE_DESC;
import static seedu.saveit.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.saveit.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.saveit.logic.commands.CommandTestUtil.START_DATE_DESC_BUS;
import static seedu.saveit.logic.commands.CommandTestUtil.START_DATE_DESC_MRT;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_END_DATE_MRT;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_GRAPH_BAR_CAPS;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_GRAPH_BAR_DESC_CAPS;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_GRAPH_PIE_DESC_CAPS;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_ORGANISATION_MONTH_DESC;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_ORGANISATION_TAG;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_ORGANISATION_TAG_DESC;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_START_DATE_MRT;
import static seedu.saveit.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.saveit.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.saveit.logic.commands.report.PrintReportCommand;
import seedu.saveit.model.expenditure.Date;
import seedu.saveit.model.report.Report;
import seedu.saveit.testutil.ReportBuilder;


public class PrintReportCommandParserTest {
    private final PrintReportCommandParser parser = new PrintReportCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Report expectedReport = new ReportBuilder().withGraphType(VALID_GRAPH_BAR_CAPS)
                .withStartDate(VALID_START_DATE_MRT)
                .withEndDate(VALID_END_DATE_MRT)
                .withOrganise(VALID_ORGANISATION_TAG)
                .build();

        assertParseSuccess(parser, PREAMBLE_WHITESPACE + START_DATE_DESC_MRT + END_DATE_DESC_MRT
                + VALID_GRAPH_BAR_DESC_CAPS + VALID_ORGANISATION_TAG_DESC, new PrintReportCommand(expectedReport));

        // multiple graph type - last graph type accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + START_DATE_DESC_MRT + END_DATE_DESC_MRT
                + VALID_GRAPH_PIE_DESC_CAPS + VALID_GRAPH_BAR_DESC_CAPS
                + VALID_ORGANISATION_TAG_DESC, new PrintReportCommand(expectedReport));

        // multiple start date - last start date accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + START_DATE_DESC_BUS + START_DATE_DESC_MRT
                + END_DATE_DESC_MRT + VALID_GRAPH_BAR_DESC_CAPS
                + VALID_ORGANISATION_TAG_DESC, new PrintReportCommand(expectedReport));

        // multiple end date - last end date accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + START_DATE_DESC_MRT + END_DATE_DESC_BUS
                + END_DATE_DESC_MRT + VALID_GRAPH_BAR_DESC_CAPS
                + VALID_ORGANISATION_TAG_DESC, new PrintReportCommand(expectedReport));

        // multiple organisation- last organisation accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + START_DATE_DESC_MRT + END_DATE_DESC_MRT
                + VALID_GRAPH_BAR_DESC_CAPS + VALID_ORGANISATION_MONTH_DESC
                + VALID_ORGANISATION_TAG_DESC, new PrintReportCommand(expectedReport));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, PrintReportCommand.MESSAGE_USAGE);

        // missing start date prefix
        assertParseFailure(parser, VALID_START_DATE_MRT + END_DATE_DESC_MRT + VALID_GRAPH_BAR_DESC_CAPS,
                expectedMessage);

        // missing end date prefix
        assertParseFailure(parser, START_DATE_DESC_MRT + VALID_END_DATE_MRT + VALID_GRAPH_BAR_DESC_CAPS,
                expectedMessage);

        // missing graph type prefix
        assertParseFailure(parser, START_DATE_DESC_MRT + END_DATE_DESC_MRT + VALID_GRAPH_BAR_CAPS,
                expectedMessage);


        // all prefixes missing
        assertParseFailure(parser, VALID_START_DATE_MRT + VALID_START_DATE_MRT + VALID_GRAPH_BAR_CAPS,
                expectedMessage);

    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid start date
        assertParseFailure(parser, INVALID_START_DATE_DESC + END_DATE_DESC_MRT
                        + VALID_GRAPH_BAR_DESC_CAPS + VALID_ORGANISATION_TAG_DESC,
                Date.MESSAGE_CONSTRAINTS);


        // invalid end date
        assertParseFailure(parser, START_DATE_DESC_MRT + INVALID_END_DATE_DESC
                        + VALID_GRAPH_BAR_DESC_CAPS + VALID_ORGANISATION_TAG_DESC,
                Date.MESSAGE_CONSTRAINTS);

        // invalid graph type
        assertParseFailure(parser, START_DATE_DESC_MRT + END_DATE_DESC_MRT
                        + INVALID_GRAPH_DESC + VALID_ORGANISATION_TAG_DESC,
                Report.GraphType.GRAPH_TYPE_MESSAGE_CONSTRAINT);

        //invalid organisation type
        assertParseFailure(parser, START_DATE_DESC_MRT + END_DATE_DESC_MRT
                        + VALID_GRAPH_BAR_DESC_CAPS + INVALID_ORGANISATION_DESC,
                Report.ORGANISE_TYPE_MESSAGE_CONSTRAINT);


        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_START_DATE_DESC + END_DATE_DESC_MRT
                        + INVALID_GRAPH_DESC + VALID_ORGANISATION_TAG_DESC,
                Date.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + START_DATE_DESC_MRT + END_DATE_DESC_MRT
                        + VALID_GRAPH_BAR_DESC_CAPS + VALID_ORGANISATION_TAG_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, PrintReportCommand.MESSAGE_USAGE));
    }

}
