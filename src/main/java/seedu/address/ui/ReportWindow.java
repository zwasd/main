package seedu.address.ui;

import java.io.File;
import java.util.logging.Logger;

import javafx.event.EventHandler;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.transform.Scale;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ReportCommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

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
    private Graph currentGraphDisplay;

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
        Label label = new Label("Export");
        label.setFont(new Font("Segoe UI Light", 14));
        label.setOnMouseClicked(click -> {
        });
        Menu menu = new Menu("", label);
        menuBar.getMenus().add(menu);
    }

    private void initStyle() {
        getRoot().initStyle(StageStyle.UTILITY);
    }

    /**
     * TODO: ADD DOC
     */
    private void initCloseHandler() {
        getRoot().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                display.clear();
                getRoot().hide();
            }
        });
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
        this.currentGraphDisplay = new Pie();
        VBox topBox = new VBox(menuBar, box.getRoot());
        VBox vbox = new VBox(topBox, display.getRoot(), (Node) currentGraphDisplay.getGraph());
        Scene scene = new Scene(vbox);
        scene.getStylesheets().addAll(new File("src/main/resources/view/DarkTheme.css").toURI().toString());
        getRoot().setScene(scene);
        getRoot().show();
    }


    /**
     * Shows the expenditure breakdown
     * in user inputted graph type.
     * Method is called when input is from
     * Main Window.
     */
    public void showResult(CommandResult result) {
        logger.fine("Showing report page.");


        if (result.isPieGraph()) {
            this.currentGraphDisplay = new Pie();

        } else if (result.isBarGraph()) {
            this.currentGraphDisplay = new Bar();
        }

        assert currentGraphDisplay != null;
        currentGraphDisplay.constructGraph(result);
        VBox topBox = new VBox(menuBar, box.getRoot());
        VBox vbox = new VBox(topBox, display.getRoot(), (Node) currentGraphDisplay.getGraph());
        Scene scene = new Scene(vbox);
        scene.getStylesheets().addAll(new File("src/main/resources/view/DarkTheme.css").toURI().toString());
        getRoot().setScene(scene);
        getRoot().show();

    }

    /**
     * Shows the expenditure breakdown
     * in user inputted graph type.
     * Method is called when input is from
     * Report Window.
     */
    public void showResult (ReportCommandResult result) {
        logger.fine("Showing report page.");

        if (result.isPieGraph()) {
            this.currentGraphDisplay = new Pie();

        } else if (result.isBarGraph()) {
            this.currentGraphDisplay = new Bar();
        }

        assert currentGraphDisplay != null;
        currentGraphDisplay.constructGraph(result);
        VBox topBox = new VBox(menuBar, box.getRoot());
        VBox vbox = new VBox(topBox, display.getRoot(), (Node) currentGraphDisplay.getGraph());
        Scene scene = new Scene(vbox);
        scene.getStylesheets().addAll(new File("src/main/resources/view/DarkTheme.css").toURI().toString());
        getRoot().setScene(scene);
        getRoot().show();
    }


    public void export(CommandResult result)  {
        logger.fine("Exporting");
        Graph toExport = null;
        Node graph;

        if (result.isPieGraph()) {
            toExport = new Pie();
        } else if (result.isBarGraph()) {
            toExport = new Bar();
        }

        assert toExport != null;
        toExport.constructGraph(result);
        graph = (Node) toExport.getGraph();
        Printer printer = Printer.getDefaultPrinter();
        PageLayout pageLayout = printer.createPageLayout(Paper.A6, PageOrientation.LANDSCAPE, Printer.MarginType.DEFAULT);
        double scaleX = pageLayout.getPrintableWidth() / graph.getBoundsInParent().getWidth();
        double scaleY = pageLayout.getPrintableHeight() / graph.getBoundsInParent().getHeight();
        graph.getTransforms().add(new Scale(scaleX, scaleY));

        PrinterJob printerJob = PrinterJob.createPrinterJob();

        if (printerJob != null) {
            boolean jobStatus = printerJob.printPage(graph);
            if(jobStatus) {
                printerJob.endJob();
            } else {
                printerJob.cancelJob();
            }
        }


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

    /**
     * Focuses on the report window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    public void addLogic(Logic logic) {
        this.logic = logic;
    }

    /**
     * Executor method for report command box.
     */
    private ReportCommandResult executeReportWindowCommand(String commandText) {

        ReportCommandResult result = null;
        try {
            result = logic.executeReportWindowCommand(commandText);
            logger.info("command executed " + commandText);
            display.setFeedbackToUser(result.getFeedbackToUser());

            if (result.getExitReport()) {
                display.clear();
                getRoot().hide();
            } else {
                showResult(result);
            }

        } catch (CommandException | ParseException e) {
            logger.info("Invalid command :" + commandText);
            display.setFeedbackToUser(e.getMessage());
        }
        return result;
    }

}
