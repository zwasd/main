package seedu.address.logic.commands;

import seedu.address.ui.Graph;

/**
 * Command result from report window.
 */
public class ReportCommandResult {

    private final boolean isExitReport;
    private final boolean isExportReport;
    private final boolean isPrintReport;
    private final String feedbackToUser;

    private Graph graph;

    public ReportCommandResult(String feedbackToUser, Graph graph) {
        this(feedbackToUser, false, false, false);
        this.graph = graph;
        ;
    }

    public ReportCommandResult(String feedbackToUser, boolean isExitReport,
                               boolean isExportReport, boolean isPrintReport) {
        this.feedbackToUser = feedbackToUser;
        this.isExitReport = isExitReport;
        this.isExportReport = isExportReport;
        this.isPrintReport = isPrintReport;
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

    public Graph getGraph() {
        return graph;
    }


}


