package seedu.address.ui;

import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ReportCommandResult;
import seedu.address.model.tag.Tag;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Graph {


    /**
     * Pie chart displaying
     * user expenditures.
     */
    public PieChart getPieChart(CommandResult result) {
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
    public PieChart getPieChart(ReportCommandResult result) {
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

    public BarChart getBarChart(CommandResult command) {

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

    public BarChart getBarChart(ReportCommandResult command) {

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
