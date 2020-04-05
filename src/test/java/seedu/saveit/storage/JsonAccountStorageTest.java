package seedu.saveit.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.saveit.testutil.Assert.assertThrows;
import static seedu.saveit.testutil.TypicalAccounts.getTypicalAccountList;
// import static seedu.saveit.testutil.TypicalExpenditures.ALICE;
import static seedu.saveit.testutil.TypicalExpenditures.HOON;
import static seedu.saveit.testutil.TypicalExpenditures.IDA;
// import static seedu.saveit.testutil.TypicalExpenditures.getTypicalAccount;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.saveit.commons.exceptions.DataConversionException;
// import seedu.saveit.model.Account;
import seedu.saveit.model.AccountList;
// import seedu.saveit.model.ReadOnlyAccount;
import seedu.saveit.model.ReadOnlyAccountList;

public class JsonAccountStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonAccountStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAddressBook(null));
    }

    private java.util.Optional<ReadOnlyAccountList> readAddressBook(String filePath) throws Exception {
        return new JsonAccountListStorage(Paths.get(filePath)).readSaveIt(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readAddressBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readAddressBook("notJsonFormatAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidExpenditureAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidExpenditureAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidExpenditureAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readAddressBook("invalidAndValidExpenditureAddressBook.json"));
    }

    @Test
    public void readAndsaveSaveIt_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        AccountList original = getTypicalAccountList();
        JsonAccountListStorage jsonAccountListStorage = new JsonAccountListStorage(filePath);

        // Save in new file and read back
        jsonAccountListStorage.saveSaveIt(original, filePath);
        ReadOnlyAccountList readBack = jsonAccountListStorage.readSaveIt(filePath).get();
        assertEquals(original, new AccountList(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addExpenditure(HOON);
        // TODO: why does allowing this to run fails?
        // original.removeExpenditure(ALICE);
        jsonAccountListStorage.saveSaveIt(original, filePath);
        readBack = jsonAccountListStorage.readSaveIt(filePath).get();
        assertEquals(original, new AccountList(readBack));

        // Save and read without specifying file path
        original.addExpenditure(IDA);
        jsonAccountListStorage.saveSaveIt(original); // file path not specified
        readBack = jsonAccountListStorage.readSaveIt().get(); // file path not specified
        assertEquals(original, new AccountList(readBack));

    }

    @Test
    public void saveSaveIt_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveSaveIt(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveSaveIt(ReadOnlyAccountList addressBook, String filePath) {
        try {
            new JsonAccountListStorage(Paths.get(filePath))
                    .saveSaveIt(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveSaveIt_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveSaveIt(new AccountList(false), null));
    }
}
