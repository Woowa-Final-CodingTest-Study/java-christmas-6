package christmas.constant;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum StarDiscount {
    SPECIAL_DISCOUNT("특별 할인", 1000, Arrays.asList(3, 10, 17, 24, 25, 31)),
    EMPTY("없음", 0, Collections.EMPTY_LIST);

    private final String discountCategory;
    private final int discountPrice;
    private final List<Integer> discountDay;

    StarDiscount(String discountCategory, int discountPrice, List<Integer> discountDay) {
        this.discountCategory = discountCategory;
        this.discountPrice = discountPrice;
        this.discountDay = discountDay;
    }

    public String getDiscountCategory() {
        return discountCategory;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public List<Integer> getDiscountDay() {
        return discountDay;
    }

    public static StarDiscount findBySpecialDiscount(int visitDate) {
        return Arrays.stream(StarDiscount.values())
                .filter(date -> date.hasStar(visitDate))
                .findAny()
                .orElse(EMPTY);
    }

    public boolean hasStar(int visitDate) {
        return discountDay.stream()
                .anyMatch(date -> date==visitDate);
    }
}
