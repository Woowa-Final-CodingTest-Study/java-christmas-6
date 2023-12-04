package christmas.domain;

import java.util.Map;
import java.util.stream.Collectors;

public class Reservation {
    private final int visitDate;
    private final Map<Menu, Integer> orderMenu;

    public Reservation(int visitDate, Map<String, Integer> orderMenu) {
        this.visitDate = visitDate;
        this.orderMenu = orderMenu.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> Menu.getMenuByName(entry.getKey()),
                        Map.Entry::getValue));
        validateDrinksExist();
    }

    private void validateDrinksExist() {
        for (Menu menu : this.orderMenu.keySet()) {
            if (!MenuType.isDrinks(menu)) {
                return;
            }
        }
        throw new IllegalArgumentException("error");
    }

    public int getVisitDate() {
        return this.visitDate;
    }

    public Map<Menu, Integer> getOrderMenu() {
        return this.orderMenu;
    }

    public String showOrderMenu() {
        StringBuilder result = new StringBuilder();
        for (Menu menu : orderMenu.keySet()) {
            result.append(menu.getName()).append(" ");
            result.append(orderMenu.get(menu)).append("개\n");
        }
        return result.toString();
    }

    public int calculateTotalAmount() {
        int totalAmount = 0;
        for (Menu menu : orderMenu.keySet()) {
            totalAmount += (menu.getPrice() * orderMenu.get(menu));
        }
        return totalAmount;
    }
}
