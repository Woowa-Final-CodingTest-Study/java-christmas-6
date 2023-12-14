package christmas.domain;

import java.util.HashMap;

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
        StringBuilder sb = new StringBuilder();
        sb.append("<주문 메뉴>\n");
        for (MenuBoard menu : order.keySet()) {
            sb.append(menu.name()).append(" ").append(order.get(menu)).append("개\n");
        }
        return sb.toString();
    }

    public void applyDiscount(int totalDiscount) {
        priceAfterDiscount = priceBeforeDiscount - totalDiscount;
    }

    public void giveFreeChampagne() {
        freeChampagne++;
    }

    public void calculatePriceBeforeDiscount() {
        int totalPrice = 0;
        for (HashMap.Entry<MenuBoard, Integer> entry : order.entrySet()) {
            MenuBoard menu = entry.getKey();
            int quantity = entry.getValue();
            totalPrice += menu.getPrice() * quantity;
        }
        priceBeforeDiscount += totalPrice;
    }

    public boolean isForGiveawayEvent() {
        return (priceBeforeDiscount >= 120_000);
    }

    public int getFreeChampagne() {
        return freeChampagne;
    }

    public String givePriceBeforeDiscountMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("<할인 전 총주문 금액>\n");
        sb.append(String.format("%,d원", priceBeforeDiscount));
        return sb.toString();
    }


    public int getPriceAfterDiscount() {
        return priceAfterDiscount;
    }
}



