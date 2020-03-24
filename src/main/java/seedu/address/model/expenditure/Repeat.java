package seedu.address.model.expenditure;

import java.time.LocalDate;
import java.util.HashSet;

import seedu.address.model.tag.Tag;

/**
 * A Repeated expenditure.
 */
public class Repeat {

    private Date startDate;
    private Date endDate;
    private Info info;
    private Amount amount;
    private Tag tag;
    private Period period;
    /**
     * Represents the frequency
     * of repeat expenditure.
     */
    public enum Period {
        DAILY, WEEKLY, MONTHLY, ANNUALLY;
    }

    // displayDate is empty, size 0 means daily.
    // non empty means weekly or monthly -> cos i will at least add one day inside.
    private HashSet<LocalDate> relevantDate;

    public Repeat(Info info, Amount amount, Date startDate, Date endDate, String period) {
        this.info = info;
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;
        setPeriod(period);
        relevantDate = new HashSet<>();
        generateRelevantDate();
    }

    public Info getInfo() {
        return info;
    }

    public Amount getAmount() {
        return amount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Period getPeriod() {
        return period;
    }

    public Tag getTag() {
        return tag;
    }

    public void setInfo(Info newInfo) {
        this.info = newInfo;
    }

    public void setAmount(Amount newAmount) {
        this.amount = newAmount;
    }

    public void setEndDate(Date newEndDate) {
        this.endDate = newEndDate;
    }

    public void setPeriod(String duration) {
        if (duration.equalsIgnoreCase("daily")) {
            this.period = Period.DAILY;
        } else if (duration.equalsIgnoreCase("monthly")) {
            this.period = Period.MONTHLY;
        } else if (duration.equalsIgnoreCase("weekly")) {
            this.period = Period.WEEKLY;
        } else if (duration.equalsIgnoreCase("annually")) {
            this.period = Period.ANNUALLY;
        }
    }


    /**
     * Update the displayDate hashSet.
     */
    private void generateRelevantDate() {
        if (this.period == Period.WEEKLY) {
            generateWeeklyDate();
        } else if (this.period == Period.MONTHLY) {
            generateMonthlyDate();
        } else if (this.period == Period.ANNUALLY) {
            generateAnnuallyDate();
        } else {
            this.relevantDate.clear();
        }

    }

    /**
     * Update if it is a weekly repeat.
     */
    private void generateWeeklyDate() {
        this.relevantDate.clear();
        LocalDate pivotDate = this.startDate.localDate;
        while (true) {
            this.relevantDate.add(pivotDate);
            pivotDate.plusWeeks(1);
            if (pivotDate.isAfter(this.endDate.localDate)) {
                break;
            }
        }
    }

    /**
     * Update if it is a monthly repeat.
     */
    public void generateMonthlyDate() {
        this.relevantDate.clear();
        LocalDate pivotDate = this.startDate.localDate;
        int i = 0;
        while (true) {
            LocalDate toStore = pivotDate.plusMonths(i);
            if (toStore.isAfter(this.endDate.localDate)) {
                break;
            }
            this.relevantDate.add(pivotDate);
            i++;
        }
    }

    /**
     * Update if it is a annually repeat.
     */
    public void generateAnnuallyDate() {
        this.relevantDate.clear();
        LocalDate pivotDate = this.startDate.localDate;
        int i = 0;
        while (true) {
            LocalDate toStore = pivotDate.plusYears(i);
            if (toStore.isAfter(this.endDate.localDate)) {
                break;
            }
            this.relevantDate.add(toStore);
            i++;
        }
    }

    /**
     * To check if this repeat object suppose to appear on that date.
     * @param targetDate the date you want to check.
     * @return true denote suppose to appear, false to denote not suppose to appear.
     */
    public boolean isOn(LocalDate targetDate) {
        if (this.period == Period.DAILY) {
            return checkDaily(targetDate);
        } else {
            return checkWeeklyOrMonthlyOrAnnually(targetDate);
        }
    }

    /**
     * Check if a date lie within the range of start and end date.
      * @param targetDate the date you want to check.
     * @return true or false.
     */
    private boolean checkDaily(LocalDate targetDate) {
        boolean wrtStartDate = targetDate.isEqual(startDate.localDate)
                || targetDate.isAfter(startDate.localDate);
        boolean wrtEndDate = targetDate.isEqual((endDate.localDate))
                || targetDate.isBefore(endDate.localDate);
        return wrtStartDate && wrtEndDate;
    }

    /**
     * Check if the date exist in the displayDate.
     * @param targetDate the date you want to check.
     * @return true or false.
     */
    private boolean checkWeeklyOrMonthlyOrAnnually(LocalDate targetDate) {
        return this.relevantDate.contains(targetDate);
    }


    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getInfo())
                .append(" Amount: ")
                .append(getAmount())
                .append(" Start Date: ")
                .append(getStartDate())
                .append(" End Date: ")
                .append(getEndDate())
                .append(" Interval: ")
                .append(getPeriod().toString())
                .append(" Tags: ")
                .append(getTag());
        return builder.toString();
    }
}
