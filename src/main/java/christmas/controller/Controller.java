package christmas.controller;

import christmas.constants.GameMessage;
import christmas.domain.MenuBoard;
import christmas.domain.Order;
import christmas.domain.VisitingDate;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Map;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printMessage(GameMessage.GREETINGS_MESSAGE.getMessage());
        VisitingDate visitingDate = registerVisitingDate();
        Order order = registerOrder();

    }

    public VisitingDate registerVisitingDate() {
        outputView.printMessage(GameMessage.ASK_VISITING_DATE_MESSAGE.getMessage());
        int visitingDate = inputView.getVisitingDate();
        return new VisitingDate(visitingDate);
    }

    public Order registerOrder() {
        outputView.printMessage(GameMessage.ASK_ORDER_MESSAGE.getMessage());
        Map<MenuBoard, Integer> order =  inputView.getOrder();
        return new Order(order);
    }
}
