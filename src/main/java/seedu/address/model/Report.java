package seedu.address.model;

import java.time.LocalDate;

/**
 * Report.
 */
public class Report {
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Report(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }


}
