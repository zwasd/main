package seedu.saveit.logic.parser.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.saveit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.saveit.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.saveit.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.saveit.logic.commands.account.AccAddCommand;
import seedu.saveit.logic.commands.account.AccCheckoutCommand;
import seedu.saveit.logic.commands.account.AccClearCommand;
import seedu.saveit.logic.commands.account.AccDeleteCommand;
import seedu.saveit.logic.commands.account.AccListCommand;
import seedu.saveit.logic.commands.account.AccRenameCommand;
import seedu.saveit.logic.commands.general.HelpCommand;
import seedu.saveit.logic.parser.exceptions.ParseException;
import seedu.saveit.model.Account;
import seedu.saveit.testutil.AccountBuilder;
import seedu.saveit.testutil.AccountUtil;

public class AccLevelParserTest {
    private final AccLevelParser parser = new AccLevelParser();

    @Test
    public void parseAccCommand_add() throws Exception {
        Account account = new AccountBuilder("school").build();
        AccAddCommand command = (AccAddCommand) parser.parseCommand(AccountUtil.getAddCommand(account));
        assertEquals(new AccAddCommand(account), command);
    }

    @Test
    public void parseAccCommand_checkout() throws Exception {
        AccCheckoutCommand command = (AccCheckoutCommand) parser.parseCommand(
                AccCheckoutCommand.COMMAND_WORD + " school");
        assertEquals(new AccCheckoutCommand("school"), command);
    }

    @Test
    public void parseAccCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(AccClearCommand.COMMAND_WORD) instanceof AccClearCommand);
    }

    @Test
    public void parseAccCommand_delete() throws Exception {
        AccDeleteCommand command = (AccDeleteCommand) parser.parseCommand(AccDeleteCommand.COMMAND_WORD + " school");
        assertEquals(new AccDeleteCommand("school"), command);
    }

    @Test
    public void parseAccCommand_list() throws Exception {
        assertTrue(parser.parseCommand(AccListCommand.COMMAND_WORD) instanceof AccListCommand);
    }

    @Test
    public void parseAccCommand_rename() throws Exception {
        AccRenameCommand command = (AccRenameCommand) parser.parseCommand(
                AccRenameCommand.COMMAND_WORD + " school work");
        assertEquals(new AccRenameCommand("school", "work"), command);
    }

    @Test
    public void accParseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void accParseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, ()
            -> parser.parseCommand("unknownCommand"));
    }

}
