package christmas.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class OrderMenu {
    private final List<MenuCount> orderMenu;

    public OrderMenu(List<MenuCount> orderMenu) {
        this.orderMenu = Collections.unmodifiableList(orderMenu);
    }

    public List<MenuCount> getOrderMenu() {
        return orderMenu;
    }

    public int getSize() {
        return orderMenu.size();
    }

    public static OrderMenu createOrderMenu(String order) {
        List<String> reserveOrderMenu = Arrays.stream(order.split(","))
                .collect(Collectors.toList());
        List<MenuCount> orderMenu = countMenu(reserveOrderMenu);
        return new OrderMenu(orderMenu);
    }

    public static List<MenuCount> countMenu(List<String> orderMenu) {
        List<MenuCount> count = new ArrayList<>();
        for(String order : orderMenu) {
            String[] menuAndCount = order.split("-");

            MenuCount menuCount = new MenuCount(menuAndCount[0], Integer.parseInt(menuAndCount[1]));
            count.add(menuCount);
        }
        return count;
    }
}
