package seedu.address.logic.commands.report;

import java.util.HashMap;

import seedu.address.logic.commands.ReportCommand;
import seedu.address.logic.commands.ReportCommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Report;

/**
 * View report command in report window.
 */
public class ReportWindowStatsCommand extends ReportCommand {

    public static final String MESSAGE_SUCCESS = "Report is generated";

    public static String MESSAGE_USAGE = "Shows the report. "
            + "\n" + "Parameters "
            + "Start Date : YYYY-MM-DD  "
            + "End Date :  YYYY-MM-DD  "
            + "Graph Type: PIE, BAR, STACK " + "\n EG"
            + "2020-03-22 " + "2020-03-25 " + "PIE";

    private HashMap statsToDisplay;
    private Report.GraphType format;
    private Report report;

    public ReportWindowStatsCommand(Report report) {
        this.report = report;
    }

    @Override
    public ReportCommandResult execute(Model model) throws CommandException {
        statsToDisplay = new GenerateStats(report, model).generateStatsByTags();
        format = report.getFormat();
        return new ReportCommandResult(format, statsToDisplay);
    }
}
