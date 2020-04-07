package seedu.saveit.ui;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.HashMap;
import java.util.logging.Logger;

import javafx.event.EventHandler;
import javafx.print.PageLayout;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.Chart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.transform.Scale;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import seedu.saveit.commons.core.LogsCenter;
import seedu.saveit.logic.Logic;
import seedu.saveit.logic.commands.CommandResult;
import seedu.saveit.logic.commands.ReportCommandResult;
import seedu.saveit.logic.commands.exceptions.CommandException;
import seedu.saveit.logic.parser.exceptions.ParseException;
import seedu.saveit.model.report.ExportFile;
import seedu.saveit.ui.exceptions.PrinterException;

/**
 * The Report Window. Provides statistics on expenditure
 * based on the user input. Supports export feature as well
 * in the future.
 */
public class ReportWindow extends UiPart<Stage> {

    //TODO: extract out the change scene code and make it a method

    public static final String REPORT_MESSAGE = "Generating report...";

    private static final Logger logger = LogsCenter.getLogger(ReportWindow.class);
    private static final String FXML = "ReportWindow.fxml";

    private Logic logic;
    private ReportCommandBox box;
    private ResultDisplay display;
    private MenuBar menuBar;
    private Graph currentGraph;

    /**
     * Creates a new Report Window.
     *
     * @param root Stage to use as the root of the Report Window.
     */
    public ReportWindow(Stage root) {
        super(FXML, root);
        root.initModality(Modality.APPLICATION_MODAL);

    }

    /**
     * Creates a new HelpWindow.
     */
    public ReportWindow() {
        this(new Stage());
        init();
    }

    /**
     * Initialise the report window
     * with necessary components.
     */
    private void init() {
        this.box = new ReportCommandBox(this::executeReportWindowCommand);
        this.display = new ResultDisplay();
        this.menuBar = new MenuBar();
        initMenu();
        initStyle();
        initCloseHandler();
    }

    /**
     * Initialise the menu
     * component of the report window.
     */
    private void initMenu() {

        //help item
        Label label = new Label("Help");
        label.setFont(new Font("Segoe UI Light", 14));
        label.setOnMouseClicked(click -> {
            try {
                executeReportWindowCommand("help");
            } catch (CommandException | ParseException | PrinterException e) {
                return;
            }
        });

        // print item
        Menu menu = new Menu("", label);
        menuBar.getMenus().add(menu);

        Label label1 = new Label("Print");
        label1.setFont(new Font("Segoe UI Light", 14));
        label1.setOnMouseClicked(click -> {
            try {
                if (currentGraph != null) {
                    print();
                } else {
                    display.setFeedbackToUser("Construct graph before printing.");
                }
            } catch (PrinterException e) {
                logger.info("Invalid printer");
                display.setFeedbackToUser(e.getMessage());
            }
        });

        Menu menu1 = new Menu("", label1);
        menuBar.getMenus().add(menu1);
    }

    private void initStyle() {
        getRoot().initStyle(StageStyle.UTILITY);
    }

    /**
     * TODO: ADD DOC
     */
    private void initCloseHandler() {
        getRoot().setOnCloseRequest(new EventHandler<>() {
            @Override
            public void handle(WindowEvent event) {
                display.clear();
                getRoot().hide();
            }
        });
    }

    /**
     * Returns true if the report window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the report window.
     */
    public void hide() {
        getRoot().hide();
    }


    public void addLogic(Logic logic) {
        this.logic = logic;
    }

    private void setScene(Node graph) {
        VBox topBox = new VBox(menuBar, box.getRoot());
        VBox vbox = new VBox(topBox, display.getRoot(), graph);
        Scene scene = new Scene(vbox);
        scene.getStylesheets().addAll(new File("src/main/resources/view/DarkTheme.css").toURI().toString());
        getRoot().setScene(scene);
        getRoot().show();
    }

    /**
     * Shows the report window.
     * Method is called when the report
     * button in Main Window in clicked.
     *
     * @throws IllegalStateException <ul>
     *                               <li>
     *                               if this method is called on a thread other than the JavaFX Application Thread.
     *                               </li>
     *                               <li>
     *                               if this method is called during animation or layout processing.
     *                               </li>
     *                               <li>
     *                               if this method is called on the primary stage.
     *                               </li>
     *                               <li>
     *                               if {@code dialogStage} is already showing.
     *                               </li>
     *                               </ul>
     */
    public void showEmpty() {
        logger.fine("Showing empty report page.");
        setScene((Node) new Pie(new HashMap(), "tag").constructGraph());
    }

