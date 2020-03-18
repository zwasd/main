package seedu.address.ui;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;

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
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * UI component that is displayed.
 */
public class CalendarView extends UiPart<Region> {

    private static final String FXML = "CalendarView.fxml";
    private static final String[] MONTHS = {"January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"};
    private static final int[] DAYS_IN_MONTH = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    private int[] simulateGridPane = new int[42];
    private int day;
    private int month;
    private int year;
    private YearMonth yearMonth;
    private LocalDate todayDate;
    private LocalDate nonPivotDate;
    private LocalDate pivotDate;
    private LocalDate firstDayOfTheMonth;
    private int prevMonthBalance;
    private int nextMonthBalance;
    private int thisMonthBalance;
    private final int dayOfTheMonthForToday;

    private Image leftArrow = new Image(this.getClass().getResourceAsStream("/images/leftButton.png"),
            30, 20, true, true);
    private Image rightArrow = new Image(this.getClass().getResourceAsStream("/images/rightButton.png"),
            30, 20, true, true);

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
        this.todayDate = LocalDate.now();
        this.pivotDate = todayDate;
        this.nonPivotDate = todayDate;
        this.day = todayDate.getDayOfMonth();
        this.month = todayDate.getMonthValue();
        this.year = todayDate.getYear();
        this.dayOfTheMonthForToday = this.day;
        this.yearMonth = YearMonth.of(this.year, this.month);
        this.firstDayOfTheMonth = yearMonth.atDay(1);
        setMonthYearLabel();
        generateCalender();
    }

    /**
     * It will update the attributes in the class according to the date parse in
     * @param date the new date
     */
    private void updateDayMonthYear(LocalDate date) {
        this.year = date.getYear();
        this.month = date.getMonthValue();
        this.day = date.getDayOfMonth();
        this.yearMonth = YearMonth.of(this.year, this.month);
        this.firstDayOfTheMonth = yearMonth.atDay(1);
    }

    private boolean isSameMonth(LocalDate d1, LocalDate d2) {
        LocalDate pivot = d1.withDayOfMonth(1);
        LocalDate toCheck = d2.withDayOfMonth(1);
        return pivot.equals(toCheck);
    }

    /**
     * Set up the left and right arrow button.
     */
    private void setUpButton() {
        ImageView leftButtonView = new ImageView(this.leftArrow);
        ImageView rightButtonView = new ImageView(this.rightArrow);
        leftButton.setPrefSize(30, 20);

        leftButton.setMinSize(30, 20);
        rightButton.setPrefSize(30, 20);
        rightButton.setMinSize(30, 20);
        leftButton.setGraphic(leftButtonView);
        rightButton.setGraphic(rightButtonView);
    }

    /**
     * Find the number of days in a month given the year and month.
     *
     * @return return the number of days.
     */
    public int findNumberOfDaysInTheMonth() {
        if (this.month == 2) {
            if (this.yearMonth.isLeapYear()) {
                return 29;
            } else {
                return DAYS_IN_MONTH[month - 1];
            }
        } else {
            return DAYS_IN_MONTH[month - 1];
        }
    }

    /**
     * Find the number of days in a month given the year and month.
     *
     * @return return the number of days.
     */
    public int findNumberOfDaysOfAMonth(int month, int year) {
        if (month == 2) {
            if (Year.isLeap(year)) {
                return 29;
            } else {
                return DAYS_IN_MONTH[month - 1];
            }
        } else {
            return DAYS_IN_MONTH[month - 1];
        }
    }

    /**
     * Find the number of days in the previous month given the year and month.
     *
     * @return return the number of days.
     */
    public int findNumberOfDaysInPreviousMonth() {
        if (this.month >= 2) {
            return findNumberOfDaysOfAMonth(this.month - 1, this.year);
        } else {
            return DAYS_IN_MONTH[11];
        }
    }

    /**
     * Set the monthYear Label's content.
     */
    private void setMonthYearLabel() {
        monthYearGridPane.setBackground(new Background(
                new BackgroundFill(Color.MEDIUMPURPLE, CornerRadii.EMPTY, Insets.EMPTY)));
        StringBuilder monthYear = new StringBuilder();
        monthYear.append(MONTHS[this.nonPivotDate.getMonthValue() - 1]);
        monthYear.append("  ");
        monthYear.append(this.nonPivotDate.getYear());
        String output = monthYear.toString();
        this.monthYearLabel.setMaxSize(200, 60);
        this.monthYearLabel.setText(output);
        this.monthYearLabel.setFont(Font.font("Cambria", 42));

    }


    /**
     * Fill up the simulateGridPane[] with the date in order.
     */
    private void fill() {
        this.thisMonthBalance = findNumberOfDaysInTheMonth();
        int firstDayOfMonth = this.firstDayOfTheMonth.getDayOfWeek().getValue();
        this.prevMonthBalance = firstDayOfMonth % 7;
        int firstValue = findNumberOfDaysInPreviousMonth() - this.prevMonthBalance + 1;
        for (int i = 0; i < this.prevMonthBalance; i++) {
            this.simulateGridPane[i] = firstValue;
            firstValue++;
        }

        for (int i = 0; i < this.thisMonthBalance; i++) {
            this.simulateGridPane[this.prevMonthBalance + i] = i + 1;
        }

        this.nextMonthBalance = 42 - this.thisMonthBalance - prevMonthBalance;
        int newStartingPoint = this.thisMonthBalance + prevMonthBalance;
        for (int i = 0; i < this.nextMonthBalance; i++) {
            this.simulateGridPane[newStartingPoint + i] = i + 1;
        }
    }

    /**
     * Generate Label for dateGridPane.
     *
     * @param dayNumber text for the Label
     * @return a label with specific text and font
     */
    private Label createLabel(int dayNumber) {
        Label label = new Label();
        label.setText("" + dayNumber);
        label.setStyle("-fx-text-fill: white");
        label.setStyle("-fx-font-weight: bold");
        return label;
    }

    /**
     * Generate a VBox with specific calendar.
     *
     * @return a VBox for dateGridPane.
     */
    private VBox placeHolderForLabel() {
        VBox holder = new VBox();
        holder.setFillWidth(false);
        holder.setPrefHeight(15);
        holder.setPrefWidth(15);
        holder.setMaxSize(20, 20);
        holder.setAlignment(Pos.CENTER);
        return holder;
    }


    /**
     * Assign a Vbox to each GridPane.
     * Each Vbox contains a Label.
     * Each label's text is correspond to the calendar.
     */
    public void generateCalender() {
        fill();
        int i = 0;
        this.weekDayGridPane.setBackground(new Background(
                new BackgroundFill(Color.MEDIUMPURPLE, CornerRadii.EMPTY, Insets.EMPTY)));
        this.dateGridPane.setBackground(new Background(
                new BackgroundFill(Color.MEDIUMPURPLE, CornerRadii.EMPTY, Insets.EMPTY)));
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                VBox holder = placeHolderForLabel();

                if (i < this.prevMonthBalance || i > 42 - 1 - this.nextMonthBalance) {
                    holder.setBlendMode(BlendMode.OVERLAY);
                }

                if (i == this.prevMonthBalance + this.day - 1 && isSameMonth(this.pivotDate, this.nonPivotDate)) {
                    holder.setBackground(new Background(
                            new BackgroundFill(Color.LIGHTPINK, CornerRadii.EMPTY, Insets.EMPTY)));
                }

                if (isSameMonth(this.todayDate, this.nonPivotDate)
                        && i == this.prevMonthBalance + this.dayOfTheMonthForToday - 1) {

                    holder.setBorder(new Border(new BorderStroke(Color.valueOf("#000000"),
                            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
                }

                Label num = createLabel(this.simulateGridPane[i]);

                holder.getChildren().add(num);

                holder.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        Label a = (Label) holder.getChildren().get(0);
                        int clickedDate = Integer.parseInt(a.getText());
                        if (holder.getBlendMode() == BlendMode.OVERLAY) {
                            pivotDate = getNewDate(clickedDate);
                            nonPivotDate = pivotDate;
                            updateDayMonthYear(pivotDate);
                            refreshCalenderView();
                        } else {
                            pivotDate = nonPivotDate.withDayOfMonth(clickedDate);
                            nonPivotDate = pivotDate;
                            updateDayMonthYear(pivotDate);
                            refreshCalenderView();
                        }

                    }
                });

                this.dateGridPane.add(holder, col, row);
                GridPane.setHalignment(holder, HPos.CENTER);
                GridPane.setValignment(holder, VPos.CENTER);
                i++;
            }
        }
    }

    /**
     * Refresh the whole dateGridPane to show latest UI.
     */
    private void refreshCalenderView() {
        dateGridPane.getChildren().clear();
        updateDayMonthYear(nonPivotDate);
        setUpButton();
        setMonthYearLabel();
        generateCalender();
    }

    /**
     * generate a new local date according to input new date.
     *
     * @param value date indicator.
     * @return a new localDate object with that date.
     */
    private LocalDate getNewDate(int value) {
        if (value <= 31 && value >= 21) {
            LocalDate prevM = this.nonPivotDate.minusMonths(1);
            prevM = prevM.withDayOfMonth(value);
            return prevM;
        } else {
            LocalDate nextM = this.nonPivotDate.plusMonths(1);
            nextM = nextM.withDayOfMonth(value);
            return nextM;
        }
    }

    /**
     * Initialise calendar to previous month data when the next button is clicked.
     */
    @FXML
    public void handleToPrev() {
        this.nonPivotDate = nonPivotDate.minusMonths(1);
        updateDayMonthYear(nonPivotDate);
        refreshCalenderView();
    }

    /**
     * Initialise calendar to next month data when the next button is clicked.
     */
    @FXML
    public void handToNext() {
        this.nonPivotDate = nonPivotDate.plusMonths(1);
        updateDayMonthYear(nonPivotDate);
        refreshCalenderView();
    }

    /**
     * This method will update the pivotDate to the new active date.
     * @param date the new pivot date
     */
    public void updateActiveDate(LocalDate date) {
        setUpButton();
        this.pivotDate = date;
        this.nonPivotDate = date;
        this.day = date.getDayOfMonth();
        this.month = date.getMonthValue();
        this.year = date.getYear();
        this.yearMonth = YearMonth.of(this.year, this.month);
        this.firstDayOfTheMonth = yearMonth.atDay(1);
        setMonthYearLabel();
        generateCalender();
    }


}
