package christmas.constant;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum WeekDiscount {
    WEEKDAY_DISCOUNT("평일 할인", MenuCategory.DESSERT.name(), 2023,
            Arrays.asList(SequenceOfWeek.MONDAY.getSequenceOfWeek(), SequenceOfWeek.TUESDAY.getSequenceOfWeek(),
                    SequenceOfWeek.WEDNESDAY.getSequenceOfWeek(), SequenceOfWeek.THURSDAY.getSequenceOfWeek(),
                    SequenceOfWeek.SUNDAY.getSequenceOfWeek())),
    WEEKEND_DISCOUNT("주말 할인", MenuCategory.MAIN.name(), 2023,
            Arrays.asList(SequenceOfWeek.FRIDAY.getSequenceOfWeek(), SequenceOfWeek.SATURDAY.getSequenceOfWeek())),
    EMPTY("없음", MenuCategory.EMPTY.name(), 0, Collections.emptyList());

    private final String discountCategory;
    private final String discountMenuCategory;
    private final int discountPrice;
    private final List<Integer> sequenceOfWeek;

    WeekDiscount(String discountCategory, String discountMenuCategory, int discountPrice,
                 List<Integer> sequenceOfWeek) {
        this.discountCategory = discountCategory;
        this.discountMenuCategory = discountMenuCategory;
        this.discountPrice = discountPrice;
        this.sequenceOfWeek = sequenceOfWeek;
    }

    public String getDiscountCategory() {
        return discountCategory;
    }

    public String getDiscountMenuCategory() {
        return discountMenuCategory;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public static WeekDiscount findByWeekDiscount(int sequenceOfWeek) {
        return Arrays.stream(WeekDiscount.values())
                .filter(weekDiscount -> weekDiscount.hasSequenceOfWeek(sequenceOfWeek))
                .findAny()
                .orElse(EMPTY);
    }

    public boolean hasSequenceOfWeek(int sequence) {
        return sequenceOfWeek.stream()
                .anyMatch(number -> number == sequence);
    }
}
