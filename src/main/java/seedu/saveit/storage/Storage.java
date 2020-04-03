package seedu.saveit.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.saveit.commons.exceptions.DataConversionException;
import seedu.saveit.model.ReadOnlyAccountList;
import seedu.saveit.model.ReadOnlyUserPrefs;
import seedu.saveit.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends SaveItStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyAccountList> readAddressBook() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyAccountList addressBook) throws IOException;

}
