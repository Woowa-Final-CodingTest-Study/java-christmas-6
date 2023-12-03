package christmas.domain.event;

import christmas.domain.MenuBoard;
import christmas.domain.Order;
import christmas.domain.VisitingDate;
import java.util.HashMap;

public class EventManager {
    private boolean isPriceAvailableForEvent;

    public void checkAvailability(Order order) {
        if (order.isPriceForEveryEvents()) {
            isPriceAvailableForEvent = true;
        }
    }

    public int calculateDdayEventDiscount(EventContext context) {
        int DdayEventDiscount = 0;
        Order order = context.getOrder();
        checkAvailability(order);
        DdayEvent ddayEvent = context.getDdayEvent();
        VisitingDate visitingDate = context.getVisitingDate();
        DdayEventDiscount += ddayEvent.applyDdayEvent(visitingDate);
        System.out.println(DdayEventDiscount);
        return DdayEventDiscount;
    }

    public int calculateDailyEventDiscount(EventContext context) {
        int dailyEventDiscount = 0;
        Order order = context.getOrder();
        checkAvailability(order);
        DailyEvent dailyEvent = context.getDailyEvent();
        VisitingDate visitingDate = context.getVisitingDate();
        dailyEventDiscount += dailyEvent.applyDailyEvent(visitingDate, order);
        System.out.println(dailyEventDiscount);
        return dailyEventDiscount;
    }

    public int calculateSpecialEventDiscount(EventContext context) {
        int specialDiscount = 0;
        Order order = context.getOrder();
        checkAvailability(order);
        SpecialEvent specialEvent = context.getSpecialEvent();
        VisitingDate visitingDate = context.getVisitingDate();
        specialDiscount += specialEvent.applySpecialEvent(visitingDate);
        System.out.println(specialDiscount);
        return specialDiscount;
    }

    public int calculateGiveawayEventDiscount(EventContext context) {
        int giveAwayDiscount = 0;
        Order order = context.getOrder();
        GiveawayEvent giveawayEvent = context.getGiveawayEvent();
        checkAvailability(order);
        giveAwayDiscount += giveawayEvent.applyGiveawayEvent(order);
        order.giveFreeChampagne();
        System.out.println(giveAwayDiscount);
        return giveAwayDiscount;
    }
}
