package christmas.domain;

import christmas.constants.ErrorMessage;
import java.util.HashMap;
import java.util.HashSet;

public class Order {
    private HashMap<MenuBoard, Integer> order;
    private int giveawayChampagne;
    private int priceBeforeDiscount;
    private int priceAfterDiscount;

    public Order(HashMap<MenuBoard, Integer> order) {
        this.order = order;
    }

    public void checkOrderValidity() {
        if (!menuItemsExistInMenuBoard()) {
            throw new IllegalArgumentException(ErrorMessage.invalidOrderError);
        }
        if (!menuItemsAreUnique()) {
            throw new IllegalArgumentException(ErrorMessage.invalidOrderError);
        }
        if (!orderedMenusAreAllBeverage()) {
            throw new IllegalArgumentException(ErrorMessage.invalidOrderError);
        }
        if (!totalQuantityIsTwentyOrLess()) {
            throw new IllegalArgumentException(ErrorMessage.invalidOrderError);
        }
    }

    private boolean menuItemsExistInMenuBoard() {
        for (MenuBoard menu : order.keySet()) {
            if (!menu.contains(menu.name())) {
                return false;
            }
        }
        return true;
    }

    private boolean menuItemsAreUnique() {
        HashSet<String> set = new HashSet<>();
        for (MenuBoard menu : order.keySet()) {
            set.add(menu.name());
        }
        if (set.size() != order.size()) {
            return false;
        }
        return true;
    }

    private boolean orderedMenusAreAllBeverage() {
        int beverageCount = 0;
        for (MenuBoard menu : order.keySet()) {
            if (menu.getType() == MenuType.BEVERAGE) {
                beverageCount++;
            }
        }
        if (beverageCount == order.size()) {
            return false;
        }
        return true;
    }

    private boolean totalQuantityIsTwentyOrLess() {
        int totalQuantity = 0;
        for (Integer quantity : order.values()) {
            totalQuantity += quantity;
        }
        return totalQuantity <= 20;
    }

    public String getOrderDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append("<주문 메뉴>\n");
        for (MenuBoard menu : order.keySet()) {
            sb.append(menu.name()).append(" ").append(order.get(menu)).append("개\n");
        }
        return sb.toString();
    }

    public int calculatePriceBeforeDiscount() {
        priceBeforeDiscount = 0;
        for (MenuBoard menu : order.keySet()) {
            int quantity = order.get(menu);
            int price = menu.getPrice();
            priceBeforeDiscount += price * quantity;
        }
        return priceBeforeDiscount;
    }

    public boolean isPriceForEveryEvents() {
        if (priceBeforeDiscount < 10_000) {
            return false;
        }
        return true;
    }

    public boolean isPriceForGiveawayEvent() {
        if (priceBeforeDiscount >= 120_000) {
            return true;
        }
        return false;
    }

    public void giveFreeChampagne() {
        giveawayChampagne++;
    }

    public HashMap<MenuBoard, Integer> getOrder() {
        return this.order;
    }

    public void applyDiscount(int discount) {
        if (isPriceForGiveawayEvent()) {
            this.priceAfterDiscount = this.priceBeforeDiscount + (discount) + 25000;
        }
        if (!isPriceForGiveawayEvent()) {
            this.priceAfterDiscount = this.priceBeforeDiscount - discount;
        }
    }

    public int getPriceAfterDiscount() {
        return priceAfterDiscount;
    }

    public int getGiveawayChampagneCount() {
        return giveawayChampagne;
    }
}
