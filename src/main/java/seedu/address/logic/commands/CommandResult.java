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
     * Report stats to user
     **/
    private final boolean showReport;
    private HashMap stats;
    private Report.GraphType graph = Report.GraphType.NULL;

    /**
     * The indicator of the current active date in the calendar view should change.
     */
    private final boolean updateCalendar;
    private LocalDate newActiveDate = null;

    /**
     * The application should exit.
     */
    private final boolean exit;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit,
                         boolean showReport, boolean updateCalendar) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.showReport = showReport;
        this.updateCalendar = updateCalendar;
    }

    public CommandResult(String feedbackToUser, LocalDate newActiveDate) {
        this(feedbackToUser, false, false, false, true);
        this.newActiveDate = newActiveDate;
    }

    public CommandResult(String feedbackToUser, Report.GraphType graph, HashMap stats) {
        this(feedbackToUser, false, false, true, false);
        this.graph = graph;
        this.stats = stats;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, false);
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

    public boolean isPieGraph() {
        return graph == Report.GraphType.PIE;
    }

    public boolean isBarGraph() {
        return graph == Report.GraphType.BAR;
    }

    public boolean isUpdateCalendar() {
        return updateCalendar;
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
                && updateCalendar == otherCommandResult.updateCalendar;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, showReport, updateCalendar);
    }

}
