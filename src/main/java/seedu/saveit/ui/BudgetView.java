package seedu.saveit.ui;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.TextAlignment;


/**
 * budget window view.
 */
public class BudgetView extends UiPart<Region> {

    private static final String FXML = "BudgetView.fxml";

    private boolean budgetExist;

    private double budgetAmount;

    private double totalSpending;

    private boolean isSaving;

    @FXML
    private VBox budgetWindow;

    private Image piggyBank = new Image(this.getClass().getResourceAsStream("/images/piggyBank.png"));

    private Image brokePiggy = new Image(this.getClass().getResourceAsStream("/images/brokePiggy.png"),
            150, 150, true, true);

    private Image feedingPiggy = new Image(this.getClass().getResourceAsStream("/images/feedingPiggy.png"),
            150, 150, true, true);

    public BudgetView() {
        super(FXML);
        if (budgetExist) {
            display();
        } else {
            setPiggyBank();
        }
    }

    /**
     * Update the budget view.
     */
    public void updateView() {
        budgetWindow.getChildren().clear();
        if (budgetExist) {
            display();
        } else {
            setPiggyBank();
        }
    }

    public void setBudgetExist(Boolean budgetExist) {
        this.budgetExist = budgetExist;
    }

    public void setBudgetAmount(double budgetAmount) {
        this.budgetAmount = budgetAmount;
    }

    public void setTotalSpending(double totalSpending) {
        this.totalSpending = totalSpending;
    }

    //If budgetExist is false, then it will just display a piggyBank.
    private void setPiggyBank() {
        ImageView pig = new ImageView(piggyBank);
        Label label = new Label("Please set the budget for this month\n");
        label.setTextFill(Paint.valueOf("000000"));
        label.setTextAlignment(TextAlignment.LEFT);
        budgetWindow.getChildren().add(label);
        budgetWindow.getChildren().add(pig);
        budgetWindow.setSpacing(20);
        budgetWindow.setAlignment(Pos.CENTER);

    }

    //If budgetExist is true

    /**
     * Display the relevant display if the budget is set.
     */
    private void display() {
        double balance = budgetAmount - totalSpending;
        if (balance <= 0) {
            this.isSaving = false;
        } else {
            this.isSaving = true;
        }
        Label budget = new Label();
        budget.setText("Budget: " + budgetAmount);
        budget.setTextAlignment(TextAlignment.LEFT);
        budget.setTextFill(Paint.valueOf("000000"));
        budget.setWrapText(true);
        Label spending = new Label();
        String spendingString = String.format("%.2f", totalSpending);
        spending.setText("Total spending: " + spendingString);
        spending.setTextAlignment(TextAlignment.LEFT);
        spending.setTextFill(Paint.valueOf("000000"));
        spending.setWrapText(true);
        Label leftOver = new Label();

        String balanceString = String.format("%.2f", balance);
        leftOver.setText("Balance: " + balanceString);
        leftOver.setTextAlignment(TextAlignment.LEFT);
        leftOver.setTextFill(Paint.valueOf("000000"));
        leftOver.setWrapText(true);
        budgetWindow.getChildren().add(budget);
        budgetWindow.getChildren().add(spending);
        budgetWindow.getChildren().add(leftOver);

        if (isSaving) {
            ImageView pig = new ImageView(feedingPiggy);
            budgetWindow.getChildren().add(pig);
        } else {
            ImageView pig = new ImageView(brokePiggy);
            budgetWindow.getChildren().add(pig);
        }
        budgetWindow.setSpacing(12);
        budgetWindow.setAlignment(Pos.CENTER);
    }


}
