package seedu.saveit.logic.commands;

import seedu.saveit.ui.Graph;

/**
 * Command result from report window.
 */
public class ReportCommandResult {

    private final boolean isExitReport;
    private final boolean isExportReport;
    private final boolean isPrintReport;
    private final boolean isShowHelp;
    private final boolean isChangeView;
    private final String feedbackToUser;

    private Graph graph;
    private String fileName;

    public ReportCommandResult(String feedbackToUser, boolean isExitReport,
                               boolean isExportReport, boolean isPrintReport,
                               boolean isShowHelp, boolean isChangeView) {
        this.feedbackToUser = feedbackToUser;
        this.isExitReport = isExitReport;
        this.isExportReport = isExportReport;
        this.isPrintReport = isPrintReport;
        this.isShowHelp = isShowHelp;
        this.isChangeView = isChangeView;

    }

    public ReportCommandResult(String feedbackToUser, Graph graph) {
        this(feedbackToUser, false, false, false, false, true);
        this.graph = graph;
    }

    public ReportCommandResult(String feedbackToUser, String fileName) {
        this(feedbackToUser, false, true, false, false, false);
        this.fileName = fileName;
    }

    public ReportCommandResult(String feedbackToUser, boolean isShowHelp) {
        this(feedbackToUser, false, false, false, isShowHelp, false);
    }

    public ReportCommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, false, false);
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

    public boolean isShowHelp() {
        return isShowHelp;
    }

    public boolean isChangeView() {
        return isChangeView;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public Graph getGraph() {
        return graph;
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof ReportCommandResult) {
            ReportCommandResult r = (ReportCommandResult) obj;
            return feedbackToUser.equals(r.feedbackToUser)
                    && isExportReport == r.isExportReport
                    && isChangeView == r.isChangeView
                    && isShowHelp == r.isShowHelp
                    && isPrintReport == r.isPrintReport
                    && isExitReport == r.isExitReport;
        }

        return false;
    }
}


