package seedu.saveit.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.saveit.commons.exceptions.DataConversionException;
import seedu.saveit.model.AccountList;
import seedu.saveit.model.ReadOnlyAccountList;

/**
 * Represents a storage for {@link AccountList}.
 */
public interface SaveItStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getSaveItFilePath();

    /**
     * Returns Account data as a {@link ReadOnlyAccountList}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyAccountList> readSaveIt() throws DataConversionException, IOException;

    /**
     * @see #getSaveItFilePath()
     */
    Optional<ReadOnlyAccountList> readSaveIt(Path filePath) throws DataConversionException, IOException;

    /**
     * Reads from saveit-demo.json in resources/files/
     * @return a ReadOnlyAccountList with the sample data
     */
    ReadOnlyAccountList readSampleSaveIt();

    /**
     * Saves the given {@link ReadOnlyAccountList} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveSaveIt(ReadOnlyAccountList addressBook) throws IOException;

    /**
     * @see #saveSaveIt(ReadOnlyAccountList)
     */
    void saveSaveIt(ReadOnlyAccountList addressBook, Path filePath) throws IOException;

}
