package seedu.saveit.logic.commands.general;

import seedu.saveit.logic.commands.Command;
import seedu.saveit.logic.commands.CommandResult;
import seedu.saveit.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE =
            "help [COMMAND] - Shows help, or help for the given command.\n"
            + "acc OPTIONS - Account related commands (enter 'help acc' for more info)\n"
            + "exp OPTIONS - Expenditure related commands (enter 'help exp' for more info)\n"
            + "repeat OPTIONS - Expenditure related commands "
            + "(for more information on the OPTIONS, enter 'help repeat' for more info)\n"
            + "report OPTIONS - Report related commands (enter 'help report' for more info)\n"
            + "find OPTIONS - Searches for expenditures (enter 'help find' for more info)\n"
            + "setbudget OPTIONS - Sets the budget for a given month (enter 'help setbudget' for more info)\n"
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
