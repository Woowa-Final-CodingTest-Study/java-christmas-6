package christmas.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Order {
    private final Map<Menu, Integer> order;

    public Order(Map<Menu, Integer> order) {
        this.order = order;
    }

    public List<String> toStrings() {
        ArrayList<String> strings = new ArrayList<>();
        for (Map.Entry<Menu, Integer> e : order.entrySet()) {
             strings.add(e.getKey().name + " " + e.getValue() + "개");
        }
        return strings;
    }

    public int getTotalCost() {
        return order.entrySet().stream()
                .map(e -> e.getKey().price * e.getValue())
                .reduce(0, Integer::sum);
    }

    public int getCountOf(Category category) {
        return order.entrySet().stream()
                .filter(e -> e.getKey().category.equals(category))
                .map(Map.Entry::getValue)
                .reduce(0, Integer::sum);
    }
}
