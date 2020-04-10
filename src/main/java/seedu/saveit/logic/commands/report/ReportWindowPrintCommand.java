package seedu.saveit.logic.commands.report;

import seedu.saveit.logic.commands.ReportCommand;
import seedu.saveit.logic.commands.ReportCommandResult;
import seedu.saveit.logic.commands.exceptions.CommandException;
import seedu.saveit.model.Model;

/**
 * Prints the report.
 */

public class ReportWindowPrintCommand extends ReportCommand {

    public static final String COMMAND_WORD = "print";
    public static final String MESSAGE_PRINT = "Checking if there is a graph to print.";

    @Override
    public ReportCommandResult execute(Model model) throws CommandException {
        return new ReportCommandResult(MESSAGE_PRINT, false, false, true, false, false);
    }
}
