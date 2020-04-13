package seedu.saveit.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Logger;

import seedu.saveit.commons.core.LogsCenter;
import seedu.saveit.commons.exceptions.DataConversionException;
import seedu.saveit.commons.exceptions.IllegalValueException;
import seedu.saveit.commons.util.FileUtil;
import seedu.saveit.commons.util.JsonUtil;
import seedu.saveit.model.AccountList;
import seedu.saveit.model.ReadOnlyAccountList;

/**
 * A class to access Account data stored as a json file on the hard disk.
 */
public class JsonAccountListStorage implements SaveItStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonAccountListStorage.class);

    private Path filePath;

    public JsonAccountListStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getSaveItFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyAccountList> readSaveIt() throws DataConversionException {
        return readSaveIt(filePath);
    }

    /**
     * Similar to {@link #readSaveIt()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyAccountList> readSaveIt(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableAccountList> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableAccountList.class);
        if (!jsonAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public ReadOnlyAccountList readSampleSaveIt() {
        String jsonString = (new Scanner(getClass().getResourceAsStream("/files/saveit-demo.json")))
                .useDelimiter("\\A").next();
        try {
            return JsonUtil.fromJsonString(jsonString, JsonSerializableAccountList.class).toModelType();
        } catch (IllegalValueException | IOException ive) {
            logger.info("Illegal values found in saveit-demo.json");
            return new AccountList(true);
        }
    }

    @Override
    public void saveSaveIt(ReadOnlyAccountList addressBook) throws IOException {
        saveSaveIt(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveSaveIt(ReadOnlyAccountList)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveSaveIt(ReadOnlyAccountList addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableAccountList(addressBook), filePath);
    }

}
