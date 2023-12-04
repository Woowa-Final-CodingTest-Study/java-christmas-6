package christmas.domain.event;

import christmas.domain.MenuBoard;
import christmas.domain.MenuType;
import christmas.domain.Order;
import christmas.domain.VisitingDate;
import java.util.HashMap;

public class DailyEvent implements Event {
    private static final int MAIN_DISCOUNT = 2023;
    private static final int DESSERT_DISCOUNT = 2023;

    @Override
    public int calculateEventDiscount(EventContext context) {
        VisitingDate visitingDate = context.getVisitingDate();
        Order order = context.getOrder();
        int discount = 0;

        if (!order.isPriceForEveryEvents()) {
            return discount;
        }

        if (visitingDate.isWeekend()) {
            discount = calculateMainDiscount(order);
        }

        if (!visitingDate.isWeekend()) {
            discount = calculateDessertDiscount(order);
        }

        return discount;
    }

    private int calculateMainDiscount(Order order) {
        int discount = 0;
        HashMap<MenuBoard, Integer> map = order.getOrder();
        for (MenuBoard menu : map.keySet()) {
            if (menu.getType() == MenuType.MAIN) {
                int quantity = map.get(menu);
                discount -= MAIN_DISCOUNT * quantity;
            }
        }
        return discount;
    }

    private int calculateDessertDiscount(Order order) {
        int discount = 0;
        HashMap<MenuBoard, Integer> map = order.getOrder();
        for (MenuBoard menu : map.keySet()) {
            if (menu.getType() == MenuType.DESSERT) {
                int quantity = map.get(menu);
                discount -= DESSERT_DISCOUNT * quantity;
            }
        }
        return discount;
    }
}

