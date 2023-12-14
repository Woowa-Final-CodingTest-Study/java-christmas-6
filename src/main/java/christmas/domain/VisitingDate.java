package christmas.domain;

import java.time.LocalDate;

import java.time.LocalDate;

public class VisitingDate {
    private final LocalDate visitingDate;

    public VisitingDate(int date) {
        this.visitingDate = LocalDate.of(2023, 12, date);
    }

    public LocalDate getVisitingDate() {
        return visitingDate;
    }
}
