package seedu.saveit.ui;

import java.time.YearMonth;
import java.util.Comparator;
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
        pie.setStartAngle(180.0);
        pie.setLegendSide(Side.RIGHT);

        if (organise.equals("tag")) {

            Set set = stats.keySet();
            Iterator itr = set.iterator();
            while (itr.hasNext()) {

                Tag index = ((Tag) itr.next());
                PieChart.Data data = new PieChart.Data(index.getTagName(), (double) stats.get(index));
                pie.getData().add(data);
            }

        } else {

            assert organise.equals("month");

            TreeMap sortedStats = new TreeMap(new Comparator<String>() {
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
                PieChart.Data data = new PieChart.Data(month, (double) sortedStats.get(month));
                pie.getData().add(data);
            }

        }
        return pie;
    }

}
