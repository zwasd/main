package seedu.saveit.ui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import seedu.saveit.model.expenditure.Tag;

/**
 * Constructs bar graph.
 */
public class Bar extends Graph {


    private HashMap stats;


    public Bar(HashMap stats) {
        this.stats = stats;
    }


    /**
     * Constructs graph based on stats.
     * @return BarChart reflecting stats.
     */
    public BarChart constructGraph() {
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
    }

}
