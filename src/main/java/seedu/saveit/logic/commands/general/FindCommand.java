package seedu.saveit.logic.commands.general;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.saveit.commons.core.Messages;
import seedu.saveit.logic.commands.Command;
import seedu.saveit.logic.commands.CommandResult;
import seedu.saveit.model.Model;
import seedu.saveit.model.expenditure.BaseExp;
import seedu.saveit.model.expenditure.InfoContainsKeywordsPredicate;

/**
 * Finds and lists all expenditures in address book whose info contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all expenditures which contain any of "
            + "the specified keyword(s) (case-insensitive) AND the specified tag (if present).\n"
            + "Parameters: [KEYWORD...] [-t TAG]\n"
            + "Example: " + COMMAND_WORD + " apple -t Food\n"
            + "Note: at least one keyword OR the tag must be specified";

    private final Predicate<BaseExp> predicate;
    private final String keywordsString;
    private final String tag;

    public FindCommand(Predicate<BaseExp> predicate, String keywordsString, String tag) {
        requireNonNull(predicate);
        this.predicate = predicate;
        this.keywordsString = keywordsString;
        this.tag = tag;
    }

    public FindCommand(InfoContainsKeywordsPredicate predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
        this.keywordsString = predicate.getKeywordsString();
        this.tag = null;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredBaseExpList(predicate);
        String result = (keywordsString == null ? "" : "Searched keywords: " + keywordsString + "\n")
                + (tag == null ? "" : "Searched tag: " + tag + "\n")
                + String.format(Messages.MESSAGE_EXPENDITURES_LISTED_OVERVIEW, model.getFilteredBaseExpList().size())
                + "\nEnter \"exp list\" to clear search results";
        return new CommandResult(result);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
