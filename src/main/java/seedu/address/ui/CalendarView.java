package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import seedu.address.model.expenditure.Date;

import java.time.DayOfWeek;
import java.util.Calendar;
import java.time.LocalDate;
import java.time.YearMonth;

public class CalendarView extends UiPart<Region> {

    private static final String GREY = "#808080";
    private static final String BLACK = "#000000";
    private static final String FXML = "CalendarPanel.fxml";
    private static final String[] MONTHS = {"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};
    private static final int[] DAYS_IN_MONTH = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    private int[] simulateGridPane = new int [42];
    private String[] colorCode = new String [42];
    private int month;
    private int year;
    private YearMonth yearMonth;
    private DayOfWeek firstDay;

    @FXML
    private Label monthYearLabel;

    @FXML
    private GridPane dateGridPane;


    public CalendarView() {
        super(FXML);
        LocalDate now = LocalDate.now();
        this.month = now.getMonthValue();
        this.year = now.getYear();
        this.yearMonth = YearMonth.of(this.year, this.month);
        setMonthYearLabel();
        generateCalender();
    }

    public CalendarView(Date date) {
        super(FXML);
        this.month = date.localDate.getMonthValue();
        this.year = date.localDate.getYear();
        setMonthYearLabel();
        generateCalender();
    }

    public boolean checkLeapYear(int year) {
        if(year % 4 == 0) {
            return true;
        }
        return false;
    }

    public Label createLabel(int dayNumber, String colorCode) {
        Label label = new Label();
        label.setText("" + dayNumber);
        label.setFont(new Font(12));
        label.setTextFill(Paint.valueOf(colorCode));
        return label;
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
        StringBuilder monthYear = new StringBuilder();
        monthYear.append(MONTHS[this.month - 1]);
        monthYear.append(" ");
        monthYear.append(this.year);
        String output = monthYear.toString();
        this.monthYearLabel.setText(output);
    }

    private void fill() {
        int days = findNumberOfDays(month,year);
        int firstDayOfMonth = this.firstDay.getValue();


        int prevMonthRemainder = firstDayOfMonth % 7;
        int firstValue = findNumberOfDaysInPreviousMonth(month,year) - prevMonthRemainder + 1;
        for(int i = 0; i < prevMonthRemainder; i++) {
            this.simulateGridPane[i] = firstValue;
            this.colorCode[i] = GREY;
            firstValue++;
        }

        for(int i = 0; i < days; i++) {
            this.simulateGridPane[prevMonthRemainder + i] = i + 1;
            this.colorCode[prevMonthRemainder + i] = BLACK;
        }

        int nextMonthDays = 42 - days - prevMonthRemainder;
        int newStartingPoint = days + prevMonthRemainder;
        for(int i = 0; i < nextMonthDays; i++) {
            this.simulateGridPane[newStartingPoint + i] = i + 1;
            this.colorCode[newStartingPoint + i] = GREY;
        }

    }

    public void generateCalender() {
        fill();
        int i = 0;
        for(int row = 0; row < 6; row++) {
            for(int col = 0; col < 7; col++) {
                Label num = createLabel(this.simulateGridPane[i], this.colorCode[i]);
                this.dateGridPane.add(num, row, col);
                i++;
            }
        }
    }



}
