package christmas.controller;

import christmas.constants.GameMessage;
import christmas.domain.MenuBoard;
import christmas.domain.Order;
import christmas.domain.VisitingDate;
import christmas.domain.service.EventManager;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.HashMap;
import java.util.Map;

public class MainController {
    private final InputView inputView;
    private final OutputView outputView;


    public MainController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printMessage(GameMessage.GREETINGS_MESSAGE.getMessage());
        VisitingDate visitingDate = registerVisitingDate();
        Order order = registerOrder();
        outputView.printMessage(order.generateOrderString());
        order.calculatePriceBeforeDiscount();
        EventManager eventManager = new EventManager();
        int totalDiscount = eventManager.calculateTotalDiscount(visitingDate, order);
        order.applyDiscount(totalDiscount);

    }

    public VisitingDate registerVisitingDate() {
        outputView.printMessage(GameMessage.ASK_VISITING_DATE_MESSAGE.getMessage());
        int visitingDate = inputView.getVisitingDate();
        return new VisitingDate(visitingDate);
    }

    public Order registerOrder() {
        outputView.printMessage(GameMessage.ASK_ORDER_MESSAGE.getMessage());
        HashMap<MenuBoard, Integer> order =  inputView.getOrder();
        return new Order(order);
    }
}
