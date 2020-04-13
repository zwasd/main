package seedu.saveit.logic.commands.report;

import static java.util.Objects.requireNonNull;
import static seedu.saveit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.HashMap;

import seedu.saveit.logic.commands.ReportCommand;
import seedu.saveit.logic.commands.ReportCommandResult;
import seedu.saveit.logic.commands.exceptions.CommandException;
import seedu.saveit.model.Model;
import seedu.saveit.model.report.Report;
import seedu.saveit.ui.Bar;
import seedu.saveit.ui.Graph;
import seedu.saveit.ui.Pie;


/**
 * View report command in report window.
 */
public class ReportWindowViewCommand extends ReportCommand {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_SUCCESS = "Report is generated";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows the report. "
            + "\nParameters: (note: no prefixes required)\n "
            + "START_DATE: YYYY-MM-DD  "
            + "END_DATE:  YYYY-MM-DD  "
            + " GRAPH_TYPE: pie | bar  "
            + " ORGANISATION : tag | month"
            + "\n"
            + "Example: view 2020-03-22 2020-03-25 pie tag ";

    private HashMap statsToDisplay;
    private Report.GraphType format;
    private Report toView;
    private Graph graph;

    public ReportWindowViewCommand(Report toView) {
        requireNonNull(toView);
        this.toView = toView;
    }

    @Override
    public ReportCommandResult execute(Model model) throws CommandException {

        if (toView.getOrganise().equals("tag")) {
            statsToDisplay = new GenerateStats(toView, model).generateStatsByTags();
        } else if (toView.getOrganise().equals("month")) {
            statsToDisplay = new GenerateStats(toView, model).generateStatsByMonth();
        } else {
            throw new CommandException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        format = toView.getFormat();

        if (format.equals(Report.GraphType.PIE)) {
            graph = new Pie(statsToDisplay, toView.getOrganise());
        } else if (format.equals(Report.GraphType.BAR)) {
            graph = new Bar(statsToDisplay, toView.getOrganise());
        } else {
            throw new CommandException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        return new ReportCommandResult(MESSAGE_SUCCESS, graph);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReportWindowViewCommand// instanceof handles nulls
                && toView.equals(((ReportWindowViewCommand) other).toView));

    }
}
