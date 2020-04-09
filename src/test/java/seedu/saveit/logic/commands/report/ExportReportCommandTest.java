package seedu.saveit.logic.commands.report;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static seedu.saveit.logic.commands.CommandTestUtil.INVALID_ORGANISATION;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_END_DATE_BUS;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_FILE_NAME;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_FILE_NAME_ALT;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_GRAPH_BAR_CAPS;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_ORGANISATION_MONTH;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_ORGANISATION_TAG;
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
import seedu.saveit.testutil.ExportFileBuilder;
import seedu.saveit.testutil.ReportBuilder;
import seedu.saveit.ui.Pie;

public class ExportReportCommandTest {

    @Test
    public void constructor_nullExpenditure_throwsNullPointerException() {

        ReportBuilder builder = new ReportBuilder()
                .withStartDate(VALID_START_DATE_BUS)
                .withEndDate(VALID_END_DATE_BUS)
                .withGraphType(VALID_GRAPH_BAR_CAPS)
                .withOrganise(VALID_ORGANISATION_TAG);


        assertThrows(NullPointerException.class, () -> new ExportReportCommand(null, VALID_FILE_NAME));
        assertThrows(NullPointerException.class, () -> new ExportReportCommand(builder.build(), null));
        assertThrows(NullPointerException.class, () -> new ExportReportCommand(null, null));

    }

    @Test
    public void equals() {
        ReportBuilder rp = new ReportBuilder()
                .withStartDate(VALID_START_DATE_BUS)
                .withEndDate(VALID_END_DATE_BUS)
                .withGraphType(VALID_GRAPH_BAR_CAPS)
                .withOrganise(VALID_ORGANISATION_MONTH);

        ExportReportCommand command1 = new ExportReportCommand(rp.build(), VALID_FILE_NAME);
        ExportReportCommand command2 = new ExportReportCommand(rp.build(), VALID_FILE_NAME);


        assertTrue(command1.equals(command2));

        //different start date
        rp.withStartDate(VALID_START_DATE_BUS_ALT);
        ExportReportCommand command3 = new ExportReportCommand(rp.build(), VALID_FILE_NAME);
        assertFalse(command1.equals(command3));
        rp.withStartDate(VALID_START_DATE_BUS);

        //different end date
        rp.withEndDate(VALID_START_DATE_BUS_ALT);
        ExportReportCommand command4 = new ExportReportCommand(rp.build(), VALID_FILE_NAME);
        assertFalse(command1.equals(command4));
        rp.withEndDate(VALID_END_DATE_BUS);

        //different file name
        ExportReportCommand command5 = new ExportReportCommand(rp.build(), VALID_FILE_NAME_ALT);
        assertFalse(command1.equals(command5));

    }

    @Test
    public void executeSuccess() {

        ReportBuilder rp = new ReportBuilder()
                .withStartDate(VALID_START_DATE_BUS)
                .withEndDate(VALID_END_DATE_BUS)
                .withGraphType(VALID_GRAPH_BAR_CAPS)
                .withOrganise(VALID_ORGANISATION_MONTH);

        ExportFileBuilder f = new ExportFileBuilder()
                .withFileName(VALID_FILE_NAME)
                .withGraph(new Pie(new HashMap(), VALID_ORGANISATION_MONTH));


        Model model = new ModelManager(getTypicalAccountList(), new UserPrefs());

        ExportReportCommand command1 = new ExportReportCommand(rp.build(), VALID_FILE_NAME);
        CommandResult r = new CommandResult(ExportReportCommand.MESSAGE_SUCCESS, f.build(), true);

        assertCommandSuccess(command1, model, r, model);

    }

    @Test
    public void executeFailure() {

        ReportBuilder rp = new ReportBuilder()
                .withStartDate(VALID_START_DATE_BUS)
                .withEndDate(VALID_END_DATE_BUS)
                .withGraphType(VALID_GRAPH_BAR_CAPS)
                .withOrganise(INVALID_ORGANISATION);

        ExportReportCommand command1 = new ExportReportCommand(rp.build(), VALID_FILE_NAME);
        Model model = new ModelManager(getTypicalAccountList(), new UserPrefs());

        assertCommandFailure(command1, model, ExportReportCommand.MESSAGE_FAIL);
    }

}
