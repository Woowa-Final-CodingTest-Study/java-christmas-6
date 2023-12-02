package christmas.domain;

import christmas.constants.ErrorMessage;
import java.util.HashMap;
import java.util.HashSet;

public class Order {
    private HashMap<MenuBoard, Integer> order;

    public Order(HashMap<MenuBoard, Integer> order) {
        this.order = order;
    }

    private void checkOrderValidity() {
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
        HashSet<String> set = new HashSet(order.keySet());
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

    public HashMap<MenuBoard, Integer> getOrder() {
        return this.order;
    }
}
