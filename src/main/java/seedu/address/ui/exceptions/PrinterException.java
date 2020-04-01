package seedu.address.ui.exceptions;

/**
 * Exception thrown when printer cannot complete job.
 */
public class PrinterException extends Exception {

    public PrinterException(String message) {
        super(message);
    }

}
