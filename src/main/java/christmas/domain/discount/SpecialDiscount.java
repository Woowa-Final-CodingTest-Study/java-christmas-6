package christmas.domain.discount;

import christmas.constant.StarDiscount;

public class SpecialDiscount {

    public int calculateSpecialDiscount(int visitDate) {
        return StarDiscount.findBySpecialDiscount(visitDate).getDiscountPrice();
    }
}
