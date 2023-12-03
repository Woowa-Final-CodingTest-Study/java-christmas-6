package christmas.domain.discount;

import christmas.constant.NumberConstant;

public class DdayDiscount {

    private static final int STANDARD_PRICE = 1_000;
    private static final int DISCOUNT_UNIT = 100;

    public int calculateDdayDiscount(int visitDate) {
        if(visitDate <= NumberConstant.CHRISTMAS_DAY) {
            return STANDARD_PRICE + DISCOUNT_UNIT*(visitDate-1);
        }
        return 0;
    }
}
