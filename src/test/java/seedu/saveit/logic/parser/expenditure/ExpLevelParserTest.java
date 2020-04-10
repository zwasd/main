package seedu.saveit.logic.parser.expenditure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static seedu.saveit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.saveit.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.saveit.testutil.Assert.assertThrows;
import static seedu.saveit.testutil.TypicalIndexes.INDEX_FIRST_EXPENDITURE;

import java.time.LocalDate;
import java.time.YearMonth;

import org.junit.jupiter.api.Test;

import seedu.saveit.logic.commands.expenditure.ExpAddCommand;

import seedu.saveit.logic.commands.expenditure.ExpDeleteCommand;
import seedu.saveit.logic.commands.expenditure.ExpEditCommand;
import seedu.saveit.logic.commands.expenditure.ExpEditCommand.EditExpenditureDescriptor;
import seedu.saveit.logic.commands.expenditure.ExpSetBudgetCommand;
import seedu.saveit.logic.commands.general.HelpCommand;
import seedu.saveit.logic.parser.exceptions.ParseException;
import seedu.saveit.model.expenditure.Amount;
import seedu.saveit.model.expenditure.Expenditure;
import seedu.saveit.testutil.EditExpenditureDescriptorBuilder;
import seedu.saveit.testutil.ExpenditureBuilder;
import seedu.saveit.testutil.ExpenditureUtil;



public class ExpLevelParserTest {
    private final ExpLevelParser parser = new ExpLevelParser();

    @Test
    public void parseExpCommand_add() throws Exception {
        Expenditure expenditure = new ExpenditureBuilder().build();
        ExpAddCommand command = (ExpAddCommand) parser.parseCommand(ExpenditureUtil.getAddCommand(expenditure));
        assertEquals(new ExpAddCommand(expenditure), command);
    }


    @Test
    public void parseExpCommand_delete() throws Exception {
        ExpDeleteCommand command = (ExpDeleteCommand) parser.parseCommand(ExpDeleteCommand.COMMAND_WORD
                        + " " + INDEX_FIRST_EXPENDITURE.getOneBased());
        assertEquals(new ExpDeleteCommand(INDEX_FIRST_EXPENDITURE), command);
    }

    @Test
    public void parseExpCommand_setBudget() throws Exception {
        YearMonth inputYearMonth = YearMonth.from(LocalDate.now());
        ExpSetBudgetCommand command = (ExpSetBudgetCommand) parser.parseCommand(ExpSetBudgetCommand.COMMAND_WORD
                + " -a 500 -ym " + inputYearMonth.toString());

        assertEquals(new ExpSetBudgetCommand(inputYearMonth, new Amount(500)), command);
    }


    @Test
    public void parseExpCommand_edit() throws Exception {
        Expenditure expenditure = new ExpenditureBuilder().build();
        EditExpenditureDescriptor descriptor = new EditExpenditureDescriptorBuilder(expenditure).build();
        ExpEditCommand command = (ExpEditCommand) parser.parseCommand(ExpEditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_EXPENDITURE.getOneBased() + " "
                + ExpenditureUtil.getEditExpenditureDescriptorDetails(descriptor));
        assertEquals(new ExpEditCommand(INDEX_FIRST_EXPENDITURE, descriptor), command);
    }

    @Test
    public void expParseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void expParseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }

}
