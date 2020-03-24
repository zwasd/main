package seedu.address.logic.commands.report;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import seedu.address.model.Model;
import seedu.address.model.Report;
import seedu.address.model.ReportableAccount;
import seedu.address.model.expenditure.Expenditure;
import seedu.address.model.expenditure.UniqueExpenditureList;
import seedu.address.model.tag.Tag;

/**
 * Internal statistics generation command.
 */
public class GenerateStats {

    private Model model;
    private Report report;

    public GenerateStats(Report report, Model model) {
        this.report = report;
        this.model = model;
    }

    /**
     * Calculate expenditures under each tag.
     * @return HashMap mapping to tag to its spending.
     */
    public HashMap<Tag, Double> generateStatsByTags() {

        ReportableAccount acct = model.getReportableAccount();
        Map expenditures = acct.getExpFromToInclusive(report.getStartDate(), report.getEndDate());
        Set keySet = expenditures.keySet();
        HashMap<Tag, Double> output = new HashMap<>();

        Iterator itr = keySet.iterator();

        while (itr.hasNext()) {

            UniqueExpenditureList list = (UniqueExpenditureList) expenditures.get(itr.next());
            Iterator listItr = list.iterator();

            while (listItr.hasNext()) {

                Expenditure current = (Expenditure) listItr.next();
                Tag tag = current.getTag();

                if (output.containsKey(tag)) {
                    output.replace(tag, output.get(tag) + current.getAmount().value);
                } else {
                    output.put(tag, current.getAmount().value);
                }

            }

        }

        return output;

    }


}

