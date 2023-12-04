package christmas.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class OrderMenuRepository {
    private final List<MenuOrder> orderMenu;

    public OrderMenuRepository(List<MenuOrder> orderMenu) {
        this.orderMenu = Collections.unmodifiableList(orderMenu);
    }

    public List<MenuOrder> getOrderMenu() {
        return orderMenu;
    }

    public int getSize() {
        return orderMenu.size();
    }

    public static OrderMenuRepository createOrderMenu(String order) {
        List<String> reserveOrderMenu = Arrays.stream(order.split(","))
                .collect(Collectors.toList());
        List<MenuOrder> orderMenu = countMenu(reserveOrderMenu);
        return new OrderMenuRepository(orderMenu);
    }

    public static List<MenuOrder> countMenu(List<String> orderMenu) {
        List<MenuOrder> count = new ArrayList<>();
        for(String order : orderMenu) {
            String[] menuAndCount = order.split("-");

            MenuOrder menuOrder = new MenuOrder(menuAndCount[0], Integer.parseInt(menuAndCount[1]));
            count.add(menuOrder);
        }
        return count;
    }
}
