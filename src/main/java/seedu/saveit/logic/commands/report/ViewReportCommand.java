package seedu.saveit.logic.commands.report;

import static java.util.Objects.requireNonNull;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_GRAPH;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_ORGANISE;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_START_DATE;

import java.util.HashMap;

import seedu.saveit.logic.commands.Command;
import seedu.saveit.logic.commands.CommandResult;
import seedu.saveit.logic.commands.exceptions.CommandException;
import seedu.saveit.logic.parser.report.ReportLevelParser;
import seedu.saveit.model.Model;
import seedu.saveit.model.report.Report;
import seedu.saveit.ui.Bar;
import seedu.saveit.ui.Graph;
import seedu.saveit.ui.Pie;

/**
 * Views report.
 */
public class ViewReportCommand extends Command {


    public static final String COMMAND_WORD = "view";
    public static final String MESSAGE_SUCCESS = "Report is generated";
    public static final String MESSAGE_FAIL = "Report cannot be generated";

    public static final String MESSAGE_USAGE = ReportLevelParser.COMMAND_WORD + " " + COMMAND_WORD
            + ": Shows the report. "
            + "\n" + "Parameters: "
            + PREFIX_START_DATE + " START_DATE "
            + PREFIX_END_DATE + " END_DATE "
            + PREFIX_GRAPH + " GRAPH_TYPE "
            + PREFIX_ORGANISE + " ORGANISATION "
            + "\n"
            + "\tGRAPH_TYPE = pie | bar, ORGANISATION = tag | month\n"
            + "Example: " + ReportLevelParser.COMMAND_WORD + " " + COMMAND_WORD
            + " " + PREFIX_START_DATE + " 2020-03-22 "
            + PREFIX_END_DATE + " 2020-03-25 "
            + PREFIX_GRAPH + " pie "
            + PREFIX_ORGANISE + " tag";

    private final Report toView;
    private HashMap statsToDisplay;
    private Report.GraphType format;
    private Graph graph;

    public ViewReportCommand(Report toView) {
        requireNonNull(toView);
        this.toView = toView;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {

        if (toView.getOrganise().equals("tag")) {
            statsToDisplay = new GenerateStats(toView, model).generateStatsByTags();
        } else if (toView.getOrganise().equals("month")) {
            statsToDisplay = new GenerateStats(toView, model).generateStatsByMonth();
        } else {
            throw new CommandException(MESSAGE_FAIL);
        }

        format = toView.getFormat();

        if (format.equals(Report.GraphType.PIE)) {
            graph = new Pie(statsToDisplay, toView.getOrganise());
        } else if (format.equals(Report.GraphType.BAR)) {
            graph = new Bar(statsToDisplay, toView.getOrganise());
        } else {
            throw new CommandException(MESSAGE_FAIL);
        }


        return new CommandResult(MESSAGE_SUCCESS, graph, false, true, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewReportCommand // instanceof handles nulls
                && toView.equals(((ViewReportCommand) other).toView));

    }

}
