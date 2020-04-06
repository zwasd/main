package seedu.saveit.logic.commands.report;

import seedu.saveit.model.Model;
import seedu.saveit.model.Report;
import seedu.saveit.model.ReportableAccount;
import seedu.saveit.model.expenditure.Expenditure;
import seedu.saveit.model.expenditure.Repeat;
import seedu.saveit.model.expenditure.Tag;
import seedu.saveit.model.expenditure.UniqueExpenditureList;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
     *
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

        Map repeats = acct.getRepeatFromToInclusive(report.getStartDate(), report.getEndDate());

        for (Repeat repeat : (Set<Repeat>) repeats.keySet()) {
           double amt = (double) repeats.get(repeat);
            Tag tag = repeat.getTag();

            if (output.containsKey(tag)) {
                output.replace(tag, output.get(tag) + amt);
            } else {
                output.put(tag, amt);
            }
        }
        return output;

    }


}

