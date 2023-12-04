package christmas.domain;

import java.util.List;

public class Calendar {
    private static final List<Integer> weekend = List.of(
            1, 2,
            8, 9,
            15, 16,
            22, 23,
            29, 30
    );
    private static final List<Integer> starday = List.of(3, 10, 17, 24, 25, 31);

    public static boolean isWeekend(int date) {
        return weekend.contains(date);
    }

    public static boolean isWeekday(int date) {
        return !weekend.contains(date);
    }

    public static boolean isStarday(int date) {
        return starday.contains(date);
    }
}
