package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAccountList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of Account data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private SaveItStorage saveItStorage;
    private UserPrefsStorage userPrefsStorage;


    public StorageManager(SaveItStorage saveItStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.saveItStorage = saveItStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ Account methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return saveItStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyAccountList> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(saveItStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyAccountList> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return saveItStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAccountList addressBook) throws IOException {
        saveAddressBook(addressBook, saveItStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyAccountList addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        saveItStorage.saveAddressBook(addressBook, filePath);
    }

}
