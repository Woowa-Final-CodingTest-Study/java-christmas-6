package christmas.controller;

import christmas.domain.MenuCount;
import christmas.domain.OrderMenu;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationController {

    InputView inputView = new InputView();
    OutputView outputView = new OutputView();

    public int inputVisitDate() {
        try {
            outputView.printVisitDatePrompt();
            int visitDate = inputView.inputUserVisitDate();
            return visitDate;
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println(illegalArgumentException.getMessage());
            return inputVisitDate();
        }
    }

    public OrderMenu orderReceipt() {
        String order = inputOrder();

        return OrderMenu.createOrderMenu(order);
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
