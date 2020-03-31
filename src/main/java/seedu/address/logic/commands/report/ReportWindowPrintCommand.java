package seedu.address.logic.commands.report;

import seedu.address.logic.commands.ReportCommand;
import seedu.address.logic.commands.ReportCommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class ReportWindowPrintCommand extends ReportCommand {

    public static final String MESSAGE_SUCCESS = "Printing report.";

    @Override
    public ReportCommandResult execute(Model model) throws CommandException {
        return new ReportCommandResult(MESSAGE_SUCCESS, false, false, true);
    }
}
