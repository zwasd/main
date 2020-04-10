package seedu.saveit.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_FILE_NAME;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_FILE_NAME_ALT;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_ORGANISATION_TAG;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import seedu.saveit.testutil.ExportFileBuilder;
import seedu.saveit.ui.Bar;
import seedu.saveit.ui.Pie;

public class ExportFileTest {


    @Test
    public void equals() {

        ExportFileBuilder file1 = new ExportFileBuilder()
                .withFileName(VALID_FILE_NAME)
                .withGraph(new Pie(new HashMap<>(), VALID_ORGANISATION_TAG));

        ExportFileBuilder file2 = new ExportFileBuilder()
                .withFileName(VALID_FILE_NAME)
                .withGraph(new Pie(new HashMap<>(), VALID_ORGANISATION_TAG));

        assertTrue(file1.build().equals(file2.build()));

        //different file name
        file1.withFileName(VALID_FILE_NAME_ALT);
        assertFalse(file1.build().equals(file2.build()));

        file1.withFileName(VALID_FILE_NAME);

        //different graph
        file1.withGraph(new Bar(new HashMap(), VALID_ORGANISATION_TAG));
        assertFalse(file1.build().equals(file2.build()));

        //different filename and graph
        file1.withFileName(VALID_FILE_NAME_ALT);
        assertFalse(file1.build().equals(file2.build()));


    }


}
