package seedu.saveit.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import seedu.saveit.logic.commands.ReportCommandResult;
import seedu.saveit.logic.commands.exceptions.CommandException;
import seedu.saveit.logic.parser.exceptions.ParseException;
import seedu.saveit.ui.exceptions.PrinterException;

/**
 * Command box for report window.
 */
public class ReportCommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "ReportCommandBox.fxml";

    private final ReportCommandExecutor commandExecutor;

    @FXML
    private ReportAutoCompleteTextField commandTextField;

    public ReportCommandBox(ReportCommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
    }
    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
        try {
            commandExecutor.executeReportWindowCommand(commandTextField.getText());
            commandTextField.setText("");
        } catch (CommandException | ParseException | PrinterException e) {
            setStyleToIndicateCommandFailure();
        }
    }

    /**
     * Sets the command box style to use the default style.
     */
    private void setStyleToDefault() {
        commandTextField.getStyleClass().remove(ERROR_STYLE_CLASS);
    }

    /**
     * Sets the command box style to indicate a failed command.
     */
    private void setStyleToIndicateCommandFailure() {
        ObservableList<String> styleClass = commandTextField.getStyleClass();

        if (styleClass.contains(ERROR_STYLE_CLASS)) {
            return;
        }

        styleClass.add(ERROR_STYLE_CLASS);
    }

    /**
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface ReportCommandExecutor {
        /**
         * Executes the command and returns the result.
         *
         * @see seedu.saveit.logic.Logic#execute(String)
         */
        ReportCommandResult executeReportWindowCommand(String commandText)
                throws CommandException, ParseException, PrinterException;
    }


}
