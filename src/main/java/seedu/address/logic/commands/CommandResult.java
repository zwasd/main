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
    private final boolean isShowReport;
    private final boolean isExportReport;
    private final boolean isPrintReport;
    private HashMap stats;
    private Report.GraphType graph = Report.GraphType.NULL;

    /**
     * The indicator of the current active date in the calendar view should change.
     */
    private final boolean updateCalendar;
    private LocalDate newActiveDate = null;

    /**
     *The indicator of the current active account name should change.
     */
    private boolean updateAccountName;
    private String activeAccountName;

    /**
     * The indicator of the current budget view should change.
     */
    private boolean updateBudgetView;
    private Double budget;
    private double totalSpending;
    /**
     * The application should exit.
     */
    private final boolean exit;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean isShowReport,
                         boolean isExportReport, boolean isPrintReport, boolean updateCalendar,
                         boolean updateAccountName, boolean updateBudgetView) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.isShowReport = isShowReport;
        this.isExportReport = isExportReport;
        this.isPrintReport = isPrintReport;
        this.updateCalendar = updateCalendar;
        this.updateAccountName = updateAccountName;
        this.updateBudgetView = updateBudgetView;
    }


    public CommandResult(String feedbackToUser, LocalDate newActiveDate, Double budget, double totalSpending) {
        this(feedbackToUser, false, false, false, false, false,
                true, false, true);
        this.newActiveDate = newActiveDate;
        this.budget = budget;
        this.totalSpending = totalSpending;

    }

    public CommandResult(String feedbackToUser, Report.GraphType graph, HashMap stats,
                         boolean exportReport, boolean showReport, boolean printReport) {
        this(feedbackToUser, false, false, showReport,
                exportReport, printReport, false, false, false);
        this.graph = graph;
        this.stats = stats;
    }

    //rename
    public CommandResult(String feedbackToUser, String newAccountName) {
        this(feedbackToUser, false, false, false, false, false,
                false, true, false);
        this.activeAccountName = newAccountName;
    }

    //checkout another account
    public CommandResult(String feedbackToUser, String newAccountName, Double budget, double totalSpending) {
        this(feedbackToUser, false, false, false, false, false,
                false, true, true);
        this.activeAccountName = newAccountName;
        this.budget = budget;
        this.totalSpending = totalSpending;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, false,
                false, false, false, false);
    }

    public CommandResult(String feedbackToUser, Double budget, double totalSpending) {
        this(feedbackToUser, false, false, false, false,
                false, false, false, true);
        this.budget = budget;
        this.totalSpending = totalSpending;
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
        return isShowReport;
    }

    public boolean isExportReport() {
        return isExportReport;
    }

    public boolean isPrintReport() {
        return isPrintReport;
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

    public boolean isUpdateBudgetView() {
        return this.updateBudgetView;
    }

    public Double getBudget() {
        return this.budget;
    }

    public double getTotalSpending() {
        return this.totalSpending;
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
                && isShowReport == otherCommandResult.isShowReport
                && isExportReport == isExportReport
                && isPrintReport == isPrintReport
                && updateCalendar == otherCommandResult.updateCalendar
                && updateAccountName == otherCommandResult.updateAccountName
                && updateBudgetView == otherCommandResult.updateBudgetView;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, isShowReport, isExportReport,
                isPrintReport, updateCalendar, updateAccountName);
    }

}
