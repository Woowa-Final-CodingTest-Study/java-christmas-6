package christmas.domain.event;

import christmas.domain.MenuBoard;
import christmas.domain.MenuType;
import christmas.domain.Order;
import christmas.domain.VisitingDate;
import java.util.HashMap;

public class DailyEvent {
    private static final int MAIN_DISCOUNT = 2023;
    private static final int DESSERT_DISCOUNT = 2023;

    public int applyDailyEvent(VisitingDate visitingDate, Order order) {
        int discount = 0;
        if (visitingDate.isWeekend()) {
            HashMap<MenuBoard, Integer> map = order.getOrder();
            for (MenuBoard menu : map.keySet()) {
                if (menu.getType() == MenuType.MAIN) {
                    int quantity = map.get(menu);
                    discount -= MAIN_DISCOUNT * quantity;
                }
            }
        }
        if (!visitingDate.isWeekend()) {
            HashMap<MenuBoard, Integer> map = order.getOrder();
            for (MenuBoard menu : map.keySet()) {
                if (menu.getType() == MenuType.DESSERT) {
                    int quantity = map.get(menu);
                    discount -= DESSERT_DISCOUNT * quantity;
                }
            }
        }
        return discount;
    }
}
