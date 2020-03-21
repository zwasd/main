package seedu.address.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import seedu.address.model.expenditure.Date;
import seedu.address.model.expenditure.Expenditure;
import seedu.address.model.expenditure.UniqueExpenditureList;
import seedu.address.model.tag.Tag;

/**
 * Report.
 */
public class Report {
    private final Date startDate;
    private final Date endDate;
    private final GraphType graph;

    /**
     * Represents the different types
     * of graph that can be generated for report.
     */
    public enum GraphType {
        BAR, PIE, STACK;
    }

    public Report(Date startDate, Date endDate, GraphType graph) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.graph = graph;
    }

    /**
     * Getter method for user input graph type.
     * @return GraphType user inpurs
     */
    public GraphType getFormat() {
        return graph;
    }

    /**
     * Calculate expenditures under each tag.
     * @param model model manager.
     * @return HashMap mapping to tag to its spending.
     */
    public HashMap<Tag, Double> generateStatsByTags(Model model) {

        ReportableAccount acct = model.getReportableAccount();
        Map expenditures = acct.getExpFromToInclusive(startDate, endDate);
        Set keySet = expenditures.keySet();
        HashMap<Tag, Double> output = new HashMap<>();

        Iterator itr = keySet.iterator();

        while (itr.hasNext()) {

            UniqueExpenditureList list = (UniqueExpenditureList) expenditures.get(itr.next());
            Iterator listItr = list.iterator();

            while (listItr.hasNext()) {

                Expenditure current = (Expenditure) listItr.next();
                Set tags = current.getTags();

                Iterator set = tags.iterator();
                Tag next = (Tag) set.next();

                if (output.containsKey(next)) {
                    output.replace(next, output.get(next) + current.getAmount().value);
                } else {
                    output.put(next, current.getAmount().value);
                }

            }

        }

        return output;

    }

}
