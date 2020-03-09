package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class InfoContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        InfoContainsKeywordsPredicate firstPredicate = new InfoContainsKeywordsPredicate(firstPredicateKeywordList);
        InfoContainsKeywordsPredicate secondPredicate = new InfoContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        InfoContainsKeywordsPredicate firstPredicateCopy = new InfoContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_infoContainsKeywords_returnsTrue() {
        // One keyword
        InfoContainsKeywordsPredicate predicate = new InfoContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new PersonBuilder().withInfo("Alice Bob").build()));

        // Multiple keywords
        predicate = new InfoContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new PersonBuilder().withInfo("Alice Bob").build()));

        // Only one matching keyword
        predicate = new InfoContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new PersonBuilder().withInfo("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new InfoContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new PersonBuilder().withInfo("Alice Bob").build()));
    }

    @Test
    public void test_infoDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        InfoContainsKeywordsPredicate predicate = new InfoContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withInfo("Alice").build()));

        // Non-matching keyword
        predicate = new InfoContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new PersonBuilder().withInfo("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match Info
        predicate = new InfoContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withInfo("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }
}

