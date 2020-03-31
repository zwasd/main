package seedu.address.ui;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ReportCommandResult;

/**
 * Generate respective graph.
 */
public abstract class Graph<T> {

    public abstract void constructGraph(CommandResult result);

    public abstract void constructGraph(ReportCommandResult result);

    public abstract T getGraph();

}
