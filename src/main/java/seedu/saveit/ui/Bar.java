package seedu.saveit.ui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import seedu.saveit.model.expenditure.Tag;

/**
 * Constructs bar graph.
 */
public class Bar extends Graph {


    private TreeMap stats;
    private String organise;


    public Bar(HashMap stats, String organise) {
        this.stats = new TreeMap(stats);
        this.organise = organise;
    }


    /**
     * Constructs graph based on stats.
     * @return BarChart reflecting stats.
     */
    public BarChart constructGraph() {

        if(organise.equals("tag")) {

            CategoryAxis xAxis = new CategoryAxis();
            xAxis.setLabel("Tag");

            NumberAxis yAxis = new NumberAxis();
            yAxis.setLabel("Expenditure");

            BarChart bar = new BarChart(xAxis, yAxis);
            bar.setTitle("Expenditure breakdown");

            Set set = stats.keySet();
            Iterator itr = set.iterator();

            XYChart.Series dataSeries = new XYChart.Series();

            while (itr.hasNext()) {

                Tag index = ((Tag) itr.next());
                dataSeries.getData().add(new XYChart.Data(index.getTagName(), (double) stats.get(index)));
            }
            bar.getData().add(dataSeries);

            return bar;

        } else {

            assert organise.equals("month");

            CategoryAxis xAxis = new CategoryAxis();
            xAxis.setLabel("Month");

            NumberAxis yAxis = new NumberAxis();
            yAxis.setLabel("Expenditure");

            BarChart bar = new BarChart(xAxis, yAxis);
            bar.setTitle("Expenditure breakdown");

            Set set = stats.keySet();
            Iterator itr = set.iterator();

            XYChart.Series dataSeries = new XYChart.Series();

            while (itr.hasNext()) {

                String month = (String)itr.next();
                dataSeries.getData().add(new XYChart.Data(month, (double) stats.get(month)));
            }
            bar.getData().add(dataSeries);

            return bar;
        }

    }

}
