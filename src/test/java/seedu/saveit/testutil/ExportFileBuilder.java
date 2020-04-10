package seedu.saveit.testutil;

import static seedu.saveit.logic.commands.CommandTestUtil.VALID_FILE_NAME;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_ORGANISATION_MONTH;

import java.util.HashMap;

import seedu.saveit.model.report.ExportFile;
import seedu.saveit.ui.Graph;
import seedu.saveit.ui.Pie;

/**
 * A utility class to help build ExportFile object
 */
public class ExportFileBuilder {

    public static final String DEFAULT_FILE_NAME = VALID_FILE_NAME;

    private String fileName;
    private Graph graphStub;


    public ExportFileBuilder() {

        this.fileName = DEFAULT_FILE_NAME;
        this.graphStub = new Pie(new HashMap(), VALID_ORGANISATION_MONTH);

    }

    /**
     * Sets the graph of ExportFile object that we are building.
     */
    public ExportFileBuilder withGraph(Graph graph) {
        this.graphStub = graph;
        return this;
    }

    /**
     * Sets the file name of ExportFile object that we are building.
     */
    public ExportFileBuilder withFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public ExportFile build() {
        return new ExportFile(fileName, graphStub);
    }
}
