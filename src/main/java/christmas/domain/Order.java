package christmas.domain;

import java.util.Map;
import java.util.Map.Entry;

public class Order {
    private final Map<MenuBoard, Integer> order;

    public Order(Map<MenuBoard, Integer> order) {
        this.order = order;
    }

    public Map<MenuBoard, Integer> getOrder() {
        return order;
    }

    public String generateOrderString() {
        StringBuilder orderString = new StringBuilder("<주문 메뉴>\n");
        for (Entry<MenuBoard, Integer> entry : order.entrySet()) {
            MenuBoard menu = entry.getKey();
            Integer quantity = entry.getValue();
            orderString.append(menu.name()).append(" ").append(quantity).append("개\n");
        }
        return orderString.toString();
    }

}

