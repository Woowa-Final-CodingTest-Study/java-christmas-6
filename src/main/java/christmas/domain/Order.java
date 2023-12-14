package christmas.domain;

import java.util.Map;

public class Order {
    private final Map<MenuBoard, Integer> order;

    public Order(Map<MenuBoard, Integer> order) {
        this.order = order;
    }

    public Map<MenuBoard, Integer> getOrder() {
        return order;
    }
}

