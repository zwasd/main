package seedu.address.ui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.CommandResult;
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

    private CommandResult resultToShow;

    /**
     * Creates a new Report Window.
     *
     * @param root Stage to use as the root of the Report Window.
     */
    public ReportWindow(Stage root) {
        super(FXML, root);
        //reportMessage.setText(REPORT_MESSAGE);
    }

    /**
     * Creates a new HelpWindow.
     */
    public ReportWindow() {
        this(new Stage());
    }

    public ReportWindow (CommandResult result) {
        this(new Stage());
        this.resultToShow = result;
    }
    /**
     * Shows the report window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing report page.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Pie chart displaying
     * user expenditures.
     */
    public void showPieChart() {
        HashMap stats = resultToShow.getStats();
        PieChart pie = new PieChart();

        Set set = stats.keySet();
        Iterator itr = set.iterator();

        while (itr.hasNext()) {

            Tag index = ((Tag) itr.next());
            PieChart.Data data = new PieChart.Data(index.getTagName(), (double) stats.get(index));
            pie.getData().add(data);
        }

        VBox vbox = new VBox(pie);
        Scene scene = new Scene(vbox, 400, 200);
        getRoot().setScene(scene);
        getRoot().setHeight(300);
        getRoot().setWidth(600);
    }

    /**
     * Shows the expenditure breakdown
     * in user inputted graph type.
     */
    public void showResult() {
        logger.fine("Showing report page.");

        //TODO : format
        showPieChart();
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

    //TODO: add in methods for handling diff graph
}
