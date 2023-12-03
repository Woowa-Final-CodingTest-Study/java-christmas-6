package christmas.domain;

public class DdayEvent {
    public int applyDdayEvent(VisitingDate visitingDate) {
        int discount = 0;
        if (!visitingDate.isAfterChristmas()) {
            discount = visitingDate.calculateDiscountPriceForDdayEvent();
        }
        return discount;
    }
}
