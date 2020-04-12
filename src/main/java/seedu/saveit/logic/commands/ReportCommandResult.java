package seedu.saveit.logic.commands;

import seedu.saveit.ui.Graph;

/**
 * Command result from report window.
 */
public class ReportCommandResult {

    private final boolean isExitReport;
    private final boolean isChangeView;
    private final String feedbackToUser;

    private Graph graph;
    private String fileName;

    public ReportCommandResult(String feedbackToUser, boolean isExitReport,
                               boolean isChangeView) {
        this.feedbackToUser = feedbackToUser;
        this.isExitReport = isExitReport;
        this.isChangeView = isChangeView;

    }

    public ReportCommandResult(String feedbackToUser, Graph graph) {
        //change view true
        this(feedbackToUser, false, true);
        this.graph = graph;
    }

    public ReportCommandResult(String feedbackToUser, String fileName) {
        this(feedbackToUser, false, false);
        this.fileName = fileName;
    }

    public ReportCommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false);
    }


    public boolean isExitReport() {
        return isExitReport;
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
                    && isExitReport == r.isExitReport
                    && isChangeView == r.isChangeView;
        }

        return false;
    }
}


