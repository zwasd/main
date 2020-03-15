package seedu.address.ui;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


import java.time.LocalDate;
import java.time.YearMonth;

public class CalendarView extends UiPart<Region> {

    private static final String FXML = "CalendarView.fxml";
    private static final String[] MONTHS = {"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};
    private static final int[] DAYS_IN_MONTH = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    private int[] simulateGridPane = new int [42];
    private int day;
    private int month;
    private int year;
    private YearMonth yearMonth;
    private LocalDate pivotDate;
    private LocalDate firstDayOfTheMonth;
    private int prevMonthBalance;
    private int nextMonthBalance;
    private int thisMonthBalance;

    private Image leftArrow = new Image(this.getClass().getResourceAsStream("/images/leftButton.png"),
            20,15,true,true);
    private Image rightArrow = new Image(this.getClass().getResourceAsStream("/images/rightButton.png"),
            20,15,true,true);

    @FXML
    private Label monthYearLabel;

    @FXML
    private GridPane dateGridPane;

    @FXML
    private GridPane monthYearGridPane;

    @FXML
    private GridPane weekDayGridPane;

    @FXML
    private Button leftButton;

    @FXML
    private Button rightButton;

    public CalendarView() {
        super(FXML);
        setUpButton();
        LocalDate now = LocalDate.now();
        this.pivotDate = now;
        this.day = now.getDayOfMonth();
        this.month = now.getMonthValue();
        this.year = now.getYear();
        this.yearMonth = YearMonth.of(this.year, this.month);
        this.firstDayOfTheMonth = yearMonth.atDay(1);
        setMonthYearLabel();
        generateCalender();
    }

    public CalendarView(LocalDate date) {
        super(FXML);
        setUpButton();
        this.pivotDate = date;
        this.day = date.getDayOfMonth();
        this.month = date.getMonthValue();
        this.year = date.getYear();
        this.yearMonth = YearMonth.of(this.year, this.month);
        this.firstDayOfTheMonth = yearMonth.atDay(1);
        setMonthYearLabel();
        generateCalender();
    }

    private void setUpButton() {
        ImageView leftButtonView = new ImageView(this.leftArrow);
        ImageView rightButtonView = new ImageView(this.rightArrow);
        leftButton.setGraphic(leftButtonView);
        rightButton.setGraphic(rightButtonView);
    }


    public boolean checkLeapYear(int year) {
        if(year % 4 == 0) {
            return true;
        }
        return false;
    }

    public int findNumberOfDays(int month, int year) {
        if(month == 2) {
            if(checkLeapYear(year)) {
                return 29;
            } else {
                return DAYS_IN_MONTH[month - 1];
            }
        } else {
            return DAYS_IN_MONTH[month - 1];
        }
    }

    public int findNumberOfDaysInPreviousMonth(int month, int year) {
        if(month >= 2){
            return findNumberOfDays(month - 1, year);
        } else {
            return DAYS_IN_MONTH[11];
        }
    }

    public void setMonthYearLabel() {
        monthYearGridPane.setBackground(new Background(
                new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        StringBuilder monthYear = new StringBuilder();
        monthYear.append(MONTHS[this.month - 1]);
        monthYear.append("    ");
        monthYear.append(this.year);
        String output = monthYear.toString();
        this.monthYearLabel.setMaxSize(200,60);
        this.monthYearLabel.setText(output);
        this.monthYearLabel.setFont(Font.font("Cambria", 42));

    }

    private void fill() {
        this.thisMonthBalance = findNumberOfDays(this.month,this.year);
        int firstDayOfMonth = this.firstDayOfTheMonth.getDayOfWeek().getValue();
        this.prevMonthBalance = firstDayOfMonth % 7;
        int firstValue = findNumberOfDaysInPreviousMonth(month,year) - this.prevMonthBalance + 1;
        for(int i = 0; i < this.prevMonthBalance; i++) {
            this.simulateGridPane[i] = firstValue;
            firstValue++;
        }

        for(int i = 0; i < this.thisMonthBalance; i++) {
            this.simulateGridPane[this.prevMonthBalance + i] = i + 1;
        }

        this.nextMonthBalance = 42 - this.thisMonthBalance - prevMonthBalance;
        int newStartingPoint = this.thisMonthBalance + prevMonthBalance;
        for(int i = 0; i < this.nextMonthBalance; i++) {
            this.simulateGridPane[newStartingPoint + i] = i + 1;
        }

    }

    private Label createLabel(int dayNumber) {
        Label label = new Label();
        label.setText("" + dayNumber);;
        label.setFont(Font.font("system", FontWeight.BOLD,12));
        return label;
    }

    private VBox placeHolderForLabel() {
        VBox holder = new VBox();
        holder.setFillWidth(false);
        holder.setPrefHeight(15);
        holder.setPrefWidth(15);
        holder.setMaxSize(20,20);
        holder.setAlignment(Pos.CENTER);
        return holder;
    }

    public void generateCalender() {
        fill();
        int i = 0;
        this.weekDayGridPane.setBackground(new Background(
                new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        this.dateGridPane.setBackground(new Background(
                new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        for(int row = 0; row < 6; row++) {
            for(int col = 0; col < 7; col++) {
                VBox holder = placeHolderForLabel();

                if(i < this.prevMonthBalance || i > 42 - 1 - this.nextMonthBalance) {
                    holder.setBlendMode(BlendMode.OVERLAY);
                }

                if(i == this.prevMonthBalance + this.day - 1) {

                    holder.setBackground(new Background(
                            new BackgroundFill(Color.LIGHTPINK, CornerRadii.EMPTY, Insets.EMPTY)));

                }

                Label num = createLabel(this.simulateGridPane[i]);

                holder.getChildren().add(num);

                holder.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        Label a = (Label) holder.getChildren().get(0);
                        int clickedDate = Integer.parseInt(a.getText());

                        if(holder.getBlendMode() == BlendMode.OVERLAY) {
                            pivotDate = getNewDate(clickedDate);
                            refreshCalenderView();
                        }else{
                            pivotDate = pivotDate.withDayOfMonth(clickedDate);
                            day = clickedDate;
                            refreshCalenderView();
                        }

                    }
                });

                this.dateGridPane.add(holder, col, row);
                GridPane.setHalignment(holder,HPos.CENTER);
                GridPane.setValignment(holder, VPos.CENTER);
                i++;
            }
        }
    }

    private void refreshCalenderView() {
        dateGridPane.getChildren().clear();
        this.month = this.pivotDate.getMonthValue();
        this.year = this.pivotDate.getYear();
        this.yearMonth = YearMonth.of(this.year, this.month);
        this.firstDayOfTheMonth = yearMonth.atDay(1);
        setUpButton();
        setMonthYearLabel();
        generateCalender();
    }

    private LocalDate getNewDate(int value) {
        if(value <= 31 && value >= 21){
            LocalDate prevM = this.pivotDate.minusMonths(1);
            prevM.withDayOfMonth(value);
            this.day = value;
            return prevM;
        }else{
            LocalDate nextM = this.pivotDate.plusMonths(1);
            nextM.withDayOfMonth(value);
            this.day = value;
            return nextM;
        }
    }

    @FXML
    public void handleToPrev() {
        this.pivotDate = pivotDate.minusMonths(1);
        refreshCalenderView();
    }

    @FXML
    public void handToNext() {
       this.pivotDate = pivotDate.plusMonths(1);
       refreshCalenderView();
    }

}
