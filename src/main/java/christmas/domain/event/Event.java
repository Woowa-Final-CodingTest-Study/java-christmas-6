package christmas.domain.event;

public interface Event {
    int calculateEventDiscount(EventContext context);
}
