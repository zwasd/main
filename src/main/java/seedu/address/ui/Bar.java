package seedu.address.ui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ReportCommandResult;
import seedu.address.model.tag.Tag;

/**
 * Constructs bar graph.
 */
public class Bar extends Graph {

    @Override
    public BarChart getGraph(CommandResult command) {

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Tag");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Expenditure");

        BarChart bar = new BarChart(xAxis, yAxis);
        bar.setTitle("Expenditure breakdown");

        HashMap stats = command.getStats();

        Set set = stats.keySet();
        Iterator itr = set.iterator();

        XYChart.Series dataSeries = new XYChart.Series();

        while (itr.hasNext()) {

            Tag index = ((Tag) itr.next());
            dataSeries.getData().add(new XYChart.Data(index.getTagName(), (double) stats.get(index)));
        }
        bar.getData().add(dataSeries);


        return bar;

    }

    @Override
    public BarChart getGraph(ReportCommandResult command) {

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Tag");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Expenditures");

        BarChart bar = new BarChart(xAxis, yAxis);

        HashMap stats = command.getStats();

        Set set = stats.keySet();
        Iterator itr = set.iterator();

        XYChart.Series dataSeries = new XYChart.Series();

        while (itr.hasNext()) {

            Tag index = ((Tag) itr.next());
            dataSeries.getData().add(new XYChart.Data(index.getTagName(), (double) stats.get(index)));
        }
        bar.getData().add(dataSeries);

        return bar;

    }
}
