package christmas.constant;

import java.time.DayOfWeek;
import java.time.LocalDate;

public enum SequenceOfWeek {
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6),
    SUNDAY(7);

    private final int sequenceOfWeek;

    SequenceOfWeek(int sequenceOfWeek) {
        this.sequenceOfWeek = sequenceOfWeek;
    }

    public int getSequenceOfWeek() {
        return sequenceOfWeek;
    }

    public static int calculateSequenceOfDay(int visitDate) {
        LocalDate localDate = LocalDate.of(NumberConstant.EVENT_YEAR, NumberConstant.EVENT_MONTH, visitDate);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();

        return dayOfWeek.getValue();
    }
}
