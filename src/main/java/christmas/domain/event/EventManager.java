package christmas.domain.event;

import christmas.domain.MenuBoard;
import christmas.domain.Order;
import christmas.domain.VisitingDate;
import christmas.view.OutputView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventManager {
    private final List<Event> events;

    public EventManager(EventContext context) {
        this.events = createEventList(context);
    }

    private List<Event> createEventList(EventContext context) {
        List<Event> events = new ArrayList<>();
        events.add(context.getDdayEvent());
        events.add(context.getDailyEvent());
        events.add(context.getSpecialEvent());
        events.add(context.getGiveawayEvent());
        return events;
    }

    public void printEventInfo(Order order, EventContext context, VisitingDate visitingDate) {
        showPriceBeforeDiscount(order);
        int totalDiscount = applyAllEvents(order,context);
        showGiveaway(order);
        showBenefits(context, visitingDate);
        showTotalDiscount(totalDiscount);
        showPriceAfterDiscount(order);
    }

    private static void showPriceBeforeDiscount(Order order) {
        int priceBeforeDiscount = order.calculatePriceBeforeDiscount();
        OutputView.printPriceBeforeDiscount(priceBeforeDiscount);
    }

    private int applyAllEvents(Order order, EventContext context) {
        int discount = 0;
        for (Event event : events) {
            discount += event.calculateEventDiscount(context);
        }
        order.applyDiscount(discount);
        return discount;
    }

    private static void showGiveaway(Order order) {
        int count = order.getGiveawayChampagneCount();
        OutputView.printGiveaway(count);
    }

    private void showBenefits(EventContext context, VisitingDate visitingDate) {
        int ddayBenefit = context.getDdayEvent().calculateEventDiscount(context);
        int dailyBenefit = context.getDailyEvent().calculateEventDiscount(context);
        int specialBenefit = context.getSpecialEvent().calculateEventDiscount(context);
        int giveAwayDiscount = context.getGiveawayEvent().calculateEventDiscount(context);

        if (ddayBenefit == 0 && dailyBenefit == 0 && specialBenefit == 0 && giveAwayDiscount == 0) {
            OutputView.printBenefitList_noBenefit();
        }
        if (ddayBenefit != 0 || dailyBenefit != 0 || specialBenefit != 0 || giveAwayDiscount != 0) {
            OutputView.showAllBenefits(ddayBenefit, dailyBenefit, specialBenefit, giveAwayDiscount, visitingDate);
        }
    }

    private static void showTotalDiscount(int totalDiscount) {
        OutputView.printTotalDiscount(totalDiscount);
    }

    private static void showPriceAfterDiscount(Order order) {
        int priceAfterDiscount = order.getPriceAfterDiscount();
        OutputView.printPriceAfterDiscount(priceAfterDiscount);
    }
}
