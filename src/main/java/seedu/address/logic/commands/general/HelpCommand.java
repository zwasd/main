package seedu.address.logic.commands.general;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE =
            "acc OPTIONS - Account related commands (for more information on the OPTIONS, enter 'help acc')\n"
            + "exp OPTIONS - Expenditure related commands (for more information on the OPTIONS, enter 'help exp')\n"
            + "report OPTION - Report related commands (for more information on the OPTIONS, enter 'help report')\n"
            + "go DATE - Show the expenditures for the particular date.\n"
            + "help [COMMAND] - Shows help, or help for the given command.\n"
            + "exit - Exits the application.";

    private String message;

    public HelpCommand() {
        this.message = SHOWING_HELP_MESSAGE;
    }

    public HelpCommand(String message) {
        this.message = message;
    }

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(message);
    }
}
