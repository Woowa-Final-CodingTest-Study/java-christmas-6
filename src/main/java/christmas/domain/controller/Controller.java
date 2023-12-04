package christmas.domain.controller;

import static christmas.constants.GameMessage.ASK_ORDER_MESSAGE;
import static christmas.constants.GameMessage.ASK_VISITING_DATE_MESSAGE;
import static christmas.constants.GameMessage.HELLO_MESSAGE;

import christmas.domain.event.DailyEvent;
import christmas.domain.event.DdayEvent;
import christmas.domain.event.EventContext;
import christmas.domain.Order;
import christmas.domain.VisitingDate;
import christmas.domain.event.manager.EventManager;
import christmas.domain.event.GiveawayEvent;
import christmas.domain.event.SpecialEvent;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Controller {
    DdayEvent ddayEvent = new DdayEvent();
    DailyEvent dailyEvent = new DailyEvent();
    SpecialEvent specialEvent = new SpecialEvent();
    GiveawayEvent giveawayEvent = new GiveawayEvent();

    public void init() {
        OutputView.printMessage(HELLO_MESSAGE);
        VisitingDate visitingDate = enrollVisitingDate();
        Order order = enrollOrder(visitingDate);
        EventContext context = new EventContext(order, visitingDate, ddayEvent, dailyEvent, specialEvent, giveawayEvent);
        EventManager eventManager = new EventManager(context);
        eventManager.printEventInfo(order, context, visitingDate);

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
}
