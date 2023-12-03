package christmas;

import static christmas.constants.GameMessage.ASK_ORDER_MESSAGE;
import static christmas.constants.GameMessage.ASK_VISITING_DATE_MESSAGE;
import static christmas.constants.GameMessage.HELLO_MESSAGE;

import christmas.domain.Order;
import christmas.domain.VisitingDate;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Controller {
    public void init() {
        sayHello();
        VisitingDate visitingDate = enrollVisitingDate();
        Order order = enrollOrder(visitingDate);
    }

    public void sayHello() {
        OutputView.printMessage(HELLO_MESSAGE);
    }

    public VisitingDate enrollVisitingDate() {
        VisitingDate visitingDate;
        do {
            try {
                OutputView.printMessage(ASK_VISITING_DATE_MESSAGE);
                visitingDate = InputView.getVisitingDateInput();
                return visitingDate;
            } catch (IllegalArgumentException e) {
                OutputView.printMessage(e.getMessage());
                visitingDate = null;
            }
        } while (visitingDate == null);
        return visitingDate;
    }

    public Order enrollOrder(VisitingDate visitingDate) {
        Order order;
        do {
            try {
                OutputView.printMessage(ASK_ORDER_MESSAGE);
                order = InputView.getOrderInput();
                int day = visitingDate.getDayOfMonth();
                OutputView.printEventPreview(day);
                OutputView.printMessage(order.getOrderDetails());
                return order;
            } catch (IllegalArgumentException e) {
                OutputView.printMessage(e.getMessage());
                order = null;
            }
        } while (order == null);
        return order;
    }

    public void printPriceBeforeDiscount(Order order, int totalPrice) {
        int priceBeforeDiscount = order.calculateTotalPrice();
        OutputView.printPriceBeforeDiscount(priceBeforeDiscount);
    }
}
