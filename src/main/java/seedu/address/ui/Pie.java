package seedu.address.ui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javafx.geometry.Side;
import javafx.scene.chart.PieChart;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ReportCommandResult;
import seedu.address.model.tag.Tag;

/**
 * Constructs pie chart.
 */
public class Pie extends Graph {

    private PieChart pie = null;

    /**
     * Returns pie.
     *
     * @return empty pie if pie is not constructed.
     */
    @Override
    public PieChart getGraph() {

        if (pie == null) {
            pie = new PieChart();
        }

        return pie;
    }

    @Override
    public void constructGraph(ReportCommandResult result) {
        HashMap stats = result.getStats();
        PieChart pie = new PieChart();
        pie.setTitle("Expenditure Breakdown");
        pie.setLabelLineLength(10);
        pie.setLegendSide(Side.RIGHT);

        Set set = stats.keySet();
        Iterator itr = set.iterator();


        while (itr.hasNext()) {

            Tag index = ((Tag) itr.next());
            PieChart.Data data = new PieChart.Data(index.getTagName(), (double) stats.get(index));
            pie.getData().add(data);
        }

        this.pie = pie;
    }

    /**
     * Pie chart displaying
     * user expenditures.
     */
    @Override
    public void constructGraph(CommandResult result) {
        HashMap stats = result.getStats();
        PieChart pie = new PieChart();

        Set set = stats.keySet();
        Iterator itr = set.iterator();

        while (itr.hasNext()) {

            Tag index = ((Tag) itr.next());
            PieChart.Data data = new PieChart.Data(index.getTagName(), (double) stats.get(index));
            pie.getData().add(data);
        }

        this.pie = pie;

    }
}
