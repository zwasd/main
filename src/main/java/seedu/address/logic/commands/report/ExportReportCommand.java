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
import seedu.address.ui.Bar;
import seedu.address.ui.Graph;
import seedu.address.ui.Pie;


/**
 * Exports report.
 */
public class ExportReportCommand extends Command {

    public static final String COMMAND_WORD = "export";
    public static final String MESSAGE_SUCCESS = "Report exported to : %1$s";
    public static final String MESSAGE_FAIL = "Report cannot be exported";
    public static final String MESSAGE_USAGE = ReportLevelParser.COMMAND_WORD + " " + COMMAND_WORD
            + ": Exports the report. "
            + "\n" + "Parameters: "
            + PREFIX_START_DATE + " STAR DATE "
            + PREFIX_END_DATE + " END DATE "
            + PREFIX_GRAPH + " GRAPH TYPE" + "\n"
            + "Example: " + ReportLevelParser.COMMAND_WORD + " " + COMMAND_WORD
            + " " + PREFIX_START_DATE + " 2020-03-22 "
            + PREFIX_END_DATE + " 2020-03-25 "
            + PREFIX_GRAPH + " PIE";

    private final Report toExport;
    private HashMap statsToExport;
    private Report.GraphType format;
    private Graph graph;

    public ExportReportCommand(Report toExport) {
        this.toExport = toExport;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        statsToExport = new GenerateStats(toExport, model).generateStatsByTags();
        format = toExport.getFormat();

        if(format.equals(Report.GraphType.PIE)) {
            graph = new Pie(statsToExport);
        } else if (format.equals(Report.GraphType.BAR)) {
            graph = new Bar(statsToExport);
        }

        return new CommandResult(MESSAGE_SUCCESS, graph, true, false, false);
    }
}
