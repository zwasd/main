package seedu.address.ui;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ReportCommandResult;

/**
 * Generate respective graph.
 */
public abstract class Graph<T> {

    public abstract T getGraph(CommandResult result);

    public abstract T getGraph(ReportCommandResult result);

}
