package christmas.domain.event;

import christmas.domain.Order;

public class GiveawayEvent implements Event{
    @Override
    public int calculateEventDiscount(EventContext context) {
        Order order = context.getOrder();
        int discount = 0;
        if (order.isPriceForEveryEvents()) {
            if (order.isPriceForGiveawayEvent()) {
                order.giveFreeChampagne();
                discount -= 25_000;
            }
            return discount;
        }
        return 0;
    }
}
