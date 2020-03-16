package seedu.address.model;

import seedu.address.model.expenditure.Date;

/**
 * Report.
 */
public class Report {
    private final Date startDate;
    private final Date endDate;

    public Report(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }


}
