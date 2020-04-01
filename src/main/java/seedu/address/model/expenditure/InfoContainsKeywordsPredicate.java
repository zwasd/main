package seedu.address.model.expenditure;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code BaseExp}'s {@code Info} matches any of the keywords given.
 */
public class InfoContainsKeywordsPredicate implements Predicate<BaseExp> {
    private final List<String> keywords;

    public InfoContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(BaseExp expenditure) {
        return keywords.stream()
                .anyMatch(keyword ->
                        StringUtil.containsSubstringInWordIgnoreCase(expenditure.getInfo().fullInfo, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InfoContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((InfoContainsKeywordsPredicate) other).keywords)); // state check
    }

    public String getKeywordsString() {
        return String.join(", ", keywords);
    }

}
