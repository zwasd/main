package seedu.saveit.model.expenditure;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

import javafx.scene.layout.Region;

import seedu.saveit.commons.exceptions.IllegalValueException;
import seedu.saveit.ui.RepeatCard;
import seedu.saveit.ui.UiPart;

/**
 * A Repeated expenditure.
 */
public class Repeat extends BaseExp {

    public static final String PERIOD_MESSAGE_CONSTRAINTS = "Period should be only: '"
            + Period.DAILY.toString() + "', '"
            + Period.WEEKLY.toString() + "', '"
            + Period.MONTHLY.toString() + "' or '"
            + Period.ANNUALLY.toString() + "'";

    private Date startDate;
    private Date endDate;
    // displayDate is empty, size 0 means daily.
    // non empty means weekly or monthly -> cos i will at least add one day inside.
    private HashSet<LocalDate> relevantDate;
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

    @Override
    public UiPart<Region> getUiCard(int displayedNumber) {
        return new RepeatCard(this, displayedNumber);
    }

    /**
     * generate a Period based on input string.
     *
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


    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Period getPeriod() {
        return period;
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
        int i = 0;
        while (true) {
            LocalDate toStore = pivotDate.plusWeeks(i);
            if (toStore.isAfter(this.endDate.localDate)) {
                break;
            }
            this.relevantDate.add(toStore);
            i++;
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
            //System.out.println(toStore);
            if (toStore.isAfter(this.endDate.localDate)) {
                break;
            }
            this.relevantDate.add(toStore);
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
     *
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
     *
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
     *
     * @param targetDate the date you want to check.
     * @return true or false.
     */
    private boolean checkWeeklyOrMonthlyOrAnnually(LocalDate targetDate) {
        return this.relevantDate.contains(targetDate);
    }