    /**
     * Shows the expenditure breakdown
     * in user inputted graph type.
     * Method is called when input is from
     * Main Window.
     */
    public void showResult(CommandResult result) {
        logger.fine("Showing report page.");
        this.currentGraph = result.getGraph();
        setScene((Node) currentGraph.constructGraph());
    }

    /**
     * Shows the expenditure breakdown
     * in user inputted graph type.
     * Method is called when input is from
     * Report Window.
     */
    public void showResult(ReportCommandResult result) {
        logger.fine("Showing report page.");
        this.currentGraph = result.getGraph();
        setScene((Node) currentGraph.constructGraph());
    }

    /**
     * Sends a print job of report from report window to printer.
     *
     * @throws PrinterException is thrown when printer cannot
     *                          successfully finish a job.
     */
    public void print() throws PrinterException {
        logger.fine("Printing");
        display.setFeedbackToUser("Printing");

        assert currentGraph != null;
        printerJob();

    }

    /**
     * Invokes printer job from Javafx.
     *
     * @throws PrinterException if job cannot finish.
     */
    public void printerJob() throws PrinterException {
        PrinterJob printerJob = PrinterJob.createPrinterJob();
        PageLayout pageLayout = printerJob.getJobSettings().getPageLayout();

        WritableImage snapshot = snapshot();

        ImageView ivSnapshot = new ImageView(snapshot);
        double scaleX = pageLayout.getPrintableWidth() / ivSnapshot.getImage().getWidth();
        double scaleY = pageLayout.getPrintableHeight() / ivSnapshot.getImage().getHeight();
        double scale = Math.min(scaleX, scaleY);
        if (scale < 1.0) {
            ivSnapshot.getTransforms().add(new Scale(scale, scale));
        }

        if (printerJob != null) {
            boolean jobStatus = printerJob.printPage(ivSnapshot);
            ;
            if (jobStatus) {
                printerJob.endJob();
            } else {
                printerJob.cancelJob();
                throw new PrinterException("Set available printer as default printer");
            }
        }

    }

    /**
     * Exports report.
     *
     * @param fileName fileName of the file to export to.
     */
    public void export(String fileName) {

        try {
            display.setFeedbackToUser("Exporting.");
            WritableImage img = snapshot();
            ExportFile file = new ExportFile(fileName, currentGraph);
            file.export(img);
        } catch (IOException e) {

            if (e instanceof FileAlreadyExistsException) {
                display.setFeedbackToUser("The file " + fileName + " already exists.");
            } else {
                display.setFeedbackToUser("Reported cannot be exported.");
            }
        }
    }

    /**
     * Snapshot of current graph.
     *
     * @return image of graph.
     */
    public WritableImage snapshot() {
        assert currentGraph != null;
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

    /**
     * Executor method for report command box.
     */
    private ReportCommandResult executeReportWindowCommand(String commandText)
            throws CommandException, ParseException, PrinterException {

        ReportCommandResult result = null;
        try {
            result = logic.executeReportWindowCommand(commandText);
            logger.info("command executed " + commandText);
            display.setFeedbackToUser(result.getFeedbackToUser());

            if (result.isExitReport()) {
                currentGraph = null;
                display.clear();
                getRoot().hide();
            } else if (result.isPrintReport() || result.isExportReport()) {
                if (currentGraph != null) {
                    if (result.isPrintReport()) {
                        print();
                    } else {
                        assert result.isPrintReport();
                        export(result.getFileName());
                    }
                } else {
                    display.setFeedbackToUser("Construct graph before printing");
                }
            } else {
                showResult(result);
            }

        } catch (CommandException | ParseException | PrinterException e) {

            if (e instanceof CommandException || e instanceof ParseException) {
                logger.info("Invalid command :" + commandText);
            } else if (e instanceof PrinterException) {
                logger.info("Invalid printer");
            }

            display.setFeedbackToUser(e.getMessage());
            throw e;
        }
        return result;
    }
}
