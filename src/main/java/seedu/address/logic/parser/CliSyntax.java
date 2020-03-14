package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    public static final Prefix PREFIX_INFO = new Prefix("-i");
    public static final Prefix PREFIX_AMOUNT = new Prefix("-a");
    public static final Prefix PREFIX_DATE = new Prefix("-d");
    public static final Prefix PREFIX_TAG = new Prefix("-t");

    public static final Prefix PREFIX_INTERVAL = new Prefix("--interval");
    public static final Prefix PREFIX_UNTIL = new Prefix("-until");


}
