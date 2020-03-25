package seedu.address.logic.commands;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Represents result of command execution in Report Window.
 */
public abstract class ReportCommand {

    public abstract ReportCommandResult execute(Model model) throws CommandException;

}
