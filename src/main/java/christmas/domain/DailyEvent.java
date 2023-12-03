package christmas.domain;

import java.util.HashMap;

public class DailyEvent {
    private static final int MAIN_DISCOUNT = 2300;
    private static final int DESSERT_DISCOUNT = 2300;

    public int applyDailyEvent(VisitingDate visitingDate, Order order) {
        int discount = 0;
        if (visitingDate.isWeekend()) {
            HashMap<MenuBoard, Integer> map = order.getOrder();
            for (MenuBoard menu : map.keySet()) {
                if (menu.getType() == MenuType.MAIN) {
                    int quantity = map.get(menu);
                    discount += MAIN_DISCOUNT * quantity;
                }
            }
        }
        if (!visitingDate.isWeekend()) {
            HashMap<MenuBoard, Integer> map = order.getOrder();
            for (MenuBoard menu : map.keySet()) {
                if (menu.getType() == MenuType.DESSERT) {
                    int quantity = map.get(menu);
                    discount += DESSERT_DISCOUNT * quantity;
                }
            }
        }
        return discount;
    }
}
