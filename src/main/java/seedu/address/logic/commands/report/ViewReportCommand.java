package seedu.address.logic.commands.report;

import java.util.HashMap;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Report;

/**
 * View report.
 */
public class ViewReportCommand extends Command {


    public static final String COMMAND_WORD = "view";
    public static final String MESSAGE_SUCCESS = "Report is generated";
    public static final String MESSAGE_FAIL = "Report cannot be generated";

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
