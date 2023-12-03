package christmas.domain.event;

import christmas.domain.MenuBoard;
import christmas.domain.Order;
import christmas.domain.VisitingDate;
import christmas.view.OutputView;
import java.util.HashMap;

public class EventManager {
    private boolean isPriceAvailableForEvent;

    public void checkAvailability(Order order) {
        if (order.isPriceForEveryEvents()) {
            isPriceAvailableForEvent = true;
        }
    }

    public int calculateDdayEventDiscount(EventContext context) {
        int DdayEventDiscount = 0;
        Order order = context.getOrder();
        checkAvailability(order);
        DdayEvent ddayEvent = context.getDdayEvent();
        VisitingDate visitingDate = context.getVisitingDate();
        DdayEventDiscount += ddayEvent.applyDdayEvent(visitingDate);
        return DdayEventDiscount;
    }

    public int calculateDailyEventDiscount(EventContext context) {
        int dailyEventDiscount = 0;
        Order order = context.getOrder();
        checkAvailability(order);
        DailyEvent dailyEvent = context.getDailyEvent();
        VisitingDate visitingDate = context.getVisitingDate();
        dailyEventDiscount += dailyEvent.applyDailyEvent(visitingDate, order);
        return dailyEventDiscount;
    }

    public int calculateSpecialEventDiscount(EventContext context) {
        int specialDiscount = 0;
        Order order = context.getOrder();
        checkAvailability(order);
        SpecialEvent specialEvent = context.getSpecialEvent();
        VisitingDate visitingDate = context.getVisitingDate();
        specialDiscount += specialEvent.applySpecialEvent(visitingDate);
        return specialDiscount;
    }

    public int calculateGiveawayEventDiscount(EventContext context) {
        int giveAwayDiscount = 0;
        Order order = context.getOrder();
        GiveawayEvent giveawayEvent = context.getGiveawayEvent();
        checkAvailability(order);
        giveAwayDiscount += giveawayEvent.applyGiveawayEvent(order);
        if (giveAwayDiscount > 0) {
            order.giveFreeChampagne();
        }
        return giveAwayDiscount;
    }

    public void printGiveaway(Order order) {
        int count = order.giveawayChampagneCount();
        OutputView.printGiveaway(count);
        OutputView.printEmptyLine();
    }

    public void showDdayBenefit(EventContext context) {
        int ddayBenefit = calculateDdayEventDiscount(context);
        OutputView.printDdayBenefit(ddayBenefit);
    }

    public void showDailyBenefit(VisitingDate visitingDate, EventContext context) {
        int dailyBenefit = calculateDailyEventDiscount(context);
        if (visitingDate.isWeekend()) {
            OutputView.printDailyBenefit_Weekend(dailyBenefit);
        }
        if (!visitingDate.isWeekend()) {
            OutputView.printDailyBenefit_Weekday(dailyBenefit);
        }
    }

    public void showSpecialBenefit(EventContext context) {
        int specialBenefit = calculateSpecialEventDiscount(context);
        OutputView.printSpecialBenefit(specialBenefit);
    }

    public void showGiveawayBenefit(EventContext context) {
        int giveAwayDiscount = calculateGiveawayEventDiscount(context);
        OutputView.printGiveawayBenefit(giveAwayDiscount);
        OutputView.printEmptyLine();
    }

    public void showTotalDiscount(int totalDiscount) {
        OutputView.printTotalDiscount(totalDiscount);
        OutputView.printEmptyLine();
    }

    public void showPriceAfterDiscount(Order order) {
        int priceAfterDiscount = order.getPriceAfterDiscount();
        OutputView.printPriceAfterDiscount(priceAfterDiscount);
        OutputView.printEmptyLine();
    }
}
