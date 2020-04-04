package seedu.saveit.model;

/**
 * Contains the component of an auto-complete field.
 */
public class AutoComplete {

    /** The completed text */
    private String command;

    /** Additional options to the command */
    private String subCommand;

    /** Description of the command */
    private String description;

    public AutoComplete(String command, String subCommand, String description) {
        this.command = command;
        this.subCommand = subCommand;
        this.description = description;
    }

    public String getCommand() {
        return command;
    }

    public String getSubCommand() {
        return subCommand;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return command + " " + subCommand + "\n" + description;
    }
}
