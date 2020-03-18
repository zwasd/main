package seedu.address.logic.commands.report;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Report;

/**
 * Export report.
 */
public class ExportReportCommand extends Command {

    public static final String COMMAND_WORD = "export";
    public static final String MESSAGE_SUCCESS = "Report exported to : %1$s";
    public static final String MESSAGE_FAIL = "Report cannot be exported";

    private final Report toExport;

    public ExportReportCommand(Report toExport) {
        this.toExport = toExport;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }
}
