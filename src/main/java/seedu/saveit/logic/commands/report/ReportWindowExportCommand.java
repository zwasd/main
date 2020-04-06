package seedu.saveit.logic.commands.report;

import seedu.saveit.logic.commands.ReportCommand;
import seedu.saveit.logic.commands.ReportCommandResult;
import seedu.saveit.logic.commands.exceptions.CommandException;
import seedu.saveit.model.Model;

/**
 * Export command for report window.
 */
public class ReportWindowExportCommand extends ReportCommand {

    public static final String COMMAND_WORD = "export";
    public static final String MESSAGE_EXPORT = "Checking if there is graph to export.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " fileName" + "\n"
            + "eg export hello";

    private String fileName;

    public ReportWindowExportCommand(String fileName) {
        this.fileName = fileName;
    }


    @Override
    public ReportCommandResult execute(Model model) throws CommandException {
        return new ReportCommandResult(MESSAGE_EXPORT, fileName);
    }
}
