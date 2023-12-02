package christmas.domain;

import christmas.constants.ErrorMessage;
import java.util.HashMap;
import java.util.HashSet;

public class Order {
    private HashMap<MenuBoard, Integer> order;

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
        if (!orderIncludesNonBeverageItem()) {
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


    private boolean orderIncludesNonBeverageItem() {
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

    public HashMap<MenuBoard, Integer> getOrder() {
        return this.order;
    }
}
