package seedu.address.logic.commands.report;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.report.ReportLevelParser;
import seedu.address.model.Model;
import seedu.address.model.Report;

import java.util.HashMap;

/**
 * Export report.
 */
public class ExportReportCommand extends Command {

    public static final String COMMAND_WORD = "export";
    public static final String MESSAGE_SUCCESS = "Report exported to : %1$s";
    public static final String MESSAGE_FAIL = "Report cannot be exported";
    public static final String MESSAGE_USAGE =  ReportLevelParser.COMMAND_WORD + " " + COMMAND_WORD
            + ": Exports the report. "
            + "\n" + "Parameters: "
            + "start date : YYYY-MM-DD  "
            + "end date :  YYYY-MM-DD  "
            + "graph type: PIE/BAR" + "\n"
            + "Example: " + ReportLevelParser.COMMAND_WORD + " " + COMMAND_WORD
            + " 2020-03-22 " + "2020-03-25 " + "PIE";

    private final Report toExport;
    private HashMap statsToExport;
    private Report.GraphType format;
    public ExportReportCommand(Report toExport) {
        this.toExport = toExport;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        statsToExport = new GenerateStats(toExport, model).generateStatsByTags();
        format = toExport.getFormat();
        return new CommandResult(MESSAGE_SUCCESS, format, statsToExport, true, false);
    }
}
