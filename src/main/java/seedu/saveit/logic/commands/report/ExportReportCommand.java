package seedu.saveit.logic.commands.report;

import static java.util.Objects.requireNonNull;
import static seedu.saveit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_FILENAME;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_GRAPH;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_ORGANISE;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_START_DATE;

import java.io.IOException;
import java.util.HashMap;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.Chart;
import javafx.scene.chart.PieChart;
import javafx.scene.image.WritableImage;

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
    public static final String MESSAGE_SUCCESS = "The report has been exported to Report/%1$s.png";
    public static final String MESSAGE_USAGE = ReportLevelParser.COMMAND_WORD + " " + COMMAND_WORD
            + ": Exports the report. "
            + "\n" + "Parameters: "
            + PREFIX_START_DATE + " START_DATE "
            + PREFIX_END_DATE + " END_DATE "
            + PREFIX_GRAPH + " GRAPH_TYPE "
            + PREFIX_ORGANISE + " ORGANISATION "
            + PREFIX_FILENAME + " FILE_NAME "
            + "\n"
            + "\tGRAPH_TYPE = pie | bar, ORGANISATION = tag | month\n"
            + "Example: " + ReportLevelParser.COMMAND_WORD + " " + COMMAND_WORD
            + " " + PREFIX_START_DATE + " 2020-03-22 "
            + PREFIX_END_DATE + " 2020-03-25 "
            + PREFIX_GRAPH + " pie "
            + PREFIX_ORGANISE + " tag "
            + PREFIX_FILENAME + " Report2 \n(will be exported as Report2.png)";

    private final Report toExport;
    private HashMap statsToExport;
    private Report.GraphType format;
    private Graph graph;
    private String fileName;

    public ExportReportCommand(Report toExport, String fileName) {
        requireNonNull(toExport);
        requireNonNull(fileName);
        this.toExport = toExport;
        this.fileName = fileName;
    }

    /**
     * Exports the report.
     */
    public void export(ExportFile file) throws CommandException {
        try {
            WritableImage img = snapshot(file.getGraph());
            file.export(img);
        } catch (IOException e) {
            throw new CommandException("File already exists.");
        }
    }

    /**
     * Takes a snapshot of the graph
     * to be exported.
     *
     * @return image of the graph.
     */
    public WritableImage snapshot(Graph graph) {
        Node node = (Node) graph.constructGraph();
        Scene sc = new Scene((Parent) node, 800, 600);
        Chart chart = null;

        if (node instanceof PieChart) {
            chart = (PieChart) node;

        } else if (node instanceof BarChart) {
            chart = (BarChart) node;
        }

        assert chart != null;

        chart.setAnimated(false);
        WritableImage img = new WritableImage(800, 600);
        node.snapshot(new SnapshotParameters(), img);

        return img;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {

        if (toExport.getOrganise().equals("tag")) {
            statsToExport = new GenerateStats(toExport, model).generateStatsByTags();
        } else if (toExport.getOrganise().equals("month")) {
            statsToExport = new GenerateStats(toExport, model).generateStatsByMonth();
        } else {
            throw new CommandException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        format = toExport.getFormat();

        if (format.equals(Report.GraphType.PIE)) {
            graph = new Pie(statsToExport, toExport.getOrganise());
        } else if (format.equals(Report.GraphType.BAR)) {
            graph = new Bar(statsToExport, toExport.getOrganise());
        } else {
            throw new CommandException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        ExportFile f = new ExportFile(fileName, graph);
        export(f);

        return new CommandResult(String.format(MESSAGE_SUCCESS, fileName), f);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExportReportCommand // instanceof handles nulls
                && toExport.equals(((ExportReportCommand) other).toExport)
                && fileName.equals(((ExportReportCommand) other).fileName));

    }


}
