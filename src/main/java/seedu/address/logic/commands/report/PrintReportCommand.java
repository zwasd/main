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
 * Prints report.
 */
public class PrintReportCommand extends Command {

    public static final String COMMAND_WORD = "print";
    public static final String MESSAGE_SUCCESS = "Printing report.";
    public static final String MESSAGE_USAGE = ReportLevelParser.COMMAND_WORD + " " + COMMAND_WORD
            + ": Prints the report. "
            + "\n" + "Parameters: "
            + PREFIX_START_DATE + " STAR DATE "
            + PREFIX_END_DATE + " END DATE "
            + PREFIX_GRAPH + " GRAPH TYPE" + "\n"
            + "Example: " + ReportLevelParser.COMMAND_WORD + " " + COMMAND_WORD
            + " " + PREFIX_START_DATE + " 2020-03-22 "
            + PREFIX_END_DATE + " 2020-03-25 "
            + PREFIX_GRAPH + " PIE";


    private final Report toPrint;
    private HashMap statsToPrint;
    private Report.GraphType format;
    private Graph graph;


    public PrintReportCommand(Report toPrint) {
        this.toPrint = toPrint;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        statsToPrint = new GenerateStats(toPrint, model).generateStatsByTags();
        format = toPrint.getFormat();

        if (format.equals(Report.GraphType.PIE)) {
            graph = new Pie(statsToPrint);
        } else if (format.equals(Report.GraphType.BAR)) {
            graph = new Bar(statsToPrint);
        }

        return new CommandResult(MESSAGE_SUCCESS, graph, false, false, true);
    }
}
