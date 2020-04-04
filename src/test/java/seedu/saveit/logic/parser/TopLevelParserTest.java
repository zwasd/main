package seedu.saveit.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.saveit.commons.core.Messages.MESSAGE_EMPTY_COMMAND;
import static seedu.saveit.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.saveit.testutil.Assert.assertThrows;
import static seedu.saveit.testutil.TypicalIndexes.INDEX_FIRST_EXPENDITURE;

import org.junit.jupiter.api.Test;

import seedu.saveit.logic.commands.account.AccClearCommand;
import seedu.saveit.logic.commands.expenditure.ExpAddCommand;
import seedu.saveit.logic.commands.expenditure.ExpDeleteCommand;
import seedu.saveit.logic.commands.expenditure.ExpEditCommand;
import seedu.saveit.logic.commands.expenditure.ExpEditCommand.EditExpenditureDescriptor;
import seedu.saveit.logic.commands.general.ExitCommand;
import seedu.saveit.logic.commands.general.HelpCommand;
import seedu.saveit.logic.parser.account.AccLevelParser;
import seedu.saveit.logic.parser.exceptions.ParseException;
import seedu.saveit.logic.parser.expenditure.ExpLevelParser;
import seedu.saveit.model.expenditure.Expenditure;
import seedu.saveit.testutil.EditExpenditureDescriptorBuilder;
import seedu.saveit.testutil.ExpenditureBuilder;
import seedu.saveit.testutil.ExpenditureUtil;

public class TopLevelParserTest {

    private final TopLevelParser parser = new TopLevelParser();

    @Test
    public void parseCommand_expenditure_add() throws Exception {
        Expenditure expenditure = new ExpenditureBuilder().build();
        ExpAddCommand command = (ExpAddCommand) parser.parseCommand(ExpLevelParser.COMMAND_WORD + " "
                + ExpenditureUtil.getAddCommand(expenditure));
        assertEquals(new ExpAddCommand(expenditure), command);
    }

    @Test
    public void parseCommand_account_clear() throws Exception {
        assertTrue(parser.parseCommand(AccLevelParser.COMMAND_WORD + " " + AccClearCommand.COMMAND_WORD)
                instanceof AccClearCommand);
        /*
        assertTrue(parser.parseCommand(AccLevelParser.COMMAND_WORD + " " + AccClearCommand.COMMAND_WORD + " 3")
                instanceof AccClearCommand);
         */
    }

    @Test
    public void parseCommand_expenditure_delete() throws Exception {
        ExpDeleteCommand command = (ExpDeleteCommand) parser.parseCommand(ExpLevelParser.COMMAND_WORD + " "
                + ExpDeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_EXPENDITURE.getOneBased());
        assertEquals(new ExpDeleteCommand(INDEX_FIRST_EXPENDITURE), command);
    }

    @Test
    public void parseCommand_expenditure_edit() throws Exception {
        Expenditure expenditure = new ExpenditureBuilder().build();
        EditExpenditureDescriptor descriptor = new EditExpenditureDescriptorBuilder(expenditure).build();
        ExpEditCommand command = (ExpEditCommand) parser.parseCommand(ExpLevelParser.COMMAND_WORD + " "
                + ExpEditCommand.COMMAND_WORD + " " + INDEX_FIRST_EXPENDITURE.getOneBased() + " "
                + ExpenditureUtil.getEditExpenditureDescriptorDetails(descriptor));
        assertEquals(new ExpEditCommand(INDEX_FIRST_EXPENDITURE, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }


    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }


    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_EMPTY_COMMAND, ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, ()
            -> parser.parseCommand("unknownCommand"));
    }
}
