package christmas.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Order {
    private final HashMap<MenuBoard, Integer> order;
    private int freeChampagne;
    private int priceBeforeDiscount;
    private int priceAfterDiscount;

    public Order(HashMap<MenuBoard, Integer> order) {
        this.order = order;
    }

    public HashMap<MenuBoard, Integer> getOrder() {
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

    public void applyDiscount(int totalDiscount) {
        priceAfterDiscount = priceBeforeDiscount - totalDiscount;
    }

    public void giveFreeChampagne() {
        freeChampagne++;
    }

    public void calculatePriceBeforeDiscount() {
        int totalPrice = 0;
        for (Map.Entry<MenuBoard, Integer> entry : order.entrySet()) {
            MenuBoard menu = entry.getKey();
            int quantity = entry.getValue();
            totalPrice += menu.getPrice() * quantity;
        }
        priceBeforeDiscount += totalPrice;
    }

    public boolean isForGiveawayEvent() {
        return (priceBeforeDiscount >= 120_000);
    }
}



