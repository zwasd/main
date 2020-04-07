package seedu.saveit.logic.commands;

import seedu.saveit.logic.commands.exceptions.CommandException;
import seedu.saveit.model.Model;

/**
 * Represents result of command execution in Report Window.
 */
public abstract class ReportCommand {

    public abstract ReportCommandResult execute(Model model) throws CommandException;

}
