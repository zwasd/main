package seedu.saveit.testutil;

import static seedu.saveit.logic.commands.CommandTestUtil.VALID_END_DATE_BUS;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_END_DATE_MRT;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_GRAPH_BAR;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_START_DATE_BUS;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_START_DATE_MRT;

import seedu.saveit.model.Report;
import seedu.saveit.model.expenditure.Date;

/**
 * A utility class to help with building Report objects.
 * Example usage: <br>
 *     {@code Report ab = new ReportBuilder();}
 */
public class ReportBuilder {
    public static final String DEFAULT_GRAPH_TYPE = VALID_GRAPH_BAR;
    public static final String DEFAULT_START_DATE = VALID_START_DATE_MRT;
    public static final String DEFAULT_END_DATE = VALID_END_DATE_MRT;

    private Report.GraphType graphType;
    private Date startDate;
    private Date endDate;

    public ReportBuilder() {
        this.graphType = Report.GraphType.valueOf(VALID_GRAPH_BAR);
        this.startDate = new Date(VALID_START_DATE_BUS);
        this.endDate = new Date(VALID_END_DATE_BUS);
    }

    /**
     * Sets the {@code graphType} of the {@code graphType} that we are building.
     */
    public ReportBuilder withGraphType(String graphType) {
        this.graphType = Report.GraphType.valueOf(graphType);;
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

    public Report build() {
        return new Report(startDate, endDate, graphType);
    }

}
