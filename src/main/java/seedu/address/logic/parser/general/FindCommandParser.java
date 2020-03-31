package seedu.address.logic.parser.general;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STOP_FIND;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_BASEEXP;

import java.util.Arrays;

import seedu.address.logic.commands.general.FindCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.expenditure.InfoContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        if (nameKeywords.length == 1 && nameKeywords[0].equals(PREFIX_STOP_FIND.getPrefix().trim())) {
            return new FindCommand(PREDICATE_SHOW_ALL_BASEEXP);
        }

        for (String keyword: nameKeywords) {
            ParserUtil.parseInfo(keyword); // will throw exception if any keyword contains any illegal characters
        }

        return new FindCommand(new InfoContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
