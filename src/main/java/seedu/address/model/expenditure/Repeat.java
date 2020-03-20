package seedu.address.model.expenditure;

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
    private String period;

    public Repeat(Info info, Amount amount, Date startDate, Date endDate, String period) {
        this.info = info;
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.period = period;
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
