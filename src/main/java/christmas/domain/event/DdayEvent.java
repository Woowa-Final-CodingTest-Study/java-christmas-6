package christmas.domain.event;

import christmas.domain.Order;
import christmas.domain.VisitingDate;

public class DdayEvent implements Event {
    @Override
    public int calculateEventDiscount(EventContext context) {
        VisitingDate visitingDate = context.getVisitingDate();
        Order order = context.getOrder();
        int discount = 0;
        if (order.isPriceForEveryEvents()) {
            if (!visitingDate.isAfterChristmas()) {
                discount = visitingDate.calculateDiscountPriceForDdayEvent();
                return discount;
            }
        }
        return 0;
    }
}
