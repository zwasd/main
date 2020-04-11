package seedu.saveit.logic.commands.report;

import java.io.IOException;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.Chart;
import javafx.scene.chart.PieChart;
import javafx.scene.image.WritableImage;

import seedu.saveit.logic.commands.ReportCommand;
import seedu.saveit.logic.commands.ReportCommandResult;
import seedu.saveit.logic.commands.exceptions.CommandException;
import seedu.saveit.model.Model;
import seedu.saveit.model.report.ExportFile;
import seedu.saveit.ui.Graph;
import seedu.saveit.ui.ReportWindow;

/**
 * Export command for report window.
 */
public class ReportWindowExportCommand extends ReportCommand {

    public static final String COMMAND_WORD = "export";
    public static final String MESSAGE_EXPORT = "Trying to export.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " fileName" + "\n"
            + "eg export hello";

    private String fileName;

    public ReportWindowExportCommand(String fileName) {
        this.fileName = fileName;
    }


    /**
     * Exports report.
     *
     * @param fileName fileName of the file to export to.
     */
    public void export(String fileName) throws CommandException {

        try {
            WritableImage img = snapshot();
            ExportFile file = new ExportFile(fileName, ReportWindow.getGraph());
            file.export(img);
        } catch (Exception e) {

            if (e instanceof NullPointerException) {
                throw new CommandException("Construct graph before printing.");
            } else if (e instanceof IOException) {
                throw new CommandException("File already exists");
            }
        }
    }

    /**
     * Takes snapshot of report to export.
     */
    public WritableImage snapshot() throws NullPointerException {

        Graph currentGraph = ReportWindow.getGraph();
        if (currentGraph == null) {

            throw new NullPointerException();
        }

        Node node;
        node = (Node) currentGraph.constructGraph();
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
    public ReportCommandResult execute(Model model) throws CommandException {
        export(fileName);
        return new ReportCommandResult(MESSAGE_EXPORT, fileName);
    }
}
