package seedu.saveit.logic.commands.report;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.saveit.logic.commands.CommandTestUtil.INVALID_ORGANISATION;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_END_DATE_BUS;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_GRAPH_BAR_CAPS;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_ORGANISATION_MONTH;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_START_DATE_BUS;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_START_DATE_BUS_ALT;
import static seedu.saveit.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.saveit.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.saveit.testutil.Assert.assertThrows;
import static seedu.saveit.testutil.TypicalAccounts.getTypicalAccountList;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import seedu.saveit.logic.commands.CommandResult;
import seedu.saveit.model.Model;
import seedu.saveit.model.ModelManager;
import seedu.saveit.model.UserPrefs;
import seedu.saveit.testutil.ReportBuilder;
import seedu.saveit.ui.Bar;
import seedu.saveit.ui.Graph;


public class PrintReportCommandTest {

    @Test
    public void constructor_nullExpenditure_throwsNullPointerException() {

        assertThrows(NullPointerException.class, () -> new PrintReportCommand(null));

    }

    @Test
    public void equals() {

        ReportBuilder rp = new ReportBuilder()
                .withStartDate(VALID_START_DATE_BUS)
                .withEndDate(VALID_END_DATE_BUS)
                .withGraphType(VALID_GRAPH_BAR_CAPS)
                .withOrganise(VALID_ORGANISATION_MONTH);

        PrintReportCommand command1 = new PrintReportCommand(rp.build());
        PrintReportCommand command2 = new PrintReportCommand(rp.build());

        assertTrue(command1.equals(command2));

        //different start date
        rp.withStartDate(VALID_START_DATE_BUS_ALT);
        PrintReportCommand command3 = new PrintReportCommand(rp.build());
        assertFalse(command1.equals(command3));

        rp.withStartDate(VALID_START_DATE_BUS);

        //different end date
        rp.withEndDate(VALID_START_DATE_BUS_ALT);
        PrintReportCommand command4 = new PrintReportCommand(rp.build());
        assertFalse(command1.equals(command4));

    }

    @Test
    public void executeSuccess() {

        ReportBuilder rp = new ReportBuilder()
                .withStartDate(VALID_START_DATE_BUS)
                .withEndDate(VALID_END_DATE_BUS)
                .withGraphType(VALID_GRAPH_BAR_CAPS)
                .withOrganise(VALID_ORGANISATION_MONTH);

        Model model = new ModelManager(getTypicalAccountList(), new UserPrefs());
        PrintReportCommand command1 = new PrintReportCommand(rp.build());
        Graph graph = new Bar(new HashMap(), VALID_ORGANISATION_MONTH);
        CommandResult r = new CommandResult(PrintReportCommand.MESSAGE_SUCCESS, graph, false, false, true);
        assertCommandSuccess(command1, model, r, model);
    }

    @Test
    public void executeFailure() {
        ReportBuilder rp = new ReportBuilder()
                .withStartDate(VALID_START_DATE_BUS)
                .withEndDate(VALID_END_DATE_BUS)
                .withGraphType(VALID_GRAPH_BAR_CAPS)
                .withOrganise(INVALID_ORGANISATION);

        PrintReportCommand command1 = new PrintReportCommand(rp.build());
        Model model = new ModelManager(getTypicalAccountList(), new UserPrefs());

        assertCommandFailure(command1, model, PrintReportCommand.MESSAGE_FAIL);
    }


}
