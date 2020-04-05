package seedu.saveit.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.saveit.commons.core.LogsCenter;
import seedu.saveit.commons.exceptions.DataConversionException;
import seedu.saveit.model.ReadOnlyAccountList;
import seedu.saveit.model.ReadOnlyUserPrefs;
import seedu.saveit.model.UserPrefs;

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
    public Path getSaveItFilePath() {
        return saveItStorage.getSaveItFilePath();
    }

    @Override
    public Optional<ReadOnlyAccountList> readSaveIt() throws DataConversionException, IOException {
        return readSaveIt(saveItStorage.getSaveItFilePath());
    }

    @Override
    public Optional<ReadOnlyAccountList> readSaveIt(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return saveItStorage.readSaveIt(filePath);
    }

    @Override
    public void saveSaveIt(ReadOnlyAccountList addressBook) throws IOException {
        saveSaveIt(addressBook, saveItStorage.getSaveItFilePath());
    }

    @Override
    public void saveSaveIt(ReadOnlyAccountList addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        saveItStorage.saveSaveIt(addressBook, filePath);
    }

}
