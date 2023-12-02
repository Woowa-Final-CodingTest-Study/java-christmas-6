package christmas.domain;

import java.time.LocalDate;

public class VisitingDate {
    private final LocalDate visitingDate;

    public VisitingDate(int day) {
        this.visitingDate = LocalDate.of(2023, 12, day);
    }
}
