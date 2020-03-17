package seedu.address.logic.parser.expenditure;

import seedu.address.logic.commands.expenditure.ExpSetBudgetCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parse set budget.
 */
public class ExpSetBudgetCommandParser implements Parser<ExpSetBudgetCommand> {
    public ExpSetBudgetCommandParser() {}
    @Override
    public ExpSetBudgetCommand parse(String userInput) throws ParseException {

        String userInputTrimmed = userInput.trim();
        String[] userInputArray = userInputTrimmed.split(" ");

        if(userInputArray.length < 3) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExpSetBudgetCommand.MESSAGE_FAIL));
        }

        double budget;
        LocalDate date;

        try{
            budget = Double.parseDouble(userInputArray[1]);
            date = LocalDate.parse(userInputArray[2], DateTimeFormatter.ofPattern("YYYY-MM"));
        } catch (Exception e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExpSetBudgetCommand.MESSAGE_FAIL));
        }

        return new ExpSetBudgetCommand(date, budget);
    }
}
