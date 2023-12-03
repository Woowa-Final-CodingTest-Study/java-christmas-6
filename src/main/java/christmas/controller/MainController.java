package christmas.controller;

import christmas.domain.GiftEvent;
import christmas.domain.OrderMenu;
import christmas.domain.TotalBenefitHistory;
import christmas.view.InputView;
import christmas.view.OutputView;

public class MainController {

    private final OrderMenuController orderMenuController;
    private final EventContentsController eventContentsController;

    public MainController() {
        orderMenuController = new OrderMenuController();
        eventContentsController = new EventContentsController();
    }

    InputView inputView = new InputView();
    OutputView outputView = new OutputView();
    public void run() {
        outputView.printEventPlanner();
        int visitDate = inputVisitDate();
        OrderMenu orderMenu = orderMenuController.orderReceipt();
        outputView.printPreviewEventBenefit(visitDate);
        outputView.printOrderMenu(orderMenu);

        eventContentsController.generateEventContents(visitDate, orderMenu);
    }

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

}