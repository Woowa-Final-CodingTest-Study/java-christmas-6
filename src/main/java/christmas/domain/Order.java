package christmas.domain;

import java.util.Map;

public class Order {
    private final Map<MenuBoard, Integer> order;

    public Order(Map<MenuBoard, Integer> order) {
        this.order = order;
        printOrderDetails(order);
    }

    public Map<MenuBoard, Integer> getOrder() {
        return order;
    }

    private void printOrderDetails(Map<MenuBoard, Integer> order) {
        for (Map.Entry<MenuBoard, Integer> entry : order.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + "개");
        }
    }
}

