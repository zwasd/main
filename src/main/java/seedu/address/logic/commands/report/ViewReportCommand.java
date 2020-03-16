package seedu.address.logic.commands.report;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Report;

/**
 * View report.
 */
public class ViewReportCommand extends Command {


    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_SUCCESS = "Report is generated";

    private final Report toView;

    public ViewReportCommand(Report toView) {
        this.toView = toView;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }
}
