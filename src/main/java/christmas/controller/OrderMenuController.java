package christmas.controller;

import christmas.domain.MenuCount;
import christmas.domain.OrderMenu;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OrderMenuController {

    InputView inputView = new InputView();
    OutputView outputView = new OutputView();

    public OrderMenu orderReceipt() {
        String order = inputOrder();
        List<String> orderMenu = Arrays.stream(order.split(","))
                .collect(Collectors.toList());

        return new OrderMenu(countMenu(orderMenu));
    }

    public List<MenuCount> countMenu(List<String> orderMenu) {
        List<MenuCount> count = new ArrayList<>();
        for(String order : orderMenu) {
            String[] menuAndCount = order.split("-");

            MenuCount menuCount = new MenuCount(menuAndCount[0], Integer.parseInt(menuAndCount[1]));
            count.add(menuCount);
        }

        return count;
    }

    public String inputOrder() {
        try {
            outputView.printOrderPrompt();
            String orderMenu = inputView.inputUserOrder();
            return orderMenu;
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println(illegalArgumentException.getMessage());
            return inputOrder();
        }
    }
}
