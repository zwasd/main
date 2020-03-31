package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import javax.swing.plaf.synth.Region;

public class BudgetWindowView extends UiPart<Node> {

    private static final String FXML = "BudgetWindowView.fxml";

    private boolean budgetExist;

    @FXML
    private VBox budgetWindow;

    private Image piggyBank = new Image(this.getClass().getResourceAsStream("/images/piggyBank.png"));

    public BudgetWindowView() {
        super(FXML);
        setPiggyBank();
    }

    private void setBudgetExist(Boolean budgetExist) {
        this.budgetExist = budgetExist;
    }



    //If budgetExist is false, then it will just display a piggyBank.
    private void setPiggyBank() {
        budgetWindow.getChildren().add(new ImageView(piggyBank));
        budgetWindow.setAlignment(Pos.CENTER);

    }

}
