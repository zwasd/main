package seedu.saveit.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.saveit.commons.core.Messages.MESSAGE_INVALID_EXPENDITURE_DISPLAYED_INDEX;
import static seedu.saveit.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import static seedu.saveit.logic.commands.CommandTestUtil.AMOUNT_DESC_BUS;
import static seedu.saveit.logic.commands.CommandTestUtil.DATE_DESC_BUS;
import static seedu.saveit.logic.commands.CommandTestUtil.INFO_DESC_BUS;

import static seedu.saveit.testutil.Assert.assertThrows;
import static seedu.saveit.testutil.TypicalExpenditures.AMY;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.saveit.logic.commands.CommandResult;
import seedu.saveit.logic.commands.account.AccListCommand;
import seedu.saveit.logic.commands.exceptions.CommandException;
import seedu.saveit.logic.commands.expenditure.ExpAddCommand;
import seedu.saveit.logic.parser.account.AccLevelParser;
import seedu.saveit.logic.parser.exceptions.ParseException;
import seedu.saveit.logic.parser.expenditure.ExpLevelParser;
import seedu.saveit.model.Model;
import seedu.saveit.model.ModelManager;
import seedu.saveit.model.ReadOnlyAccountList;
import seedu.saveit.model.UserPrefs;
import seedu.saveit.model.expenditure.Expenditure;
import seedu.saveit.storage.JsonAccountListStorage;
import seedu.saveit.storage.JsonUserPrefsStorage;
import seedu.saveit.storage.StorageManager;
import seedu.saveit.testutil.ExpenditureBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonAccountListStorage addressBookStorage =
                new JsonAccountListStorage(temporaryFolder.resolve("addressBook.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String expDeleteCommand = "exp delete 11";
        assertCommandException(expDeleteCommand, MESSAGE_INVALID_EXPENDITURE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = AccLevelParser.COMMAND_WORD + " " + AccListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, AccListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonAccountListIoExceptionThrowingStub
        JsonAccountListStorage addressBookStorage =
                new JsonAccountListIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionAddressBook.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command

        String addCommand = ExpLevelParser.COMMAND_WORD + " " + ExpAddCommand.COMMAND_WORD
                + INFO_DESC_BUS + AMOUNT_DESC_BUS + DATE_DESC_BUS;
        Expenditure expectedExpenditure = new ExpenditureBuilder(AMY).build();

        ModelManager expectedModel = new ModelManager();
        expectedModel.addExpenditure(expectedExpenditure);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredExpenditureList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredExpenditureList().remove(0));
    }

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage,
            Model expectedModel) throws CommandException, ParseException {
        CommandResult result = logic.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    /**
     * Executes the command, confirms that a ParseException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage) {
        Model expectedModel = new ModelManager(model.getAccountList(), new UserPrefs());
        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandSuccess(String, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage, Model expectedModel) {
        assertThrows(expectedException, expectedMessage, () -> logic.execute(inputCommand));
        assertEquals(expectedModel, model);
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonAccountListIoExceptionThrowingStub extends JsonAccountListStorage {
        private JsonAccountListIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveSaveIt(ReadOnlyAccountList addressBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
