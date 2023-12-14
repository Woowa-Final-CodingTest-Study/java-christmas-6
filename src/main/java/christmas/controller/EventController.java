package christmas.controller;

import christmas.domain.Order;

public class EventController {

    public void run(Order order) {
        enrollEvent(order);
    }

    public void enrollEvent(Order order) {
        if (isForEvent(order)) {

        }
    }

    public boolean isForEvent(Order order) {
        int totalPrice = order.generateTotalPrice();
        return totalPrice >= 10_000;
    }

}
