package christmas.domain.event;

import christmas.domain.Order;
import christmas.domain.VisitingDate;
import java.time.LocalDate;

public class DdayDiscount {

    public int calculateDdayDiscount(VisitingDate visitingDate, Order order) {
        int discount = 0;
        if (order.isForAllEvents()) {
            if (visitingDate.isBeforeChristmas()) {
                discount += visitingDate.calculateDiscountPriceForDdayEvent();
            }
            return discount;
        }
        return discount;
    }
}
