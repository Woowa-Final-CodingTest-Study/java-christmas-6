package christmas.domain.event;

import christmas.domain.VisitingDate;
import java.time.LocalDate;

public class DdayDiscount {

    public int calculateDdayDiscount(VisitingDate visitingDate) {
        int discount = 0;
        if (visitingDate.isBeforeChristmas()) {
            discount += visitingDate.calculateDiscountPriceForDdayEvent();
        }
        return discount;
    }
}
