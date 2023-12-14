package christmas.domain.event;

import christmas.domain.Order;

public class GiveawayEvent {
    public int calculateGiveawayEvent(Order order) {
        int discount = 0;
        if (order.isForAllEvents()) {
            if (order.isForGiveawayEvent()) {
                order.giveFreeChampagne();
                discount -= 25_000;
            }
            return discount;
        }
        return discount;
    }
}
