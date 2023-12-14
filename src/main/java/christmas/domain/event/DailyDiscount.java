package christmas.domain.event;

import christmas.domain.MenuBoard;
import christmas.domain.MenuType;
import christmas.domain.Order;
import christmas.domain.VisitingDate;
import java.util.HashMap;
import java.util.Map;

public class DailyDiscount {
    private static final int MAIN_DISCOUNT = 2023;
    private static final int DESSERT_DISCOUNT = 2023;

    public int calculateDailyDiscount(VisitingDate visitingDate, Order order) {
        if (visitingDate.isWeekend()) {
            return calculateDiscount(order, MenuType.MAIN, MAIN_DISCOUNT);
        }
        // 여기는 주말이 아닐 때 실행됩니다.
        return calculateDiscount(order, MenuType.DESSERT, DESSERT_DISCOUNT);
    }


    private int calculateDiscount(Order order, MenuType menuType, int discountPerItem) {
        int discount = 0;
        if (order.isForAllEvents()) {
            Map<MenuBoard, Integer> map = order.getOrder();
            for (Map.Entry<MenuBoard, Integer> entry : map.entrySet()) {
                if (entry.getKey().getType() == menuType) {
                    discount -= discountPerItem * entry.getValue();
                }
            }
            return discount;
        }
        return discount;
    }

//    public int calculateDailyDiscount(VisitingDate visitingDate, Order order) {
//        int discount = 0;
//        if (visitingDate.isWeekend()) {
//            calculateMainDiscount(order);
//        }
//        if (!visitingDate.isWeekend()) {
//            calculateDessertDiscount(order);
//        }
//        return discount;
//    }
//
//    private int calculateMainDiscount(Order order) {
//        int discount = 0;
//        HashMap<MenuBoard, Integer> map = order.getOrder();
//        for (MenuBoard menu : map.keySet()) {
//            if (menu.getType() == MenuType.MAIN) {
//                int quantity = map.get(menu);
//                discount -= MAIN_DISCOUNT * quantity;
//            }
//        }
//        return discount;
//    }
//
//    private int calculateDessertDiscount(Order order) {
//        int discount = 0;
//        HashMap<MenuBoard, Integer> map = order.getOrder();
//        for (MenuBoard menu : map.keySet()) {
//            if (menu.getType() == MenuType.DESSERT) {
//                int quantity = map.get(menu);
//                discount -= DESSERT_DISCOUNT * quantity;
//            }
//        }
//        return discount;
//    }
}
