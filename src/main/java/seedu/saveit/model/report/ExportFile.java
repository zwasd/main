package seedu.saveit.model.report;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;

import seedu.saveit.commons.util.StringUtil;
import seedu.saveit.ui.Graph;

/**
 * Class used for to represent
 * file that report graph is exporting to.
 */
public class ExportFile {


    public static final String FILENAME_CONSTRAINT = "File name "
            + "should not have whitespace and should be in lower case and alphanumeric.";

    private String fileName;
    private Graph graph;

    public ExportFile(String fileName, Graph graph) {
        this.fileName = fileName;
        this.graph = graph;
    }

    /**
     * Exports image into file.
     */
    public void export(WritableImage img) throws IOException {
        File f = new File("Report/" + fileName + ".png");
        f.getParentFile().mkdir();
        if (f.exists()) {
            throw new FileAlreadyExistsException(fileName);
        }
        ImageIO.write(SwingFXUtils.fromFXImage(img, null), "png", f);
        f.createNewFile();
    }

    public Graph getGraph() {
        return graph;
    }

    public String getFileName() {
        return fileName;
    }

    /**
     * Checks if {@code String fileName} is a valid file name
     *
     * @param fileName of file.
     * @return true if fileName if valid and false, otherwise.
     */
    public static boolean isValidFileName(String fileName) {

        if (fileName.contains(" ")
                || !(fileName.length() > 0)
                || !(fileName.toLowerCase().equals(fileName))
                || !(StringUtil.isAlphanumeric(fileName))) {
            return false;
        }

        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else {
            if (obj instanceof ExportFile) {
                ExportFile f = (ExportFile) obj;
                return graph.equals(f.graph)
                        && fileName.equals(f.fileName);
            }
        }
        return false;
    }
}
