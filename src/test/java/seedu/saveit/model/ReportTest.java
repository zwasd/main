package seedu.saveit.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static seedu.saveit.logic.commands.CommandTestUtil.VALID_END_DATE_BUS;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_END_DATE_MRT;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_GRAPH_BAR_CAPS;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_GRAPH_PIE_CAPS;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_ORGANISATION_MONTH;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_ORGANISATION_TAG;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_START_DATE_BUS;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_START_DATE_BUS_ALT;

import org.junit.jupiter.api.Test;

import seedu.saveit.testutil.ReportBuilder;

public class ReportTest {

    @Test
    public void equals() {

        ReportBuilder report1 = new ReportBuilder()
                .withStartDate(VALID_START_DATE_BUS)
                .withEndDate(VALID_END_DATE_BUS)
                .withGraphType(VALID_GRAPH_PIE_CAPS)
                .withOrganise(VALID_ORGANISATION_TAG);

        ReportBuilder report2 = new ReportBuilder()
                .withStartDate(VALID_START_DATE_BUS)
                .withEndDate(VALID_END_DATE_BUS)
                .withGraphType(VALID_GRAPH_PIE_CAPS)
                .withOrganise(VALID_ORGANISATION_TAG);

        assertTrue(report1.build().equals(report2.build()));

        // different start date
        report1.withStartDate(VALID_START_DATE_BUS_ALT);
        assertFalse(report1.build().equals(report2.build()));

        report1.withStartDate(VALID_START_DATE_BUS);

        //different end date
        report1.withEndDate(VALID_END_DATE_MRT);
        assertFalse(report1.build().equals(report2.build()));

        report1.withEndDate(VALID_END_DATE_BUS);

        //different graph type
        report1.withGraphType(VALID_GRAPH_BAR_CAPS);
        assertFalse(report1.build().equals(report2.build()));

        report1.withGraphType(VALID_GRAPH_PIE_CAPS);

        //different organise
        report1.withOrganise(VALID_ORGANISATION_MONTH);
        assertFalse(report1.build().equals(report2.build()));

        // all fields different
        report1.withStartDate(VALID_START_DATE_BUS_ALT);
        report1.withEndDate(VALID_END_DATE_MRT);
        report1.withGraphType(VALID_GRAPH_BAR_CAPS);
        report1.withOrganise(VALID_ORGANISATION_MONTH);
        assertFalse(report1.build().equals(report2.build()));

    }
}
