package seedu.address.model.expenditure;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Tag;

/**
 * A Repeated expenditure.
 */
public class Repeat {

    public static final String PERIOD_MESSAGE_CONSTRAINTS = "Period should be only: '"
            + Period.DAILY.toString() + "', '"
            + Period.WEEKLY.toString() + "', '"
            + Period.MONTHLY.toString() + "' or '"
            + Period.ANNUALLY.toString() + "'";

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
        DAILY("daily"),
        WEEKLY("weekly"),
        MONTHLY("monthly"),
        ANNUALLY("annually");

        private final String keyword;

        Period(String keyword) {
            this.keyword = keyword;
        }

        /**
         * Check if the input string is an valid period.
         */
        public static boolean isValidPeriod(String period) {
            return period.equalsIgnoreCase(Period.DAILY.toString())
                || period.equalsIgnoreCase(Period.WEEKLY.toString())
                || period.equalsIgnoreCase(Period.MONTHLY.toString())
                || period.equalsIgnoreCase(Period.ANNUALLY.toString());
        }

        public String toString() {
            return keyword;
        }

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

    public Repeat(Info info, Amount amount, Date startDate, Date endDate, Tag tag, String period) {
        this.info = info;
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tag = tag;
        setPeriod(period);
        relevantDate = new HashSet<>();
        generateRelevantDate();
    }

    /**
     * generate a Period based on input string.
     * @param period the period in string.
     * @return a new period.
     */
    public static Period generatePeriod(String period) throws IllegalValueException {
        Period p;
        if (period.equalsIgnoreCase(Period.DAILY.toString())) {
            p = Period.DAILY;
        } else if (period.equalsIgnoreCase(Period.WEEKLY.toString())) {
            p = Period.WEEKLY;
        } else if (period.equalsIgnoreCase(Period.MONTHLY.toString())) {
            p = Period.MONTHLY;
        } else if (period.equalsIgnoreCase(Period.ANNUALLY.toString())) {
            p = Period.ANNUALLY;
        } else {
            throw new IllegalValueException(PERIOD_MESSAGE_CONSTRAINTS);
        }
        return p;
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

    public void setStartDate(Date newStartDate) {
        this.startDate = newStartDate;
    }

    public void setEndDate(Date newEndDate) {
        this.endDate = newEndDate;
    }

    public void setPeriod(String duration) {
        if (duration.equalsIgnoreCase(Period.DAILY.toString())) {
            this.period = Period.DAILY;
        } else if (duration.equalsIgnoreCase(Period.WEEKLY.toString())) {
            this.period = Period.WEEKLY;
        } else if (duration.equalsIgnoreCase(Period.MONTHLY.toString())) {
            this.period = Period.MONTHLY;
        } else if (duration.equalsIgnoreCase(Period.ANNUALLY.toString())) {
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


    /**
     * Do note that this method will only be call if period is not daily.
     * Get all the date from the relevantDate that falls in the given YearMonth.
     * @param givenYearMonth target YearMonth.
     * @return an arrayList of all the dates that follows within the YearMonth.
     */
    private ArrayList<LocalDate> isOnYearMonth(YearMonth givenYearMonth) {
        ArrayList <LocalDate> allDateWithinGivenYearMonth = new ArrayList<>();
        Iterator iterator = relevantDate.iterator();
        while (iterator.hasNext()) {
            LocalDate temp = (LocalDate) iterator.next();
            if (YearMonth.from(temp).equals(givenYearMonth)) {
                allDateWithinGivenYearMonth.add(temp);
            }
        }
        return allDateWithinGivenYearMonth;
    }

    /**
     * Calculate the total value for the given YearMonth and period is "DAILY".
     * @param givenYearMonth the target YearMonth
     * @return the total value of that period.
     */
    private double calculateDaily(YearMonth givenYearMonth) {
        if (YearMonth.from(startDate.localDate).isAfter(givenYearMonth)
                || YearMonth.from(endDate.localDate).isBefore(givenYearMonth)) {
            return 0;
        } else {
            double total;
            if (YearMonth.from(startDate.localDate).equals((givenYearMonth))) {
                // Start date is in the same month of the given year month.
                if (YearMonth.from(endDate.localDate).equals((givenYearMonth))) {
                    // End date is in the same month of the given year month.
                    int totalDays = this.startDate.localDate.until(this.endDate.localDate).getDays();
                    total = totalDays * this.amount.value;
                } else {
                    // End date is not in the same month of the given year month.
                    int totalNumberOfDaysInTheMonth = givenYearMonth.lengthOfMonth();
                    int totalNumberOfCountableDays = totalNumberOfDaysInTheMonth
                            - this.startDate.localDate.getDayOfMonth() + 1;
                    total = totalNumberOfCountableDays * this.amount.value;
                }
                return total;
            } else {
                // Start date is before the given YearMonth
                if (YearMonth.from(endDate.localDate).isAfter(givenYearMonth)) {
                    // End date is after the given YearMonth
                    total = givenYearMonth.lengthOfMonth() * this.amount.value;
                } else {
                    // End date is within the given YearMonth
                    int countableDays = this.endDate.localDate.getDayOfMonth();
                    total = countableDays * this.amount.value;
                }
                return total;
            }
        }
    }

    /**
     *
     */
    public double calculateForGivenYearMonth(YearMonth givenYearMonth) {
        if (this.period == Period.DAILY) {
            return calculateDaily(givenYearMonth);
        } else {
            int totalNumOfDays = isOnYearMonth(givenYearMonth).size();
            double total = totalNumOfDays * this.amount.value;
            return total;
        }
    }






    /**
     * Returns true if both repeats have all same fields.
     * Can be used for testing.
     */
    @Override
    public boolean equals(Object other) {

        if (!(other instanceof Repeat)) { // short circuit if not same type
            return false;
        } else if (other == this) {
            return true;
        }

        Repeat otherRepeat = (Repeat) other;
        boolean sameInfo = otherRepeat.info.equals(this.info);
        boolean sameAmt = otherRepeat.amount.equals(this.amount);
        boolean sameStartDate = otherRepeat.startDate.equals(this.startDate);
        boolean sameEndDate = otherRepeat.endDate.equals(this.endDate);
        boolean sameTag = otherRepeat.tag.equals(this.tag);
        boolean samePeriod = otherRepeat.period.equals(this.period);

        return sameAmt && sameStartDate && sameEndDate && sameInfo && sameTag && samePeriod;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(info, amount, startDate, endDate, tag, period);
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
