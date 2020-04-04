package seedu.saveit.testutil;

import static seedu.saveit.logic.commands.CommandTestUtil.VALID_AMOUNT_BUS;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_AMOUNT_MRT;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_DATE_BUS;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_DATE_MRT;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_INFO_BUS;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_INFO_MRT;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_TAG_BUS;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.saveit.model.Account;
import seedu.saveit.model.expenditure.Expenditure;

/**
 * A utility class containing a list of {@code Expenditure} objects to be used in tests.
 */
public class TypicalExpenditures {

    public static final Expenditure ALICE = new ExpenditureBuilder().withInfo("Alice Pauline")
            .withDate("2019-09-11").withAmount(3.14)
            .withTag("friends").build();
    public static final Expenditure BENSON = new ExpenditureBuilder().withInfo("Benson Meier")
            .withDate("2019-09-11")
            .withAmount(3.14)
            .withTag("owesMoney").build();
    public static final Expenditure CARL = new ExpenditureBuilder().withInfo("Carl Kurz")
            .withAmount(3.14).withDate("2019-09-11").build();
    public static final Expenditure DANIEL = new ExpenditureBuilder().withInfo("Daniel Meier")
            .withAmount(3.14).withDate("2019-09-11").withTag("friends").build();
    public static final Expenditure ELLE = new ExpenditureBuilder().withInfo("Elle Meyer")
            .withAmount(3.14).withDate("2019-09-11").build();
    public static final Expenditure FIONA = new ExpenditureBuilder().withInfo("Fiona Kunz")
            .withAmount(3.14).withDate("2019-09-11").build();
    public static final Expenditure GEORGE = new ExpenditureBuilder().withInfo("George Best")
            .withAmount(3.14).withDate("2019-09-11").build();

    // Manually added
    public static final Expenditure HOON = new ExpenditureBuilder().withInfo("Hoon Meier")
            .withAmount(3.14).withDate("2019-09-11").build();
    public static final Expenditure IDA = new ExpenditureBuilder().withInfo("Ida Mueller")
            .withAmount(3.14).withDate("2019-09-11").build();

    // Manually added - Expenditure's details found in {@code CommandTestUtil}
    public static final Expenditure AMY = new ExpenditureBuilder().withInfo(VALID_INFO_BUS)
            .withAmount(VALID_AMOUNT_BUS).withDate(VALID_DATE_BUS).build();
    public static final Expenditure BOB = new ExpenditureBuilder().withInfo(VALID_INFO_MRT)
            .withAmount(VALID_AMOUNT_MRT).withDate(VALID_DATE_MRT).withTag(VALID_TAG_BUS)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalExpenditures() {} // prevents instantiation

    /**
     * Returns an {@code Account} with all the typical expenditures.
     */
    public static Account getTypicalAccount() {
        Account ab = new Account();
        for (Expenditure expenditure : getTypicalExpenditures()) {
            ab.addExpenditure(expenditure);
        }
        return ab;
    }

    public static List<Expenditure> getTypicalExpenditures() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
