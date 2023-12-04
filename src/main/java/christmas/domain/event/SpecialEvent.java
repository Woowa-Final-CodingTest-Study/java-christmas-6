package christmas.domain.event;

import christmas.domain.VisitingDate;

public class SpecialEvent implements Event{

    @Override
    public int calculateEventDiscount(EventContext context) {
        VisitingDate visitingDate = context.getVisitingDate();
        int discount = 0;
        if (visitingDate.isAvailableForSpecialEvent()) {
            discount -= 1000;
        }
        return discount;    }
}
