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
    }
}
