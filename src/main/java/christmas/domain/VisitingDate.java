package christmas.domain;

import christmas.constants.Constants;
import christmas.constants.ErrorMessage;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class VisitingDate {
    private final LocalDate visitingDate;

    public VisitingDate(int day) {
        validateVisitingDate(day);
        this.visitingDate = LocalDate.of(2023, 12, day);
    }

    private void validateVisitingDate(int day) {
        if (!isValidDate(day)) {
            throw new IllegalArgumentException(ErrorMessage.invalidDateError);
        }
    }

    private boolean isValidDate(int day) {
        if (day < Constants.START_OF_MONTH || day > Constants.END_OF_MONTH) {
            return false;
        }
        return true;
    }

    public int getDayOfMonth() {
        return visitingDate.getDayOfMonth();
    }

    public int calculateDiscountPriceForDdayEvent() {
        int day = visitingDate.getDayOfMonth();
        return -(1000 + (day - 1) * 100);
    }

    public boolean isAfterChristmas() {
        int day = visitingDate.getDayOfMonth();
        if (day >= Constants.FIRST_DAY_AFTER_CHRISTMAS && day <= Constants.END_OF_MONTH) {
            return true;
        }
        return false;
    }

    public boolean isWeekend() {
        DayOfWeek dayOfWeek = visitingDate.getDayOfWeek();
        if (dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY) {
            return true;
        }
        return false;
    }

    public boolean isAvailableForSpecialEvent() {
        DayOfWeek dayOfWeek = visitingDate.getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SUNDAY || visitingDate.equals(LocalDate.of(2023, 12, 25))) {
            return true;
        }
        return false;
    }
}
