package seedu.address.model;

import seedu.address.model.expenditure.Date;

/**
 * Report.
 */
public class Report {
    private final Date startDate;
    private final Date endDate;
    private final GraphType graph;

    /**
     * Represents the different types
     * of graph that can be generated for report.
     */
    public enum GraphType {
        BAR, PIE, STACK;
    }

    public Report(Date startDate, Date endDate, GraphType graph) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.graph = graph;
    }

    /**
     * Getter method for user input graph type.
     * @return GraphType user inpurs
     */
    public GraphType getFormat() {
        return graph;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

}
