package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Objects;

import seedu.address.model.Report;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /**
     * Help information should be shown to the user.
     */
    private final boolean showHelp;

    /**
     * Indicates the action of report command.
     **/
    private final boolean showReport;
    private final boolean exportReport;
    private final boolean printReport;
    private HashMap stats;
    private Report.GraphType graph = Report.GraphType.NULL;

    /**
     * The indicator of the current active date in the calendar view should change.
     */
    private final boolean updateCalendar;
    private LocalDate newActiveDate = null;

    /**
     *
     */
    private boolean updateAccountName;
    private String activeAccountName;

    /**
     * The application should exit.
     */
    private final boolean exit;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean showReport,
                         boolean exportReport, boolean printReport, boolean updateCalendar, boolean updateAccountName) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.showReport = showReport;
        this.exportReport = exportReport;
        this.printReport = printReport;
        this.updateCalendar = updateCalendar;
        this.updateAccountName = updateAccountName;
    }

    public CommandResult(String feedbackToUser, LocalDate newActiveDate) {
        this(feedbackToUser, false, false, false,
                false, false, true, false);
        this.newActiveDate = newActiveDate;
    }

    public CommandResult(String feedbackToUser, Report.GraphType graph, HashMap stats,
                         boolean exportReport, boolean showReport, boolean printReport) {
        this(feedbackToUser, false, false, showReport,
                exportReport, printReport, false, false);
        this.graph = graph;
        this.stats = stats;
    }

    public CommandResult(String feedbackToUser, String newAccountName) {
        this(feedbackToUser, false, false, false, false, false, false, true);
        this.activeAccountName = newAccountName;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, false,
                false, false, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isShowReport() {
        return showReport;
    }

    public boolean isExportReport() {
        return exportReport;
    }

    public boolean isPrintReport() {
        return printReport;
    }

    public boolean isPieGraph() {
        return graph == Report.GraphType.PIE;
    }

    public boolean isBarGraph() {
        return graph == Report.GraphType.BAR;
    }

    public boolean isUpdateCalendar() {
        return updateCalendar;
    }

    public boolean isUpdateAccountName() {
        return updateAccountName;
    }

    public String getActiveAccountName() {
        return this.activeAccountName;
    }

    public LocalDate getNewActiveDate() {
        return newActiveDate;
    }

    public HashMap getStats() {
        return stats;
    }

    public Report.GraphType getGraphType() {
        return graph;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit
                && showReport == otherCommandResult.showReport
                && exportReport == exportReport
                && printReport == printReport
                && updateCalendar == otherCommandResult.updateCalendar
                && updateAccountName == otherCommandResult.updateAccountName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, showReport, exportReport,
                printReport, updateCalendar, updateAccountName);
    }

}
