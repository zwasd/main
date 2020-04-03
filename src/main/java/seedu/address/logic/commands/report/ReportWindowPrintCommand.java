package seedu.address.logic.commands.report;

import seedu.address.logic.commands.ReportCommand;
import seedu.address.logic.commands.ReportCommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Prints the report.
 */

public class ReportWindowPrintCommand extends ReportCommand {

    public static final String MESSAGE_PRINT = "Checking if there is a graph to print.";

    @Override
    public ReportCommandResult execute(Model model) throws CommandException {
        return new ReportCommandResult(MESSAGE_PRINT, false, false, true);
    }
}
