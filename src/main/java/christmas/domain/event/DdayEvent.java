package christmas.domain.event;

import christmas.domain.VisitingDate;

public class DdayEvent implements Event{
    @Override
    public int calculateEventDiscount(EventContext context) {
        VisitingDate visitingDate = context.getVisitingDate();
        int discount = 0;
        if (!visitingDate.isAfterChristmas()) {
            discount = visitingDate.calculateDiscountPriceForDdayEvent();
        }
        return discount;
    }
}
