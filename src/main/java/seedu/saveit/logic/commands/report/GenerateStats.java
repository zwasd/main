package seedu.saveit.logic.commands.report;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import seedu.saveit.model.Model;
import seedu.saveit.model.ReportableAccount;
import seedu.saveit.model.expenditure.Date;
import seedu.saveit.model.expenditure.Expenditure;
import seedu.saveit.model.expenditure.Repeat;
import seedu.saveit.model.expenditure.Tag;
import seedu.saveit.model.expenditure.UniqueExpenditureList;
import seedu.saveit.model.report.Report;


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

                if(current.getAmount().value == 0) {
                    continue;
                }

                Tag tag = current.getTag();

                if (output.containsKey(tag)) {
                    output.replace(tag, output.get(tag) + current.getAmount().value);
                } else {
                    output.put(tag, current.getAmount().value);
                }

            }

        }

        Map repeats = acct.getRepeatExpFromToInclusiveByRepeat(report.getStartDate(), report.getEndDate());

        for (Repeat repeat : (Set<Repeat>) repeats.keySet()) {
            double amt = (double) repeats.get(repeat);

            if (amt == 0) {
                continue;
            }

            Tag tag = repeat.getTag();

            if (output.containsKey(tag)) {
                output.replace(tag, output.get(tag) + amt);
            } else {
                output.put(tag, amt);
            }
        }
        return output;

    }

    public HashMap<String, Double> generateStatsByMonth() {

        HashMap<String, Double> output = new HashMap<>();


        ReportableAccount acct = model.getReportableAccount();
        Map<Date, UniqueExpenditureList> expenditures = acct.getExpFromToInclusive(report.getStartDate(), report.getEndDate());

        for(Date date : expenditures.keySet()) {

            UniqueExpenditureList list = expenditures.get(date);
            Iterator listItr = list.iterator();

            while (listItr.hasNext()) {

                Expenditure current = (Expenditure) listItr.next();

                if(current.getAmount().value == 0.0) {
                    continue;
                }

                String month = String.valueOf(YearMonth.from(date.localDate));

                if (output.containsKey(month)) {
                    output.replace(month, output.get(month) + current.getAmount().value);
                } else {
                    output.put(month, current.getAmount().value);
                }

            }

        }


        Map<String, Double> repeats = acct.getRepeatExpFromToInclusiveByMonth(report.getStartDate(), report.getEndDate());

        for(String yearMonth: repeats.keySet()) {

            double amount = repeats.get(yearMonth);

            if(amount == 0.0) {
                continue;
            }

            if (output.containsKey(yearMonth)) {
                output.replace(yearMonth , output.get(yearMonth) + repeats.get(yearMonth));
            } else {
                output.put(yearMonth , repeats.get(yearMonth));
            }
        }

        return output;
    }


}

