package seedu.address.ui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ReportCommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

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

    /**
     * Creates a new Report Window.
     *
     * @param root Stage to use as the root of the Report Window.
     */
    public ReportWindow(Stage root) {
        super(FXML, root);

    }

    /**
     * Creates a new HelpWindow.
     */
    public ReportWindow() {
        this(new Stage());
         initStyle();
         initCloseHandler();
        this.box = new ReportCommandBox(this::executeReportWindowCommand);
        this.display = new ResultDisplay();
    }


    public void initStyle() {
        getRoot().initStyle(StageStyle.UTILITY);
    }

    public void initCloseHandler() {
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
        logger.fine("Showing report page.");
        VBox vbox = new VBox(box.getRoot(), display.getRoot(), new PieChart());
        Scene scene = new Scene(vbox);
        getRoot().setScene(scene);
        getRoot().show();
    }

    /**
     * Pie chart displaying
     * user expenditures.
     */
    public PieChart showPieChart(CommandResult result) {
        HashMap stats = result.getStats();
        PieChart pie = new PieChart();

        Set set = stats.keySet();
        Iterator itr = set.iterator();

        while (itr.hasNext()) {

            Tag index = ((Tag) itr.next());
            PieChart.Data data = new PieChart.Data(index.getTagName(), (double) stats.get(index));
            pie.getData().add(data);
        }

        return pie;

    }

    /**
     * Pie chart displaying
     * user expenditures.
     */
    public PieChart showPieChart(ReportCommandResult result) {
        HashMap stats = result.getStats();
        PieChart pie = new PieChart();

        Set set = stats.keySet();
        Iterator itr = set.iterator();

        while (itr.hasNext()) {

            Tag index = ((Tag) itr.next());
            PieChart.Data data = new PieChart.Data(index.getTagName(), (double) stats.get(index));
            pie.getData().add(data);
        }

        return pie;

    }

    /**
     * Shows the expenditure breakdown
     * in user inputted graph type.
     */
    public void showResult(CommandResult result) {
        logger.fine("Showing report page.");
        PieChart pie = showPieChart(result);
        VBox vbox = new VBox(box.getRoot(), display.getRoot(), pie);
        Scene scene = new Scene(vbox);
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
     * Executor method for command box.
     */
    private ReportCommandResult executeReportWindowCommand(String commandText)  {

        ReportCommandResult command = null;
        try {
            command = logic.executeReportWindowCommand(commandText);
            logger.info("command executed " + commandText);
            display.setFeedbackToUser(command.getFeedbackToUser());

            if (command.getExitReport()) {
                display.clear();
                getRoot().hide();
            } else {
                PieChart pie = showPieChart(command);
                VBox vbox = new VBox(box.getRoot(), display.getRoot(), pie);
                Scene scene = new Scene(vbox);
                getRoot().setScene(scene);
                getRoot().show();
            }

        } catch (CommandException | ParseException e) {
            logger.info("Invalid command :" + commandText);
            display.setFeedbackToUser(e.getMessage());
        }
        return command;
    }
}
