package seedu.saveit.model;

import seedu.saveit.logic.parser.exceptions.ParseException;
import seedu.saveit.model.expenditure.Date;

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
        BAR, PIE, NULL;

        public static final String GRAPH_TYPE_MESSAGE_CONSTRAINT = "Graph types should only be: "
                + GraphType.BAR + ", " + GraphType.PIE;

        /**
         * Checks if {@code g} is a valid GraphType.
         */
        public static boolean isValidGraph(String g) {
            switch (g) {
            case "BAR":
            case "PIE":
                return true;
            default:
                return false;
            }
        }

        /**
         * Maps {@code String graph} to corresponding graphType
         */
        public static GraphType mapToGraphType(String graph) throws ParseException {
            switch (graph) {
            case "BAR":
                return GraphType.BAR;
            case "PIE":
                return GraphType.PIE;
            default:
                throw new ParseException(GRAPH_TYPE_MESSAGE_CONSTRAINT);
            }
        }
    }

    public Report(Date startDate, Date endDate, GraphType graph) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.graph = graph;
    }

    /**
     * Getter method for user input graph type.
     * @return GraphType user inputs
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
