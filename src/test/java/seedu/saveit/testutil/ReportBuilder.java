package seedu.saveit.testutil;

import static seedu.saveit.logic.commands.CommandTestUtil.VALID_END_DATE_BUS;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_END_DATE_MRT;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_GRAPH_BAR_CAPS;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_ORGANISATION_MONTH;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_START_DATE_BUS;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_START_DATE_MRT;

import seedu.saveit.logic.parser.exceptions.ParseException;
import seedu.saveit.model.expenditure.Date;
import seedu.saveit.model.report.Report;

/**
 * A utility class to help with building Report objects.
 * Example usage: <br>
 *     {@code Report ab = new ReportBuilder();}
 */
public class ReportBuilder {
    public static final String DEFAULT_GRAPH_TYPE = VALID_GRAPH_BAR_CAPS;
    public static final String DEFAULT_START_DATE = VALID_START_DATE_MRT;
    public static final String DEFAULT_END_DATE = VALID_END_DATE_MRT;

    private Report.GraphType graphType;
    private Date startDate;
    private Date endDate;
    private String organise;

    public ReportBuilder() {
        try {
            this.graphType = Report.GraphType.mapToGraphType(VALID_GRAPH_BAR_CAPS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.startDate = new Date(VALID_START_DATE_BUS);
        this.endDate = new Date(VALID_END_DATE_BUS);
        this.organise = VALID_ORGANISATION_MONTH;
    }

    /**
     * Sets the {@code graphType} of the {@code graphType} that we are building.
     */
    public ReportBuilder withGraphType(String graphType) {
        try {
            this.graphType = Report.GraphType.mapToGraphType(graphType);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * Sets the {@code startDate} of the {@code report} that we are building.
     */
    public ReportBuilder withStartDate(String startDate) {
        this.startDate = new Date(startDate);
        return this;
    }

    /**
     * Sets the {@code endDate} of the {@code report} that we are building.
     */
    public ReportBuilder withEndDate(String endDate) {
        this.endDate = new Date(endDate);
        return this;
    }

    /**
     *  Sets the {@code organise} of the {@code report} that we are building.
     */
    public ReportBuilder withOrganise(String organise) {
        this.organise = organise;
        return this;
    }

    public Report build() {
        return new Report(startDate, endDate, graphType, organise);
    }

}
