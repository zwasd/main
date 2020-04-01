package seedu.address.logic.commands;

import java.util.HashMap;

import seedu.address.model.Report;

/**
 * Command result from report window.
 */
public class ReportCommandResult {

    private final boolean isExitReport;
    private final boolean isExportReport;
    private final boolean isPrintReport;
    private final String feedbackToUser;

    private HashMap stats;
    private Report.GraphType graph = Report.GraphType.NULL;

    public ReportCommandResult(String feedbackToUser, Report.GraphType graph, HashMap stats) {
        this(feedbackToUser, false, false, false);
        this.graph = graph;
        this.stats = stats;
        ;
    }

    public ReportCommandResult(String feedbackToUser, boolean isExitReport,
                               boolean isExportReport, boolean isPrintReport) {
        this.feedbackToUser = feedbackToUser;
        this.isExitReport = isExitReport;
        this.isExportReport = isExportReport;
        this.isPrintReport = isPrintReport;
    }

    public HashMap getStats() {
        return stats;
    }

    public boolean isExitReport() {
        return isExitReport;
    }

    public boolean isExportReport() {
        return isExportReport;
    }

    public boolean isPrintReport() {
        return isPrintReport;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isPieGraph() {
        return graph == Report.GraphType.PIE;
    }

    public boolean isBarGraph() {
        return graph == Report.GraphType.BAR;
    }
}


