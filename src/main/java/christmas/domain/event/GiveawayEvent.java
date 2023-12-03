package christmas.domain.event;

import christmas.domain.Order;

public class GiveawayEvent {
    public int applyGiveawayEvent(Order order) {
        int discount = 0;
        if (order.isPriceForGiveawayEvent()) {
            discount -= 25_000;
        }
        return discount;
    }
}
