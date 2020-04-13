package seedu.saveit.ui;

import java.util.HashMap;
import java.util.logging.Logger;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
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
import seedu.saveit.ui.exceptions.PrinterException;

/**
 * The Report Window. Provides statistics on expenditure
 * based on the user input. Supports export feature as well
 * in the future.
 */
public class ReportWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(ReportWindow.class);
    private static final String FXML = "ReportWindow.fxml";
    private static Graph currentGraph;

    private Logic logic;
    private ReportCommandBox box;
    private ResultDisplay display;
    private MenuBar menuBar;

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

                executeReportWindowCommand("print");

            } catch (Exception e) {
                logger.info("Invalid or no printer");
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
     * Initialises close handler.
     */
    private void initCloseHandler() {
        getRoot().setOnCloseRequest(new EventHandler<>() {
            @Override
            public void handle(WindowEvent event) {
                currentGraph = null;
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
        //scene.getStylesheets().addAll("@DarkTheme.css");
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
        currentGraph = result.getGraph();
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
        currentGraph = result.getGraph();
        setScene((Node) currentGraph.constructGraph());
    }

    public static Graph getGraph() {
        return currentGraph;
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

            } else if (result.isChangeView()) {
                showResult(result);
            }

        } catch (CommandException | ParseException e) {

            logger.info("Invalid command :" + commandText);
            display.setFeedbackToUser(e.getMessage());
            throw e;
        }
        return result;
    }
}
