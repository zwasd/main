package seedu.saveit.testutil;

import static seedu.saveit.logic.commands.CommandTestUtil.VALID_AMOUNT_BUS;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_AMOUNT_MRT;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_DAILY_PERIOD;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_END_DATE_BUS;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_END_DATE_MRT;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_INFO_BUS;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_INFO_MRT;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_START_DATE_BUS;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_START_DATE_MRT;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_TAG_TRANSPORT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.saveit.model.Account;
import seedu.saveit.model.expenditure.Repeat;

public class TypicalRepeats {

    public static final Repeat ALICE = new RepeatBuilder().withInfo("Alice Pauline")
            .withStartDate("2019-09-11").withEndDate("2020-09-11").withAmount(3.14)
            .withTag("friends").withPeriod("DAILY").build();
    public static final Repeat BENSON = new RepeatBuilder().withInfo("Benson Meier")
            .withStartDate("2019-09-11").withEndDate("2019-09-13")
            .withAmount(3.14)
            .withTag("owesMoney").withPeriod("DAILY").build();
    public static final Repeat CARL = new RepeatBuilder().withInfo("Carl Kurz")
            .withAmount(3.14).withStartDate("2019-09-11").withEndDate("2020-09-11")
            .withPeriod("MONTHLY").build();
    public static final Repeat DANIEL = new RepeatBuilder().withInfo("Daniel Meier")
            .withAmount(3.14).withStartDate("2019-09-11").withEndDate("2019-09-13").withTag("friends")
            .withPeriod("DAILY").build();
    public static final Repeat ELLE = new RepeatBuilder().withInfo("Elle Meyer")
            .withAmount(3.14).withStartDate("2019-09-11").withEndDate("2019-09-13")
            .withPeriod("DAILY").build();
    public static final Repeat FIONA = new RepeatBuilder().withInfo("Fiona Kunz")
            .withAmount(3.14).withStartDate("2019-09-11").withEndDate("2019-09-13")
            .withPeriod("MONTHLY").build();
    public static final Repeat GEORGE = new RepeatBuilder().withInfo("George Best")
            .withAmount(3.14).withStartDate("2019-09-11").withEndDate("2019-09-13")
            .withPeriod("DAILY").build();

    // Manually added
    public static final Repeat HOON = new RepeatBuilder().withInfo("Hoon Meier")
            .withAmount(3.14).withStartDate("2019-09-11").withEndDate("2019-09-13")
            .withPeriod("DAILY").build();
    public static final Repeat IDA = new RepeatBuilder().withInfo("Ida Mueller")
            .withAmount(3.14).withStartDate("2019-09-11").withEndDate("2019-09-13")
            .withPeriod("DAILY").build();

    // Manually added - Repeat's details found in {@code CommandTestUtil}
    public static final Repeat AMY = new RepeatBuilder().withInfo(VALID_INFO_BUS)
            .withAmount(VALID_AMOUNT_BUS).withStartDate(VALID_START_DATE_BUS)
            .withEndDate(VALID_END_DATE_BUS).withPeriod(VALID_DAILY_PERIOD).build();
    public static final Repeat BOB = new RepeatBuilder().withInfo(VALID_INFO_MRT)
            .withAmount(VALID_AMOUNT_MRT).withStartDate(VALID_START_DATE_MRT)
            .withEndDate(VALID_END_DATE_MRT).withTag(VALID_TAG_TRANSPORT).withPeriod(VALID_DAILY_PERIOD)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalRepeats() {} // prevents instantiation

    /**
     * Returns an {@code Account} with all the typical repeats.
     */
    public static Account getTypicalAccount() {
        Account ab = new Account();
        for (Repeat repeat : getTypicalRepeats()) {
            ab.addRepeat(repeat);
        }
        return ab;
    }

    public static List<Repeat> getTypicalRepeats() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
