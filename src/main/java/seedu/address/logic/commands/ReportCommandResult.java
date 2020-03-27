package seedu.address.logic.commands;

import java.util.HashMap;

import seedu.address.model.Report;

/**
 * Command result from report window.
 */
public class ReportCommandResult {

    private final boolean exitReport;
    private final String feedbackToUser;

    private HashMap stats;
    private Report.GraphType graph = Report.GraphType.NULL;

    public ReportCommandResult(String feedbackToUser, Report.GraphType graph, HashMap stats) {
        this(feedbackToUser, false);
        this.graph = graph;
        this.stats = stats;
  ;
    }

    public ReportCommandResult(String feedbackToUser, boolean exitReport) {
        this.feedbackToUser = feedbackToUser;
        this.exitReport = exitReport;
    }

    public HashMap getStats() {
        return stats;
    }

    public boolean getExitReport() {
        return exitReport;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isPieGraph() { return graph == Report.GraphType.PIE; }

    public boolean isBarGraph() { return graph == Report.GraphType.BAR; }
}


