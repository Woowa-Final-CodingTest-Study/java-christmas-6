package christmas.model;

import java.util.Map;

public class Order {
    private final Map<Menu, Integer> order;

    public Order(Map<Menu, Integer> order) {
        this.order = order;
    }
}
