package seedu.saveit.model.report;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;
import seedu.saveit.ui.Graph;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

public class ExportFile {


    public static final String FILENAME_CONSTRAINT = "File name " +
            "should not have whitespace.";

    private String fileName;
    private Graph graph;

    public ExportFile(String fileName, Graph graph) {

        this.fileName = fileName;
        this.graph = graph;
    }

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

    public static boolean isValidFileName(String fileName) {

        if(fileName.contains(" ")) {
            return false;
        }
        return true;
    }


}
