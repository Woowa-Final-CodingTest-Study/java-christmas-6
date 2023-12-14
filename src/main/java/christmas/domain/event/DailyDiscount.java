package christmas.domain.event;

import christmas.domain.VisitingDate;

public class DailyDiscount {
    public int calculateDailyDiscount(VisitingDate visitingDate) {
        int discount = 0;
        if (visitingDate.isForSpecialEvent()) {
            discount += 1000;
        }
        return discount;
    }
}
