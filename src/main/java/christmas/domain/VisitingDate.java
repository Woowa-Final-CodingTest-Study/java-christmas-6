package christmas.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class VisitingDate {
    private final LocalDate visitingDate;

    public VisitingDate(int date) {
        this.visitingDate = LocalDate.of(2023, 12, date);
    }

    public LocalDate getVisitingDate() {
        return visitingDate;
    }

    public boolean isForSpecialEvent() {
        int dayOfMonth = visitingDate.getDayOfMonth();
        int[] specialEventDays = {3, 10, 17, 24, 25, 31};

        for (int specialDay : specialEventDays) {
            if (dayOfMonth == specialDay) {
                return true;
            }
        }
        return false;
    }

    public boolean isWeekend() {
        DayOfWeek dayOfWeek = visitingDate.getDayOfWeek();
        return (dayOfWeek == DayOfWeek.FRIDAY && dayOfWeek == DayOfWeek.SATURDAY);
    }

    public boolean isBeforeChristmas() {
        LocalDate christmasDate = LocalDate.of(2023, 12, 25);
        return visitingDate.isBefore(christmasDate);
    }


    public int calculateDiscountPriceForDdayEvent() {
        int day = visitingDate.getDayOfMonth();
        return (1000 + (day - 1) * 100);
    }
}
