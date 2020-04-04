package seedu.saveit.logic.parser.general;

import static java.util.Objects.requireNonNull;
import static seedu.saveit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;

import seedu.saveit.logic.commands.general.FindCommand;
import seedu.saveit.logic.parser.ArgumentMultimap;
import seedu.saveit.logic.parser.ArgumentTokenizer;
import seedu.saveit.logic.parser.Parser;
import seedu.saveit.logic.parser.ParserUtil;
import seedu.saveit.logic.parser.exceptions.ParseException;
import seedu.saveit.model.expenditure.InfoContainsKeywordsPredicate;
import seedu.saveit.model.expenditure.Tag;

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
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TAG);
        String trimmedArgs = argMultimap.getPreamble();
        if (trimmedArgs.isEmpty() && !argMultimap.getValue(PREFIX_TAG).isPresent()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        if (!trimmedArgs.isEmpty()) {
            String[] nameKeywords = trimmedArgs.split("\\s+");
            for (String keyword: nameKeywords) {
                ParserUtil.parseInfo(keyword); // will throw exception if any keyword contains any illegal characters
            }
            InfoContainsKeywordsPredicate predicate = new InfoContainsKeywordsPredicate(Arrays.asList(nameKeywords));
            if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
                Tag tag = ParserUtil.parseTag(argMultimap.getValue(PREFIX_TAG).get());
                return new FindCommand(baseExp -> predicate.test(baseExp) && baseExp.getTag().equals(tag),
                                        predicate.getKeywordsString(), tag.getTagName());
            } else {
                return new FindCommand(predicate);
            }
        } else {
            Tag tag = ParserUtil.parseTag(argMultimap.getValue(PREFIX_TAG).get());
            return new FindCommand(baseExp -> baseExp.getTag().equals(tag), null, tag.getTagName());
        }
    }

}
