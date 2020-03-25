package seedu.address.logic.commands;

import java.util.HashMap;

import seedu.address.model.Report;

/**
 * Command result from report window.
 */
public class ReportCommandResult {


    private final boolean exitReport;
    private HashMap stats;
    private Report.GraphType graph;

    public ReportCommandResult(Report.GraphType graph, HashMap stats) {
        this(false);
        this.graph = graph;
        this.stats = stats;
    }

    public ReportCommandResult(boolean exitReport) {
        this.exitReport = exitReport;
    }

    public HashMap getStats() {
        return stats;
    }

    public boolean getExitReport() {
        return exitReport;
    }
}