    /**
     * Do note that this method will only be call if period is not daily.
     * Get all the date from the relevantDate that falls in the given YearMonth.
     *
     * @param givenYearMonth target YearMonth.
     * @return an arrayList of all the dates that follows within the YearMonth.
     */
    private ArrayList<LocalDate> isOnYearMonth(YearMonth givenYearMonth) {
        ArrayList<LocalDate> allDateWithinGivenYearMonth = new ArrayList<>();
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
     *
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
                    int totalDays = this.startDate.localDate.until(this.endDate.localDate).getDays() + 1;
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
     * Calculate daily repeat for
     * given date range.
     *
     * @param startDate containing the first day.
     * @param endDate   containing the last day.
     * @return
     */
    public double calculateDaily(Date startDate, Date endDate) {

        LocalDate adjustedStart = null;
        LocalDate adjustedEnd = null;

        if (startDate.localDate.isAfter(this.getEndDate().localDate)
                || endDate.localDate.isBefore(this.getStartDate().localDate)) {
            return 0;
        }

        if (Date.isEqualOrBefore(this.getEndDate(), endDate)) {

            adjustedStart = startDate.localDate;

            if (Date.isEqualOrAfter(this.getStartDate(), startDate)) {
                adjustedStart = this.getStartDate().localDate;
            }
            adjustedEnd = this.getEndDate().localDate;

        } else {
            adjustedStart = this.getStartDate().localDate;

            if (Date.isEqualOrBefore(this.getStartDate(), startDate)) {
                adjustedStart = startDate.localDate;
            }
            adjustedEnd = endDate.localDate;
        }

        //System.out.println("ADJ " + adjustedStart);
        //System.out.println("ADJ " + adjustedEnd);

        int days = (adjustedStart).until(adjustedEnd).getDays() + 1;

        //System.out.println(days);

        double amount = this.getAmount().value * days;

        //System.out.println("Amt " + amount);
        return amount;
    }

    /**
     * Calculate daily repeat from
     * startDate to endDate and
     * group in months.
     */
    public HashMap<String, Double> calculateDailyRepeatMonth(Date startDate, Date endDate) {


        Date adjustedStart = null;
        Date adjustedEnd = null;

        if (startDate.localDate.isAfter(this.getEndDate().localDate)
                || endDate.localDate.isBefore(this.getStartDate().localDate)) {
            return new HashMap();
        }

        if (Date.isEqualOrBefore(this.getEndDate(), endDate)) {

            adjustedStart = startDate;

            if (Date.isEqualOrAfter(this.getStartDate(), startDate)) {
                adjustedStart = this.getStartDate();
            }
            adjustedEnd = this.getEndDate();

        } else {
            adjustedStart = this.getStartDate();

            if (Date.isEqualOrBefore(this.getStartDate(), startDate)) {
                adjustedStart = startDate;
            }
            adjustedEnd = endDate;
        }


        HashMap<String, Double> output = new HashMap<>();


        // get eom for start date
        LocalDate endOfMonthOfStartLocalDate = YearMonth.from(adjustedStart.localDate).atEndOfMonth();
        Date endOfMonthOfStartDate = new Date(endOfMonthOfStartLocalDate.format(DateTimeFormatter.ISO_DATE));

        //first month expenditure
        output.put(String.valueOf(YearMonth.from(endOfMonthOfStartLocalDate)),
                calculateDaily(adjustedStart, endOfMonthOfStartDate));

        //middle months expenditure
        YearMonth currentMonth = YearMonth.from(adjustedStart.localDate.plusMonths(1));
        YearMonth end = YearMonth.from(adjustedEnd.localDate.minusMonths(1));

        while (!currentMonth.isAfter(end)) {
            output.put(String.valueOf(currentMonth), calculateDaily(currentMonth));
            currentMonth = currentMonth.plusMonths(1);
        }

        //last month expenditure
        LocalDate startOfMonthOfEndLocalDate = YearMonth.from(adjustedStart.localDate).atDay(1);
        Date startOfMonthOfEndDate = new Date(startOfMonthOfEndLocalDate.format(DateTimeFormatter.ISO_DATE));

        output.put(String.valueOf(YearMonth.from(startOfMonthOfEndLocalDate)),
                calculateDaily(startOfMonthOfEndDate, adjustedEnd));
        return output;
    }

    /**
     * Calculate repeat (weekly, monthly, annually)
     * total value for
     * given months.
     * @param givenYearMonth
     * @return total spending for that repeat.
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
     * Calculate repeat (weekly, monthly, annually)
     * total value for
     * given range of months.
     *
     * @param startYearMonth first month.
     * @param endYearMonth   last month.
     * @return accumulated amount in these months.
     */
    public double calculateForGivenYearMonthRange(YearMonth startYearMonth, YearMonth endYearMonth) {
        double total = 0;
        YearMonth current = startYearMonth;

        while (!current.isAfter(endYearMonth)) {
            total = total + calculateForGivenYearMonth(current);
            current = current.plusMonths(1);
            //System.out.println("total " + total);
        }

        return total;
    }

    /**
     * Calculates the total value from given start
     * to end date.
     *
     * @param startDate the starting date.
     * @param endDate   ending date.
     * @return total value.
     */
    public double calculateWkOrMthOrYr(Date startDate, Date endDate) {

        LocalDate adjustedStart = null;
        LocalDate adjustedEnd = null;

        if (startDate.localDate.isAfter(this.getEndDate().localDate)
                || endDate.localDate.isBefore(this.getStartDate().localDate)) {
            return 0;
        }

        if (Date.isEqualOrBefore(this.getEndDate(), endDate)) {

            adjustedStart = startDate.localDate;

            if (Date.isEqualOrAfter(this.getStartDate(), startDate)) {
                adjustedStart = this.getStartDate().localDate;
            }
            adjustedEnd = this.getEndDate().localDate;

        } else {
            adjustedStart = this.getStartDate().localDate;

            if (Date.isEqualOrBefore(this.getStartDate(), startDate)) {
                adjustedStart = startDate.localDate;
            }
            adjustedEnd = endDate.localDate;
        }

        assert adjustedEnd != null && adjustedStart != null;

        double amount = 0;

        //System.out.println("ADJ " + adjustedStart);
        //System.out.println("ADJ " + adjustedEnd);

        if (adjustedStart.getDayOfMonth() != 1) {

            LocalDate endOfMonth = YearMonth.from(adjustedStart).atEndOfMonth();

            amount = amount + calculateRepeatTillEndOfMonth(adjustedStart,
                    endOfMonth);

            //System.out.println( "Amt " + amount);
            adjustedStart = YearMonth.from(adjustedStart.plusMonths(1)).atDay(1);
        }

        if (adjustedEnd.plusDays(1).getMonth() == adjustedEnd.getMonth()) {
            LocalDate startOfMonth = YearMonth.from(adjustedEnd).atDay(1);
            amount = amount + calculateRepeatTillEndOfMonth(startOfMonth, adjustedEnd);
            adjustedEnd = startOfMonth.minusDays(1);
            //System.out.println("Amt " + amount);
        }

        //System.out.println("ADJ " + adjustedStart);
        //System.out.print("ADJ " + adjustedEnd);
        amount = amount + calculateForGivenYearMonthRange(YearMonth.from(adjustedStart), YearMonth.from(adjustedEnd));

        return amount;

    }

    /**
     * Calculates the total value for given start
     * and end date and group in months.
     *
     * @param startDate the starting date.
     * @param endDate   ending date.
     */
    public HashMap <String, Double> calculateWkOrMthOrYrMonth(Date startDate, Date endDate) {
        HashMap<String, Double> output = new HashMap<>();


        // get eom for start date
        LocalDate endOfMonthOfStartLocalDate = YearMonth.from(startDate.localDate).atEndOfMonth();
        Date endOfMonthOfStartDate = new Date(endOfMonthOfStartLocalDate.format(DateTimeFormatter.ISO_DATE));

        //first month expenditure
        output.put(String.valueOf(YearMonth.from(endOfMonthOfStartLocalDate)),
                calculateRepeatTillEndOfMonth(startDate.localDate, endOfMonthOfStartDate.localDate));

        //middle months expenditure
        YearMonth currentMonth = YearMonth.from(startDate.localDate.plusMonths(1));
        YearMonth end = YearMonth.from(endDate.localDate.minusMonths(1));

        while (!currentMonth.isAfter(end)) {

            output.put(String.valueOf(currentMonth), calculateForGivenYearMonth(currentMonth));
            currentMonth = currentMonth.plusMonths(1);
        }

        //last month expenditure
        LocalDate startOfMonthOfEndLocalDate = YearMonth.from(endDate.localDate).atDay(1);
        Date startOfMonthOfEndDate = new Date(startOfMonthOfEndLocalDate.format(DateTimeFormatter.ISO_DATE));

        output.put(String.valueOf(YearMonth.from(startOfMonthOfEndLocalDate)),
                calculateRepeatTillEndOfMonth(startOfMonthOfEndDate.localDate, endDate.localDate));
        return output;
    }

    /**
     * Calculates repeat(weekly, monthly, annually)
     * from startDate to endDate in the same month.
     */
    public double calculateRepeatTillEndOfMonth(LocalDate startLocalDate, LocalDate endLocalDate) {

        if (startLocalDate.getMonth() != endLocalDate.getMonth()) {
            throw new RuntimeException("This method is for calculation within same month");
        }

        LocalDate adjustedStart = null;
        LocalDate adjustedEnd = null;

        if (startLocalDate.isAfter(this.getEndDate().localDate)
                || endLocalDate.isBefore(this.getStartDate().localDate)) {
            return 0;
        }

        if (endDate.localDate.isBefore(startLocalDate)
                || endDate.localDate.isEqual(startLocalDate)) {

            adjustedStart = startLocalDate;


            if (startDate.localDate.isAfter(startLocalDate)
                    || startDate.localDate.isEqual(startLocalDate)) {
                adjustedStart = startDate.localDate;
            }
            adjustedEnd = endDate.localDate;

        } else {
            adjustedStart = this.getStartDate().localDate;

            if (startDate.localDate.isBefore(startLocalDate)
                    || startDate.localDate.isEqual(startLocalDate)) {
                adjustedStart = startLocalDate;
            }
            adjustedEnd = endLocalDate;
        }

        System.out.println(this);
        System.out.println(adjustedStart);
        System.out.println(adjustedEnd);

        double amount = 0;

        if (this.getPeriod() == Period.WEEKLY) {
            LocalDate current = null;
            if (relevantDate.contains(adjustedStart)) {
                current = adjustedStart;
            } else {


                //check if repeat date is before or after start for start's week
                if (adjustedStart.getDayOfWeek().getValue()
                        - startDate.localDate.getDayOfWeek().getValue() > 0) {

                    current = adjustedStart.plusWeeks(1).minusDays(adjustedStart.getDayOfWeek().getValue()
                            - startDate.localDate.getDayOfWeek().getValue());

                } else if (adjustedStart.getDayOfWeek().getValue()
                        - startDate.localDate.getDayOfWeek().getValue() < 0) {

                    current = adjustedStart.plusDays(startDate.localDate.getDayOfWeek().getValue()
                            - adjustedStart.getDayOfWeek().getValue());
                }
            }
            assert current != null;
            while (!current.isAfter(adjustedEnd)) {
                System.out.println(current);
                amount = amount + this.getAmount().value;
                current = current.plusWeeks(1);
            }

            System.out.println("week " + amount);

            return amount;

        } else if (this.getPeriod() == Period.MONTHLY) {

            //check if the repeat is on last day of mth
            LocalDate nextDateRepeat = this.getStartDate().localDate.plusDays(1);

            //repeat falls on last day of every month
            if (nextDateRepeat.getMonth() != this.getStartDate().localDate.getMonth()) {

                //check if endDate is also last day of every month
                LocalDate nextDateEnd = adjustedEnd.plusDays(1);

                if (nextDateEnd.getMonth() != adjustedStart.getMonth()) {
                    amount = this.getAmount().value;
                    //System.out.println("month " + amount);
                    return amount;
                }
            } else {

                int dayOfMonthRepeat = this.getStartDate().localDate.getDayOfMonth();
                int adjustedStartDayOfMonth = adjustedStart.getDayOfMonth();
                int adjustedEndDayOfMonth = adjustedEnd.getDayOfMonth();

                //Check if the repeat day is in between the start and end.
                if (adjustedStartDayOfMonth <= dayOfMonthRepeat && dayOfMonthRepeat <= adjustedEndDayOfMonth) {
                    amount = this.getAmount().value;
                    //System.out.println("month " + amount);
                    return amount;
                }
            }
        } else if (this.getPeriod() == Period.ANNUALLY) {

            if (adjustedStart.getMonth()
                    == this.getStartDate().localDate.getMonth()) {

                if (adjustedStart.getDayOfMonth()
                        == this.getStartDate().localDate.getDayOfMonth()) {
                    amount = this.getAmount().value;
                    System.out.println("annual " + amount);
                    return amount;
                }

            }
        }

        assert amount == 0;
        return amount;
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
