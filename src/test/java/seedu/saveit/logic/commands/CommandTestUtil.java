package seedu.saveit.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static seedu.saveit.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_FILENAME;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_GRAPH;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_INFO;

import static seedu.saveit.logic.parser.CliSyntax.PREFIX_ORGANISE;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_PERIOD;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.saveit.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.saveit.commons.core.index.Index;
import seedu.saveit.logic.commands.exceptions.CommandException;
import seedu.saveit.logic.commands.expenditure.ExpEditCommand.EditExpenditureDescriptor;
import seedu.saveit.model.AccountList;
import seedu.saveit.model.Model;
import seedu.saveit.model.expenditure.BaseExp;
import seedu.saveit.model.expenditure.Expenditure;
import seedu.saveit.model.expenditure.InfoContainsKeywordsPredicate;
import seedu.saveit.testutil.EditExpenditureDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {
    public static final String VALID_INFO_BUS = "Bus fare";
    public static final String VALID_INFO_MRT = "Mrt fare";
    public static final double VALID_AMOUNT_BUS = 3.14;
    public static final double VALID_AMOUNT_MRT = 1.34;
    public static final String VALID_DATE_BUS = "2019-09-11";
    public static final String VALID_DATE_MRT = "2019-09-12";
    public static final String VALID_TAG_BUS = "BUS";
    public static final String VALID_TAG_TRANSPORT = "Transport";
    public static final String INFO_DESC_BUS = " " + PREFIX_INFO + VALID_INFO_BUS;
    public static final String INFO_DESC_MRT = " " + PREFIX_INFO + VALID_INFO_MRT;
    public static final String AMOUNT_DESC_BUS = " " + PREFIX_AMOUNT + VALID_AMOUNT_BUS;
    public static final String AMOUNT_DESC_MRT = " " + PREFIX_AMOUNT + VALID_AMOUNT_MRT;
    public static final String DATE_DESC_BUS = " " + PREFIX_DATE + VALID_DATE_BUS;
    public static final String DATE_DESC_MRT = " " + PREFIX_DATE + VALID_DATE_MRT;
    public static final String TAG_DESC_TRANSPORT = " " + PREFIX_TAG + VALID_TAG_TRANSPORT;
    public static final String TAG_DESC_BUS = " " + PREFIX_TAG + VALID_TAG_BUS;
    public static final String INVALID_INFO_DESC = " " + PREFIX_INFO + ""; // '' not allowed in infos
    public static final String INVALID_AMOUNT_DESC = " " + PREFIX_AMOUNT + "bob!yahoo"; // a string
    public static final String INVALID_DATE_DESC = " " + PREFIX_DATE; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";


    public static final String VALID_START_DATE_BUS = "2019-01-02";
    public static final String VALID_START_DATE_BUS_ALT = "2019-02-01";
    public static final String VALID_END_DATE_BUS = "2019-04-03";
    public static final String START_DATE_DESC_BUS = " " + PREFIX_START_DATE + VALID_START_DATE_BUS;
    public static final String END_DATE_DESC_BUS = " " + PREFIX_END_DATE + VALID_END_DATE_BUS;
    public static final String VALID_START_DATE_MRT = "2020-01-01";
    public static final String VALID_END_DATE_MRT = "2020-04-01";
    public static final String START_DATE_DESC_MRT = " " + PREFIX_START_DATE + VALID_START_DATE_MRT;
    public static final String END_DATE_DESC_MRT = " " + PREFIX_END_DATE + VALID_END_DATE_MRT;
    public static final String VALID_DAILY_PERIOD = "DAILY";
    public static final String VALID_WEEKLY_PERIOD = "WEEKLY";
    public static final String VALID_MONTHLY_PERIOD = "MONTHLY";
    public static final String VALID_ANNUALLY_PERIOD = "ANNUALLY";
    public static final String VALID_DAILY_PERIOD_DESC = " " + PREFIX_PERIOD + VALID_DAILY_PERIOD;
    public static final String VALID_WEEKLY_PERIOD_DESC = " " + PREFIX_PERIOD + VALID_WEEKLY_PERIOD;
    public static final String VALID_MONTHLY_PERIOD_DESC = " " + PREFIX_PERIOD + VALID_MONTHLY_PERIOD;


    // this date does not exist
    public static final String INVALID_START_DATE_DESC = " " + PREFIX_START_DATE + "2019-02-31";
    public static final String INVALID_END_DATE_DESC = " " + PREFIX_END_DATE; // empty string
    public static final String INVALID_PERIOD_DESC = " " + PREFIX_PERIOD + "MINUTE"; // empty string

    //report
    public static final String VALID_GRAPH_BAR_CAPS = "bar";
    public static final String VALID_GRAPH_PIE_CAPS = "pie";
    public static final String INVALID_GRAPH = "";
    public static final String VALID_GRAPH_BAR_DESC_CAPS = " " + PREFIX_GRAPH + VALID_GRAPH_BAR_CAPS;
    public static final String VALID_GRAPH_PIE_DESC_CAPS = " " + PREFIX_GRAPH + VALID_GRAPH_PIE_CAPS;
    public static final String INVALID_GRAPH_DESC = " " + PREFIX_GRAPH + INVALID_GRAPH;
    public static final String VALID_FILE_NAME = "Hello";
    public static final String VALID_FILE_NAME_ALT = "hey";
    public static final String INVALID_FILE_NAME = " ";
    public static final String VALID_FILE_NAME_DESC = " " + PREFIX_FILENAME + VALID_FILE_NAME;
    public static final String INVALID_FILE_NAME_DESC = " " + PREFIX_FILENAME + INVALID_FILE_NAME;
    public static final String VALID_ORGANISATION_TAG = "tag";
    public static final String VALID_ORGANISATION_MONTH = "month";
    public static final String INVALID_ORGANISATION = "";
    public static final String VALID_ORGANISATION_TAG_DESC = " " + PREFIX_ORGANISE + VALID_ORGANISATION_TAG;
    public static final String VALID_ORGANISATION_MONTH_DESC = " " + PREFIX_ORGANISE + VALID_ORGANISATION_MONTH;
    public static final String INVALID_ORGANISATION_DESC = " " + PREFIX_ORGANISE + INVALID_ORGANISATION;


    public static final EditExpenditureDescriptor DESC_AMY;
    public static final EditExpenditureDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditExpenditureDescriptorBuilder().withInfo(VALID_INFO_BUS)
                .withAmount(VALID_AMOUNT_BUS).withDate(VALID_DATE_BUS).build();
        DESC_BOB = new EditExpenditureDescriptorBuilder().withInfo(VALID_INFO_MRT)
                .withAmount(VALID_AMOUNT_MRT).withDate(VALID_DATE_MRT)
                .withTag(VALID_TAG_BUS).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(ReportCommand command, Model actualModel,
                                            ReportCommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            ReportCommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(ReportCommand command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        ReportCommandResult expectedCommandResult = new ReportCommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }


    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered expenditure list and selected expenditure in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(ReportCommand command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.

        AccountList expectedAccountList = new AccountList(actualModel.getAccountList());
        List<Expenditure> expectedFilteredList = new ArrayList<>(actualModel.getFilteredExpenditureList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAccountList, actualModel.getAccountList());
        assertEquals(expectedFilteredList, actualModel.getFilteredExpenditureList());
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered expenditure list and selected expenditure in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.

        AccountList expectedAccountList = new AccountList(actualModel.getAccountList());
        List<Expenditure> expectedFilteredList = new ArrayList<>(actualModel.getFilteredExpenditureList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAccountList, actualModel.getAccountList());
        assertEquals(expectedFilteredList, actualModel.getFilteredExpenditureList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the expenditure at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showExpenditureAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredBaseExpList().size());

        BaseExp baseExp = model.getFilteredBaseExpList().get(targetIndex.getZeroBased());
        final String[] splitInfo = baseExp.getInfo().fullInfo.split("\\s+");
        model.updateFilteredBaseExpList(new InfoContainsKeywordsPredicate(Arrays.asList(splitInfo[0])));
        assertEquals(1, model.getFilteredBaseExpList().size());
    }

}
