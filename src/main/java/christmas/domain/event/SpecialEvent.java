package christmas.domain.event;

import christmas.domain.VisitingDate;

public class SpecialEvent {
    public int applySpecialEvent(VisitingDate visitingDate) {
        int discount = 0;
        if (visitingDate.isAvailableForSpecialEvent()) {
            discount -= 1000;
        }
        return discount;
    }
}
