package christmas.domain.event;

import christmas.domain.Order;
import christmas.domain.VisitingDate;

public class SpecialDiscount {

    public int calculateSpecialDiscount(VisitingDate visitingDate, Order order) {
        int discount = 0;
        if (order.isForAllEvents()) {
            if (visitingDate.isForSpecialEvent()) {
                discount += 1000;
            }
            return discount;
        }
        return discount;
    }
}
