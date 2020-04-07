package seedu.saveit.logic.parser.report;

import static seedu.saveit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.saveit.commons.core.Messages.MESSAGE_INVALID_DATE;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_GRAPH;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_ORGANISE;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_START_DATE;

import java.time.LocalDate;
import java.util.stream.Stream;

import seedu.saveit.logic.commands.report.PrintReportCommand;
import seedu.saveit.logic.parser.ArgumentMultimap;
import seedu.saveit.logic.parser.ArgumentTokenizer;
import seedu.saveit.logic.parser.Parser;
import seedu.saveit.logic.parser.ParserUtil;
import seedu.saveit.logic.parser.Prefix;
import seedu.saveit.logic.parser.exceptions.ParseException;
import seedu.saveit.model.expenditure.Date;
import seedu.saveit.model.report.Report;

/**
 * Parses for report print.
 */
public class PrintReportCommandParser implements Parser<PrintReportCommand> {
    @Override
    public PrintReportCommand parse(String userInput) throws ParseException {

        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_START_DATE,
                PREFIX_END_DATE, PREFIX_GRAPH, PREFIX_ORGANISE);

        if (!arePrefixesPresent(argumentMultimap, PREFIX_START_DATE, PREFIX_END_DATE, PREFIX_GRAPH, PREFIX_ORGANISE)
                || !argumentMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, PrintReportCommand.MESSAGE_USAGE));
        }

        Date startDate = ParserUtil.parseDate(argumentMultimap.getValue(PREFIX_START_DATE)
                .orElseGet(() -> LocalDate.now().toString()));
        Date endDate = ParserUtil.parseDate(argumentMultimap.getValue(PREFIX_END_DATE)
                .orElseGet(() -> LocalDate.now().toString()));
        Report.GraphType graphType = ParserUtil.parseGraph(argumentMultimap.getValue(PREFIX_GRAPH)
                .orElseGet(() -> Report.GraphType.PIE.toString()));
        String organise = ParserUtil.parseOrganise(argumentMultimap.getValue(PREFIX_ORGANISE)
                .orElseGet(() -> "tag"));


        if (!Date.isEqualOrBefore(startDate, endDate)) {
            throw new ParseException(String.format(MESSAGE_INVALID_DATE,
                    PrintReportCommand.MESSAGE_USAGE));
        }

        Report report = new Report(startDate, endDate, graphType, organise);

        return new PrintReportCommand(report);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
