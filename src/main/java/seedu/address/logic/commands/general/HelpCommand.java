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
            "help [COMMAND] - Shows help, or help for the given command.\n"
            + "acc OPTIONS - Account related commands (for more information on the OPTIONS, enter 'help acc')\n"
            + "exp OPTIONS - Expenditure related commands (for more information on the OPTIONS, enter 'help exp')\n"
            + "repeat OPTIONS - Expenditure related commands "
            + "(for more information on the OPTIONS, enter 'help repeat')\n"
            + "report OPTIONS - Report related commands (for more information on the OPTIONS, enter 'help report')\n"
            + "find OPTIONS - Searches for expenditures (for more information on the OPTIONS, enter 'help find')\n"
            + "go DATE - Show the expenditures for the particular date.\n"
            + "exit - Exits the application.";

    private String message;

    public HelpCommand() {
        this.message = SHOWING_HELP_MESSAGE;
    }

    public HelpCommand(String message) {
        this.message = message;
    }

    public HelpCommand(String message, boolean unknown) {
        this.message = !unknown ? message
                : (message.isEmpty() ? "" : "Unknown command \"" + message + "\"\n")
                        + SHOWING_HELP_MESSAGE;
    }

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(message);
    }
}
