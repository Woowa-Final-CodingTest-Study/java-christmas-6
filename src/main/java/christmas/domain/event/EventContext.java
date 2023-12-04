package christmas.domain.event;

import christmas.domain.Order;
import christmas.domain.VisitingDate;

public class EventContext {
    private final Order order;
    private final VisitingDate visitingDate;
    private final DdayEvent ddayEvent;
    private final DailyEvent dailyEvent;
    private final SpecialEvent specialEvent;
    private final GiveawayEvent giveawayEvent;

    public EventContext(Order order, VisitingDate visitingDate,
                        DdayEvent ddayEvent, DailyEvent dailyEvent,
                        SpecialEvent specialEvent, GiveawayEvent giveawayEvent) {

        this.order = order;
        this.visitingDate = visitingDate;
        this.ddayEvent = ddayEvent;
        this.dailyEvent = dailyEvent;
        this.specialEvent = specialEvent;
        this.giveawayEvent = giveawayEvent;
    }

    public Order getOrder() {
        return order;
    }

    public VisitingDate getVisitingDate() {
        return visitingDate;
    }

    public DdayEvent getDdayEvent() {
        return ddayEvent;
    }

    public DailyEvent getDailyEvent() {
        return dailyEvent;
    }

    public SpecialEvent getSpecialEvent() {
        return specialEvent;
    }

    public GiveawayEvent getGiveawayEvent() {
        return giveawayEvent;
    }
}
