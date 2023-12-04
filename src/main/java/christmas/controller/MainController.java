package christmas.controller;

import christmas.domain.OrderMenu;
import christmas.view.InputView;
import christmas.view.OutputView;

public class MainController {

    private final ReservationController reservationController;
    private final EventContentsController eventContentsController;

    OutputView outputView = new OutputView();

    public MainController() {
        reservationController = new ReservationController();
        eventContentsController = new EventContentsController();
    }

    public void run() {
        int visitDate = askForVisitDate();
        OrderMenu orderMenu = askForOrderMenu();

        displayEventHistory(visitDate, orderMenu);
    }

    public int askForVisitDate() {
        outputView.printEventPlanner();
        int visitDate = reservationController.inputVisitDate();

        return visitDate;
    }

    public OrderMenu askForOrderMenu() {
        OrderMenu orderMenu = reservationController.orderReceipt();

        return orderMenu;
    }

    public void displayEventHistory(int visitDate, OrderMenu orderMenu) {
        outputView.printPreviewEventBenefit(visitDate);
        outputView.printOrderMenu(orderMenu);

        eventContentsController.generateEventContents(visitDate, orderMenu);
    }

}