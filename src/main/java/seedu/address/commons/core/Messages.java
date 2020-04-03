package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_EMPTY_COMMAND = "No command entered!\nEnter \"help\" for more info";
    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command!\nEnter \"help\" for more info";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_EXPENDITURE_DISPLAYED_INDEX =
            "The expenditure index provided is invalid";
    public static final String MESSAGE_EXPENDITURES_LISTED_OVERVIEW = "%1$d expenditures listed!";
    public static final String MESSAGE_INVALID_ACCOUNT_NAME = "The account with the specified name "
            + "\"%1$s\" was not found.\nEnter \"acc list\" to view all accounts";
    public static final String MESSAGE_INVALID_DATE = "The start date provided should be before or equal to end date.";
    public static final String MESSAGE_INVALID_TYPE_AT_INDEX = "The entry at the index is a(n) %1$s, "
                                                                + "please use the correct command";
    public static final String MESSAGE_INVALID_REPEAT_DISPLAYED_INDEX =
            "The repeat index provided is invalid";

}
