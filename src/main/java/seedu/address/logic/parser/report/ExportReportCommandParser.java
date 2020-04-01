package seedu.address.logic.parser.report;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRAPH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;

import java.time.LocalDate;
import java.util.stream.Stream;

import seedu.address.logic.commands.report.ExportReportCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Report;
import seedu.address.model.expenditure.Date;

/**
 * Parse export report.
 */
public class ExportReportCommandParser implements Parser<ExportReportCommand> {
    public ExportReportCommandParser() {

    }

    @Override
    public ExportReportCommand parse(String userInput) throws ParseException {

        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_START_DATE,
                PREFIX_END_DATE, PREFIX_GRAPH);

        if (!arePrefixesPresent(argumentMultimap, PREFIX_START_DATE, PREFIX_END_DATE, PREFIX_GRAPH)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExportReportCommand.MESSAGE_USAGE));
        }

        Date startDate = ParserUtil.parseDate(argumentMultimap.getValue(PREFIX_START_DATE)
                .orElseGet(() -> LocalDate.now().toString()));
        Date endDate = ParserUtil.parseDate(argumentMultimap.getValue(PREFIX_END_DATE)
                .orElseGet(() -> LocalDate.now().toString()));
        Report.GraphType graphType = ParserUtil.parseGraph(argumentMultimap.getValue(PREFIX_GRAPH)
                .orElseGet(() -> Report.GraphType.PIE.toString()));


        if (!Date.isEqualOrBefore(startDate, endDate)) {
            throw new ParseException(String.format(MESSAGE_INVALID_DATE,
                    ExportReportCommand.MESSAGE_USAGE));
        }

        Report report = new Report(startDate, endDate, graphType);

        return new ExportReportCommand(report);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
