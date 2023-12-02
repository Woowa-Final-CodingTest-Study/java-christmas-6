package christmas.domain;

import christmas.constants.ErrorMessage;
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
        if (day < 1 || day > 31) {
            return false;
        }
        return true;
    }
}
