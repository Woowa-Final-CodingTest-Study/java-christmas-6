package christmas.domain;

import java.util.Collections;
import java.util.List;

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
}
