package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EXPENDITURE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.account.AccClearCommand;
import seedu.address.logic.commands.expenditure.ExpAddCommand;
import seedu.address.logic.commands.expenditure.ExpDeleteCommand;
import seedu.address.logic.commands.expenditure.ExpEditCommand;
import seedu.address.logic.commands.expenditure.ExpEditCommand.EditExpenditureDescriptor;
import seedu.address.logic.commands.general.ExitCommand;
import seedu.address.logic.commands.general.HelpCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.expenditure.Expenditure;
import seedu.address.model.expenditure.InfoContainsKeywordsPredicate;
import seedu.address.testutil.EditExpenditureDescriptorBuilder;
import seedu.address.testutil.ExpenditureBuilder;
import seedu.address.testutil.ExpenditureUtil;

public class TopLevelParserTest {

    private final TopLevelParser parser = new TopLevelParser();

    @Test
    public void parseCommand_add() throws Exception {
        Expenditure expenditure = new ExpenditureBuilder().build();
        ExpAddCommand command = (ExpAddCommand) parser.parseCommand(ExpenditureUtil.getAddCommand(expenditure));
        assertEquals(new ExpAddCommand(expenditure), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(AccClearCommand.COMMAND_WORD) instanceof AccClearCommand);
        assertTrue(parser.parseCommand(AccClearCommand.COMMAND_WORD + " 3") instanceof AccClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        ExpDeleteCommand command = (ExpDeleteCommand) parser.parseCommand(
                seedu.address.logic.commands.expenditure.ExpDeleteCommand.COMMAND_WORD
                        + " " + INDEX_FIRST_EXPENDITURE.getOneBased());
        assertEquals(new ExpDeleteCommand(INDEX_FIRST_EXPENDITURE), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Expenditure expenditure = new ExpenditureBuilder().build();
        EditExpenditureDescriptor descriptor = new EditExpenditureDescriptorBuilder(expenditure).build();
        ExpEditCommand command = (ExpEditCommand) parser.parseCommand(ExpEditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_EXPENDITURE.getOneBased() + " " + ExpenditureUtil.getEditExpenditureDescriptorDetails(descriptor));
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
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, ()
            -> parser.parseCommand("unknownCommand"));
    }
}
