package seedu.saveit.logic.commands.report;

import java.util.HashMap;

import seedu.saveit.logic.commands.ReportCommand;
import seedu.saveit.logic.commands.ReportCommandResult;
import seedu.saveit.logic.commands.exceptions.CommandException;
import seedu.saveit.model.Model;
import seedu.saveit.model.Report;
import seedu.saveit.ui.Bar;
import seedu.saveit.ui.Graph;
import seedu.saveit.ui.Pie;

/**
 * View report command in report window.
 */
public class ReportWindowStatsCommand extends ReportCommand {

    public static final String MESSAGE_SUCCESS = "Report is generated";

    public static final String MESSAGE_USAGE = "Parameters: "
            + " graph type : PIE  "
            + " start date : YYYY-MM-DD  "
            + " end date :  YYYY-MM-DD  " + "\n"
            + "eg: " + " PIE " + " 2020-03-22 " + " 2020-03-25 ";

    private HashMap statsToDisplay;
    private Report.GraphType format;
    private Report report;
    private Graph graph;

    public ReportWindowStatsCommand(Report report) {
        this.report = report;
    }

    @Override
    public ReportCommandResult execute(Model model) throws CommandException {
        statsToDisplay = new GenerateStats(report, model).generateStatsByTags();
        format = report.getFormat();

        if (format.equals(Report.GraphType.PIE)) {
            graph = new Pie(statsToDisplay);
        } else if (format.equals(Report.GraphType.BAR)) {
            graph = new Bar(statsToDisplay);
        }

        return new ReportCommandResult(MESSAGE_SUCCESS, graph);
    }
}
