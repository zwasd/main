package seedu.saveit.testutil;

import static seedu.saveit.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_FILENAME;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_GRAPH;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_ORGANISE;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_START_DATE;

import seedu.saveit.logic.commands.report.ExportReportCommand;
import seedu.saveit.logic.commands.report.PrintReportCommand;
import seedu.saveit.logic.commands.report.ViewReportCommand;
import seedu.saveit.model.report.Report;

/**
 * A utility class for ReportUtil.
 */
public class ReportUtil {

    /**
     * Returns an VIEW command string for viewing report {@code report}.
     */
    public static String getReportViewCommand(Report report) {
        return ViewReportCommand.COMMAND_WORD + " " + getReportDetails(report);
    }

    /**
     * Returns an export command string for export report {@code report}.
     */
    public static String getReportExportCommand(Report report, String fileName) {
        return ExportReportCommand.COMMAND_WORD + " " + getReportDetails(report) + PREFIX_FILENAME + fileName;
    }

    /**
     * Returns an print command string for print report {@code report}.
     */
    public static String getReportPrintCommand(Report report) {
        return PrintReportCommand.COMMAND_WORD + " " + getReportDetails(report);
    }



    /**
     * Returns the part of command string for the given {@code repeat}'s details.
     */
    public static String getReportDetails(Report report) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_START_DATE + report.getStartDate().localDate.toString() + " ");
        sb.append(PREFIX_END_DATE + report.getEndDate().localDate.toString() + " ");
        sb.append(PREFIX_GRAPH + report.getFormat().toString().toLowerCase() + " ");
        sb.append(PREFIX_ORGANISE + report.getOrganise() + " ");
        return sb.toString();
    }

}
