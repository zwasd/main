package seedu.saveit.logic.commands.report;

import seedu.saveit.logic.commands.Command;
import seedu.saveit.logic.commands.CommandResult;
import seedu.saveit.logic.commands.exceptions.CommandException;
import seedu.saveit.logic.parser.report.ReportLevelParser;
import seedu.saveit.model.Model;
import seedu.saveit.model.Report;
import seedu.saveit.ui.Bar;
import seedu.saveit.ui.Graph;
import seedu.saveit.ui.Pie;

import java.util.HashMap;

import static seedu.saveit.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_GRAPH;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_START_DATE;

/**
 * Prints report.
 */
public class PrintReportCommand extends Command {

    public static final String COMMAND_WORD = "print";
    public static final String MESSAGE_SUCCESS = "Printing report.";
    public static final String MESSAGE_USAGE = ReportLevelParser.COMMAND_WORD + " " + COMMAND_WORD
            + ": Prints the report. "
            + "\n" + "Parameters: "
            + PREFIX_START_DATE + " START DATE "
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

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PrintReportCommand // instanceof handles nulls
                && toPrint.equals(((PrintReportCommand) other).toPrint));

    }

}
