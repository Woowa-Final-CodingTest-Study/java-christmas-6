package christmas.domain;

public class DiscountContext {
    private final Order order;
    private final VisitingDate visitingDate;
    private final DdayEvent ddayEvent;
    private final DailyEvent dailyEvent;

    public DiscountContext(Order order, VisitingDate visitingDate, DdayEvent ddayEvent, DailyEvent dailyEvent) {
        this.order = order;
        this.visitingDate = visitingDate;
        this.ddayEvent = ddayEvent;
        this.dailyEvent = dailyEvent;
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
}
