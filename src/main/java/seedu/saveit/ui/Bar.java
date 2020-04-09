package seedu.saveit.ui;

import java.time.YearMonth;
import java.util.Comparator;
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


    private HashMap stats;
    private String organise;


    public Bar(HashMap stats, String organise) {
        this.stats = stats;
        this.organise = organise;
    }


    /**
     * Constructs graph based on stats.
     *
     * @return BarChart reflecting stats.
     */
    public BarChart constructGraph() {

        if (organise.equals("tag")) {

            CategoryAxis yAxis = new CategoryAxis();
            yAxis.setLabel("Tag");

            NumberAxis xAxis = new NumberAxis();
            xAxis.setLabel("Expenditure");

            BarChart bar = new BarChart(xAxis, yAxis);
            bar.setTitle("Expenditure breakdown");
            bar.setLegendVisible(false);

            Set set = stats.keySet();
            Iterator itr = set.iterator();

            XYChart.Series dataSeries = new XYChart.Series();

            while (itr.hasNext()) {

                Tag index = ((Tag) itr.next());
                dataSeries.getData().add(new XYChart.Data((double) stats.get(index),
                        index.getTagName() + " : $" + stats.get(index)));
            }
            bar.getData().add(dataSeries);

            return bar;

        } else {

            assert organise.equals("month");

            CategoryAxis yAxis = new CategoryAxis();
            yAxis.setLabel("Month");

            NumberAxis xAxis = new NumberAxis();
            xAxis.setLabel("Expenditure");

            BarChart bar = new BarChart(xAxis, yAxis);
            bar.setTitle("Expenditure breakdown");
            bar.setLegendVisible(false);

            XYChart.Series dataSeries = new XYChart.Series();

            TreeMap<String, Double> sortedStats = new TreeMap(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    YearMonth ym1 = YearMonth.parse(o1);
                    YearMonth ym2 = YearMonth.parse(o2);
                    return ym1.compareTo(ym2);
                }
            });

            sortedStats.putAll(stats);

            Set set = sortedStats.keySet();
            Iterator itr = set.iterator();
            while (itr.hasNext()) {

                String month = (String) itr.next();


                dataSeries.getData().add(new XYChart.Data((double) sortedStats.get(month),
                        month + " : $" + sortedStats.get(month)));
            }

            bar.getData().add(dataSeries);

            return bar;
        }

    }


}
