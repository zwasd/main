package seedu.saveit.ui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

import javafx.geometry.Side;
import javafx.scene.chart.PieChart;

import seedu.saveit.model.expenditure.Tag;

/**
 * Constructs pie chart.
 */
public class Pie extends Graph {

    private HashMap stats;
    private String organise;

    public Pie(HashMap stats, String organise) {
        this.stats = stats;
        this.organise = organise;
    }

    /**
     * Constructs graph based on stats
     *
     * @return PieChart reflecting stats.
     */
    public PieChart constructGraph() {

        PieChart pie = new PieChart();
        pie.setTitle("Expenditure Breakdown");
        pie.setLabelLineLength(10);
        pie.setLegendSide(Side.RIGHT);
        Set set = stats.keySet();
        Iterator itr = set.iterator();

        if (organise.equals("tag")) {

            while (itr.hasNext()) {

                Tag index = ((Tag) itr.next());
                PieChart.Data data = new PieChart.Data(index.getTagName(), (double) stats.get(index));
                pie.getData().add(data);
            }

        } else {

            assert organise.equals("month");

            TreeMap sortedStats = new TreeMap(stats);
            while (itr.hasNext()) {
                String month = (String) itr.next();
                PieChart.Data data = new PieChart.Data(month, (double) sortedStats.get(month));
                pie.getData().add(data);
            }

        }
        return pie;
    }

}
