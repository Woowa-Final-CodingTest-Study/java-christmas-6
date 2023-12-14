package christmas.domain.service;

import christmas.domain.Order;
import christmas.domain.VisitingDate;
import christmas.domain.event.DailyDiscount;
import christmas.domain.event.DdayDiscount;
import christmas.domain.event.GiveawayEvent;
import christmas.domain.event.SpecialDiscount;
import java.util.List;
import javax.naming.event.EventContext;
import jdk.jfr.Event;

public class EventManager {
    private GiveawayEvent giveawayEvent = new GiveawayEvent();
    private SpecialDiscount specialDiscount = new SpecialDiscount();
    private DailyDiscount dailyDiscount = new DailyDiscount();
    private DdayDiscount ddayDiscount = new DdayDiscount();

    public int calculateTotalDiscount(VisitingDate visitingDate, Order order) {
        int totalDiscount = 0;
        totalDiscount += giveawayEvent.calculateGiveawayEvent(order);
        totalDiscount += specialDiscount.calculateSpecialDiscount(visitingDate);
        totalDiscount += dailyDiscount.calculateDailyDiscount(visitingDate, order);
        totalDiscount += ddayDiscount.calculateDdayDiscount(visitingDate);
        return totalDiscount;
    }

}
