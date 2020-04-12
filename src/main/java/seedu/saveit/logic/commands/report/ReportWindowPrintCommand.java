package seedu.saveit.logic.commands.report;

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

import seedu.saveit.logic.commands.ReportCommand;
import seedu.saveit.logic.commands.ReportCommandResult;
import seedu.saveit.logic.commands.exceptions.CommandException;
import seedu.saveit.model.Model;
import seedu.saveit.ui.Graph;
import seedu.saveit.ui.ReportWindow;
import seedu.saveit.ui.exceptions.PrinterException;

/**
 * Prints the report.
 */

public class ReportWindowPrintCommand extends ReportCommand {

    public static final String COMMAND_WORD = "print";
    public static final String MESSAGE_PRINT = "Setting up print jpb.";

    /**
     * Invokes printer job from Javafx.
     *
     * @throws PrinterException if job cannot finish.
     */
    public void printerJob(WritableImage snapshot) throws CommandException {

        try {

            PrinterJob printerJob = PrinterJob.createPrinterJob();
            PageLayout pageLayout = printerJob.getJobSettings().getPageLayout();
            ImageView imgView = new ImageView(snapshot);

            double scaleX = pageLayout.getPrintableWidth() / imgView.getImage().getWidth();
            double scaleY = pageLayout.getPrintableHeight() / imgView.getImage().getHeight();
            double scale = Math.min(scaleX, scaleY);
            if (scale < 1.0) {
                imgView.getTransforms().add(new Scale(scale, scale));
            }

            boolean jobStatus = printerJob.printPage(imgView);
            if (jobStatus) {
                printerJob.endJob();

            } else {
                printerJob.cancelJob();
                throw new PrinterException("Set available printer as default "
                        + "printer before printing.");
            }

        } catch (Exception e) {

            throw new CommandException("Set available printer as default "
                    + "printer before printing.");
        }
    }


    /**
     * Takes a snapshot of report.
     */
    public WritableImage snapshot() throws CommandException {

        Graph currentGraph = ReportWindow.getGraph();
        if (currentGraph == null) {
            throw new CommandException("Construct graph before printing.");
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
        WritableImage snapshot = snapshot();
        printerJob(snapshot);
        return new ReportCommandResult(MESSAGE_PRINT);
    }
}
