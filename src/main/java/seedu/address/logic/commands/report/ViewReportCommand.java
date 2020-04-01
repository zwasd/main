package seedu.address.logic.commands.report;

import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRAPH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;

import java.util.HashMap;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.report.ReportLevelParser;
import seedu.address.model.Model;
import seedu.address.model.Report;

/**
 * Views report.
 */
public class ViewReportCommand extends Command {


    public static final String COMMAND_WORD = "view";
    public static final String MESSAGE_SUCCESS = "Report is generated";

    public static final String MESSAGE_USAGE = ReportLevelParser.COMMAND_WORD + " " + COMMAND_WORD
            + ": Shows the report. "
            + "\n" + "Parameters: "
            + PREFIX_START_DATE + " STAR DATE "
            + PREFIX_END_DATE + " END DATE "
            + PREFIX_GRAPH + " GRAPH TYPE " + "\n"
            + "Example: " + ReportLevelParser.COMMAND_WORD + " " + COMMAND_WORD
            + " " + PREFIX_START_DATE + " 2020-03-22 "
            + PREFIX_END_DATE + " 2020-03-25 "
            + PREFIX_GRAPH + " PIE";

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
        return new CommandResult(MESSAGE_SUCCESS, format, statsToDisplay, false, true, false);
    }
}
