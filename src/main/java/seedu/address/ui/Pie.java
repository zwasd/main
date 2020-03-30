package seedu.address.ui;


import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ReportCommandResult;
import seedu.address.model.tag.Tag;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Pie extends Graph {

    @Override
    public PieChart getGraph(ReportCommandResult result) {
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

        return pie;
    }

    /**
     * Pie chart displaying
     * user expenditures.
     */
    @Override
    public PieChart getGraph(CommandResult result) {
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
}
