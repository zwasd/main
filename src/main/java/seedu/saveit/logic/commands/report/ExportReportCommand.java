package seedu.saveit.logic.commands.report;

import static seedu.saveit.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_FILENAME;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_GRAPH;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_ORGANISE;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_START_DATE;

import java.util.HashMap;

import seedu.saveit.logic.commands.Command;
import seedu.saveit.logic.commands.CommandResult;
import seedu.saveit.logic.commands.exceptions.CommandException;
import seedu.saveit.logic.parser.report.ReportLevelParser;
import seedu.saveit.model.Model;
import seedu.saveit.model.report.ExportFile;
import seedu.saveit.model.report.Report;
import seedu.saveit.ui.Bar;
import seedu.saveit.ui.Graph;
import seedu.saveit.ui.Pie;


/**
 * Exports report.
 */
public class ExportReportCommand extends Command {

    public static final String COMMAND_WORD = "export";
    public static final String MESSAGE_SUCCESS = "Report is exporting.";
    public static final String MESSAGE_FAIL = "Report cannot be exported";
    public static final String MESSAGE_USAGE = ReportLevelParser.COMMAND_WORD + " " + COMMAND_WORD
            + ": Exports the report. "
            + "\n" + "Parameters: "
            + PREFIX_START_DATE + " STAR DATE "
            + PREFIX_END_DATE + " END DATE "
            + PREFIX_GRAPH + " GRAPH TYPE "
            + PREFIX_ORGANISE + " ORGANISE "
            + PREFIX_FILENAME + " FILE NAME "
            + "\n"
            + "Example: " + ReportLevelParser.COMMAND_WORD + " " + COMMAND_WORD
            + " " + PREFIX_START_DATE + " 2020-03-22 "
            + PREFIX_END_DATE + " 2020-03-25 "
            + PREFIX_GRAPH + " pie "
            + PREFIX_ORGANISE + " tag"
            + PREFIX_FILENAME + " Report ";

    private final Report toExport;
    private HashMap statsToExport;
    private Report.GraphType format;
    private Graph graph;
    private String fileName;

    public ExportReportCommand(Report toExport, String fileName) {
        this.toExport = toExport;
        this.fileName = fileName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        if (toExport.getOrganise().equals("tag")) {
            statsToExport = new GenerateStats(toExport, model).generateStatsByTags();
        } else if (toExport.getOrganise().equals("month")) {
            statsToExport = new GenerateStats(toExport, model).generateStatsByMonth();
        }

        format = toExport.getFormat();

        if (format.equals(Report.GraphType.PIE)) {
            graph = new Pie(statsToExport, toExport.getOrganise());
        } else if (format.equals(Report.GraphType.BAR)) {
            graph = new Bar(statsToExport, toExport.getOrganise());
        }

        ExportFile f = new ExportFile(fileName, graph);
        return new CommandResult(MESSAGE_SUCCESS, f, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExportReportCommand // instanceof handles nulls
                && toExport.equals(((ExportReportCommand) other).toExport));

    }


}
