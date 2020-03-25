package seedu.address.logic.commands.report;

import java.util.HashMap;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.report.ReportLevelParser;
import seedu.address.model.Model;
import seedu.address.model.Report;

/**
 * View report.
 */
public class ViewReportCommand extends Command {


    public static final String COMMAND_WORD = "view";
    public static final String MESSAGE_SUCCESS = "Report is generated";

    public static final String MESSAGE_USAGE = ReportLevelParser.COMMAND_WORD + " " + COMMAND_WORD
            + ": Shows the report. "
            + "\n" + "Parameters "
            + "Start Date : YYYY-MM-DD  "
            + "End Date :  YYYY-MM-DD  "
            + "Graph Type: PIE "
            + "Example : " + ReportLevelParser.COMMAND_WORD + " " + COMMAND_WORD + "\n"
            + "2020-03-22 " + "2020-03-25 " + "PIE";

    private final Report toView;
    private HashMap statsToDisplay;
    private Report.GraphType format;

    public ViewReportCommand(Report toView) {
        this.toView = toView;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        statsToDisplay = new GenerateStats(toView, model).generateStatsByTags();
        format = toView.getFormat();
        return new CommandResult(MESSAGE_SUCCESS, format, statsToDisplay);
    }
}
