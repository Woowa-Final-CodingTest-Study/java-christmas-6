package christmas.controller;

import christmas.domain.OrderMenuRepository;
import christmas.view.InputView;
import christmas.view.OutputView;

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

    public OrderMenuRepository orderReceipt() {
        String order = inputOrder();

        return OrderMenuRepository.createOrderMenu(order);
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
