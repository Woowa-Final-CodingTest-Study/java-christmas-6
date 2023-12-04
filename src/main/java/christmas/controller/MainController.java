package christmas.controller;

import christmas.domain.OrderMenuRepository;
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
        OrderMenuRepository orderMenuRepository = askForOrderMenu();

        displayEventHistory(visitDate, orderMenuRepository);
    }

    public int askForVisitDate() {
        outputView.printEventPlanner();
        int visitDate = reservationController.inputVisitDate();

        return visitDate;
    }

    public OrderMenuRepository askForOrderMenu() {
        OrderMenuRepository orderMenuRepository = reservationController.orderReceipt();

        return orderMenuRepository;
    }

    public void displayEventHistory(int visitDate, OrderMenuRepository orderMenuRepository) {
        outputView.printPreviewEventBenefit(visitDate);
        outputView.printOrderMenu(orderMenuRepository);

        eventContentsController.generateEventContents(visitDate, orderMenuRepository);
    }

}