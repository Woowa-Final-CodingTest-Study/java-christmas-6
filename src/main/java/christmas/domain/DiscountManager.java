package christmas.domain;

public class DiscountManager {
    private boolean isPriceAvailableForEvent;

    public void checkAvailability(Order order) {
        if (order.isPriceForEvent()) {
            isPriceAvailableForEvent = true;
        }
    }

    public int calculateDdayEventDiscount(DiscountContext context) {
        int DdayEventDiscount = 0;
        Order order = context.getOrder();
        checkAvailability(order);
        DdayEvent ddayEvent = context.getDdayEvent();
        VisitingDate visitingDate = context.getVisitingDate();
        DdayEventDiscount += ddayEvent.applyDdayEvent(visitingDate);
        return DdayEventDiscount;
    }

    public int calculateDailyEventDiscount(DiscountContext context) {
        int dailyEventDiscount = 0;
        Order order = context.getOrder();
        checkAvailability(order);
        DailyEvent dailyEvent = context.getDailyEvent();
        VisitingDate visitingDate = context.getVisitingDate();
        dailyEventDiscount += dailyEvent.applyDailyEvent(visitingDate, order);
        return dailyEventDiscount;
    }


}
