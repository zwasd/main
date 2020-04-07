package seedu.saveit.logic.commands.report;

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
 * Prints report.
 */
public class PrintReportCommand extends Command {

    public static final String COMMAND_WORD = "print";
    public static final String MESSAGE_SUCCESS = "Printing report.";
    public static final String MESSAGE_FAIL = "Report cannot be printed";
    public static final String MESSAGE_USAGE = ReportLevelParser.COMMAND_WORD + " " + COMMAND_WORD
            + ": Prints the report. "
            + "\n" + "Parameters:  "
            + PREFIX_START_DATE + " START DATE "
            + PREFIX_END_DATE + " END DATE "
            + PREFIX_GRAPH + " GRAPH TYPE "
            + PREFIX_ORGANISE + " ORGANISE "
            + "\n"
            + "Example: " + ReportLevelParser.COMMAND_WORD + " " + COMMAND_WORD
            + " " + PREFIX_START_DATE + " 2020-03-22 "
            + PREFIX_END_DATE + " 2020-03-25 "
            + PREFIX_GRAPH + " pie "
            + PREFIX_ORGANISE + " tag ";


    private final Report toPrint;
    private HashMap statsToPrint;
    private Report.GraphType format;
    private Graph graph;


    public PrintReportCommand(Report toPrint) {
        this.toPrint = toPrint;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        if (toPrint.getOrganise().equals("tag")) {
            statsToPrint = new GenerateStats(toPrint, model).generateStatsByTags();
        } else if (toPrint.getOrganise().equals("month")) {
            statsToPrint = new GenerateStats(toPrint, model).generateStatsByMonth();
        } else {
            throw new CommandException(MESSAGE_FAIL);
        }

        format = toPrint.getFormat();

        if (format.equals(Report.GraphType.PIE)) {
            graph = new Pie(statsToPrint, toPrint.getOrganise());
        } else if (format.equals(Report.GraphType.BAR)) {
            graph = new Bar(statsToPrint, toPrint.getOrganise());
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
