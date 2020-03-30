package seedu.address.ui;



import java.io.File;
import java.util.logging.Logger;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
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

    public static final String REPORT_MESSAGE = "Generating report...";

    private static final Logger logger = LogsCenter.getLogger(ReportWindow.class);
    private static final String FXML = "ReportWindow.fxml";

    private Logic logic;
    private ReportCommandBox box;
    private ResultDisplay display;
    private MenuBar menuBar;
    private VBox currentVBox;
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

    private void init() {
        this.box = new ReportCommandBox(this::executeReportWindowCommand);
        this.display = new ResultDisplay();
        this.menuBar = new MenuBar();
        initMenu();
        initStyle();
        initCloseHandler();
    }

    private void initMenu() {
        Label label = new Label("Export");
        label.setFont(new Font("Segoe UI Light", 14 ));
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
        PieChart emptyPie = new PieChart();
        VBox topBox = new VBox(menuBar, box.getRoot());
        VBox vbox = new VBox(topBox, display.getRoot());
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

        Graph graph = null;

        if (result.isPieGraph()) {
            graph = new Pie();

        } else if (result.isBarGraph()) {
            graph = new Bar();
        }

        assert graph != null;
        VBox vBox = new VBox(box.getRoot(), display.getRoot(), (Node) graph.getGraph(result));
        this.currentVBox = vBox;
        Scene scene = new Scene(vBox);
        scene.getStylesheets().addAll(new File("src/main/resources/view/DarkTheme.css").toURI().toString());
        getRoot().setScene(scene);
        getRoot().show();

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
    //TODO: add in methods for handling diff graph

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

                Graph graph = null;

                if (result.isPieGraph()) {
                    graph = new Pie();

                } else if (result.isBarGraph()) {
                    graph = new Bar();
                }

                assert graph != null;
                VBox vBox = new VBox(box.getRoot(), display.getRoot(), (Node)graph.getGraph(result));
                this.currentVBox =  vBox;
                Scene scene = new Scene(vBox);
                getRoot().setScene(scene);
                getRoot().show();
            }

        } catch (CommandException | ParseException e) {
            logger.info("Invalid command :" + commandText);
            display.setFeedbackToUser(e.getMessage());
        }
        return result;
    }
}
