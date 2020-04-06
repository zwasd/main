package seedu.saveit.model.report;

import seedu.saveit.ui.Graph;

public class ExportFile {


    public static final String FILENAME_CONSTRAINT = "File name " +
            "should not have whitespace.";

    private String fileName;
    private Graph graph;


    public ExportFile(String fileName, Graph graph) {

        this.fileName = fileName;
        this.graph = graph;
    }

    public Graph getGraph() {
        return graph;
    }

    public String getFileName() {
        return fileName;
    }

    public static boolean isValidFileName(String fileName) {

        if(fileName.contains(" ")) {
            return false;
        }
        return true;
    }


}
