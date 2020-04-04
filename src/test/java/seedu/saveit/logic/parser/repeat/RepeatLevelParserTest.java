package seedu.saveit.logic.parser.repeat;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static seedu.saveit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.saveit.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.saveit.testutil.Assert.assertThrows;
import static seedu.saveit.testutil.TypicalIndexes.INDEX_FIRST_EXPENDITURE;
import static seedu.saveit.testutil.TypicalIndexes.INDEX_FIRST_REPEAT;

import org.junit.jupiter.api.Test;

import seedu.saveit.logic.commands.general.HelpCommand;
import seedu.saveit.logic.commands.repeat.RepeatAddCommand;
import seedu.saveit.logic.commands.repeat.RepeatDeleteCommand;
import seedu.saveit.logic.commands.repeat.RepeatEditCommand;
import seedu.saveit.logic.parser.exceptions.ParseException;
import seedu.saveit.model.expenditure.Repeat;
import seedu.saveit.testutil.EditRepeatDescriptorBuilder;
import seedu.saveit.testutil.RepeatBuilder;
import seedu.saveit.testutil.RepeatUtil;


public class RepeatLevelParserTest {
    private final RepeatLevelParser parser = new RepeatLevelParser();
    @Test
    public void parseExpCommand_add() throws Exception {
        Repeat repeat = new RepeatBuilder().build();
        RepeatAddCommand command = (RepeatAddCommand) parser.parseCommand(RepeatUtil.getAddCommand(repeat));
        assertEquals(new RepeatAddCommand(repeat), command);
    }

    @Test
    public void parseExpCommand_delete() throws Exception {
        RepeatDeleteCommand command = (RepeatDeleteCommand) parser
                .parseCommand(RepeatDeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_REPEAT.getOneBased());
        assertEquals(new RepeatDeleteCommand(INDEX_FIRST_EXPENDITURE), command);
    }

    @Test
    public void parseRepeatCommand_edit() throws Exception {
        Repeat repeat = new RepeatBuilder().build();
        RepeatEditCommand.EditRepeatDescriptor descriptor = new EditRepeatDescriptorBuilder(repeat).build();
        RepeatEditCommand command = (RepeatEditCommand) parser
                .parseCommand(RepeatEditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_REPEAT.getOneBased() + " "
                + RepeatUtil.getEditRepeatDescriptorDetails(descriptor));
        assertEquals(new RepeatEditCommand(INDEX_FIRST_REPEAT, descriptor), command);
    }

    @Test
    public void repeatParseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void repeatParseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser
                .parseCommand("unknownCommand"));
    }
}
