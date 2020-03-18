package seedu.address.model.expenditure;

/**
 * A Repeated expenditure.
 */
public class Repeat extends Expenditure {

    private Date startDate;
    private Date endDate;

    public Repeat(Info info, Amount amount, Date startDate, Date enddate) {
        super(info, amount, null, null);
        this.startDate = startDate;
        this.endDate = enddate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
