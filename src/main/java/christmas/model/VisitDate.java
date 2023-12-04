package christmas.model;

import java.time.DayOfWeek;
import java.util.List;

public class VisitDate {
    private static final List<DayOfWeek> weekend = List.of(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY);
    private static final List<Integer> specialDate = List.of(3, 10, 17, 24, 25, 31);

    private final int visitDate;

    public VisitDate(int visitDate) {
        this.visitDate = visitDate;
    }

    public boolean isWeekend() {
        // 2023년 12월 1일: 금요일
        // DayOfWeek은 월~일이 1~7에 대응됨
        DayOfWeek dayOfWeek = DayOfWeek.of((visitDate + 3)/7+1);
        return weekend.contains(dayOfWeek);
    }

    public boolean isSpecialDate() {
        return specialDate.contains(visitDate);
    }

    public int value() {
        return visitDate;
    }
}
