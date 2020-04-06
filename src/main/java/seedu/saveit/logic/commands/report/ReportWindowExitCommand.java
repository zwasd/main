package seedu.saveit.logic.commands.report;

import seedu.saveit.logic.commands.ReportCommand;
import seedu.saveit.logic.commands.ReportCommandResult;
import seedu.saveit.logic.commands.exceptions.CommandException;
import seedu.saveit.model.Model;

/**
 * Exit command for report window.
 */
public class ReportWindowExitCommand extends ReportCommand {

   public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT = "Exiting report window!";
    public ReportWindowExitCommand() {

    }

    @Override
    public ReportCommandResult execute(Model model) throws CommandException {
        return new ReportCommandResult(MESSAGE_EXIT, true, false, false);
    }
}
