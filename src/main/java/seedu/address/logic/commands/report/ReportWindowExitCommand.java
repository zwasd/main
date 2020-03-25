package seedu.address.logic.commands.report;

import seedu.address.logic.commands.ReportCommand;
import seedu.address.logic.commands.ReportCommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Exit command for report window.
 */
public class ReportWindowExitCommand extends ReportCommand {

    public ReportWindowExitCommand() {

    }

    @Override
    public ReportCommandResult execute(Model model) throws CommandException {
        return new ReportCommandResult(true);
    }
}
