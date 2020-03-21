package seedu.address.model.expenditure;

import seedu.address.model.tag.Tag;

import java.time.LocalDate;
import java.util.HashSet;

/**
 * A Repeated expenditure.
 */
public class Repeat {

    private Date startDate;
    private Date endDate;
    private Info info;
    private Amount amount;
    private Tag tag;
    private String period;
    // displayDate is empty, size 0 means daily.
    // non empty means weekly or monthly -> cos i will at least add one day inside.
    private HashSet<LocalDate> displayDate;

    public Repeat(Info info, Amount amount, Date startDate, Date endDate, String period) {
        this.info = info;
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.period = period;
        displayDate = new HashSet<>();
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

    public String getPeriod() {
        return period;
    }

    public Tag getTag() {
        return tag;
    }

    public void setInfo(String newInfo) {
        this.info = info;
    }

    public void setAmount(Amount newAmount) {
        this.amount = newAmount;
    }

    public void setEndDate(Date newEndDate) {
        this.endDate = newEndDate;
    }

    public void setPeriod(String newPeriod) {
        this.period = newPeriod;
    }

    /**
     * Update the displayDate hashSet.
     */
    private void generateDisplayDate() {
        if(this.period.equalsIgnoreCase("weekly")) {
            generateWeeklyDate();
        } else if(this.period.equalsIgnoreCase("monthly")){
            generateMonthlyDate();
        } else {
            this.displayDate.clear();
        }

    }

    /**
     * Update if it is a weekly repeat.
     */
    private void generateWeeklyDate() {
        this.displayDate.clear();
        LocalDate pivotDate = this.startDate.localDate;
        while(true) {
            this.displayDate.add(pivotDate);
            pivotDate.plusWeeks(1);
            if(pivotDate.isAfter(this.endDate.localDate)) {
                break;
            }
        }
    }

    /**
     * Update if it is a monthly repeat.
     */
    public void generateMonthlyDate() {
        this.displayDate.clear();
        LocalDate pivotDate = this.startDate.localDate;
        while(true) {
            this.displayDate.add(pivotDate);
            pivotDate.plusMonths(1);
            if(pivotDate.isAfter(this.endDate.localDate)) {
                break;
            }
        }
    }

    /**
     * To check if this repeat object suppose to appear on that date.
     * @param targetDate the date you want to check.
     * @return true denote suppose to appear, false to denote not suppose to appear.
     */
    public boolean isOn(LocalDate targetDate) {
        if(this.period.equalsIgnoreCase("daily")) {
            return checkDaily(targetDate);
        } else {
            return checkWeeklyOrMonthly(targetDate);
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
    private boolean checkWeeklyOrMonthly(LocalDate targetDate) {
        return this.displayDate.contains(targetDate);
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
                .append(getPeriod())
                .append(" Tags: ")
                .append(getTag());
        return builder.toString();
    }
}
