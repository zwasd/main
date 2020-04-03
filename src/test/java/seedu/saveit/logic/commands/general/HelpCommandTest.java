package seedu.saveit.logic.commands.general;

import static seedu.saveit.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.saveit.logic.commands.general.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.saveit.logic.commands.CommandResult;
import seedu.saveit.model.Model;
import seedu.saveit.model.ModelManager;


public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, false, false,
                false, false, false, false,
                false, false);
        assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }
}
