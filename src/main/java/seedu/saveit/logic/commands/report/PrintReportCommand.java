package seedu.saveit.logic.commands.report;

import static java.util.Objects.requireNonNull;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_GRAPH;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_ORGANISE;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_START_DATE;

import java.util.HashMap;

import javafx.print.PageLayout;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.Chart;
import javafx.scene.chart.PieChart;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.transform.Scale;

import seedu.saveit.logic.commands.Command;
import seedu.saveit.logic.commands.CommandResult;
import seedu.saveit.logic.commands.exceptions.CommandException;
import seedu.saveit.logic.parser.report.ReportLevelParser;
import seedu.saveit.model.Model;
import seedu.saveit.model.report.Report;
import seedu.saveit.ui.Bar;
import seedu.saveit.ui.Graph;
import seedu.saveit.ui.Pie;
import seedu.saveit.ui.exceptions.PrinterException;


/**
 * Prints report.
 */
public class PrintReportCommand extends Command {

    public static final String COMMAND_WORD = "print";
    public static final String MESSAGE_SUCCESS = "Setting up print job.";
    public static final String MESSAGE_FAIL = "Report cannot be printed";
    public static final String MESSAGE_USAGE = ReportLevelParser.COMMAND_WORD + " " + COMMAND_WORD
            + ": Prints the report. "
            + "\n" + "Parameters:  "
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
            + PREFIX_ORGANISE + " tag ";


    private final Report toPrint;
    private HashMap statsToPrint;
    private Report.GraphType format;
    private Graph graph;


    public PrintReportCommand(Report toPrint) {
        requireNonNull(toPrint);
        this.toPrint = toPrint;
    }

    /**
     * Invokes printer job of Javafx.
     *
     * @throws PrinterException if job cannot finish.
     */
    public void printerJob(Graph graph) throws CommandException {

        try {
            PrinterJob printerJob = PrinterJob.createPrinterJob();
            PageLayout pageLayout = printerJob.getJobSettings().getPageLayout();

            WritableImage snapshot = snapshot(graph);

            ImageView ivSnapshot = new ImageView(snapshot);
            double scaleX = pageLayout.getPrintableWidth() / ivSnapshot.getImage().getWidth();
            double scaleY = pageLayout.getPrintableHeight() / ivSnapshot.getImage().getHeight();
            double scale = Math.min(scaleX, scaleY);
            if (scale < 1.0) {
                ivSnapshot.getTransforms().add(new Scale(scale, scale));
            }
            boolean jobStatus = printerJob.printPage(ivSnapshot);
            if (jobStatus) {
                printerJob.endJob();
            } else {
                printerJob.cancelJob();
                throw new PrinterException("Set available printer as default "
                        + "printer before printing");
            }

        } catch (Exception e) {
            throw new CommandException("Set available printer as default "
                    + "printer before printing.");
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
        } else {
            throw new CommandException(MESSAGE_FAIL);
        }

        printerJob(graph);

        return new CommandResult(MESSAGE_SUCCESS, graph, false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PrintReportCommand // instanceof handles nulls
                && toPrint.equals(((PrintReportCommand) other).toPrint));

    }

}
