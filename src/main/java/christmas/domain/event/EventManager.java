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
        DdayEventDiscount += ddayEvent.calculateEventDiscount(context);
        return DdayEventDiscount;
    }

    public int calculateDailyEventDiscount(EventContext context) {
        int dailyEventDiscount = 0;
        Order order = context.getOrder();
        checkAvailability(order);
        DailyEvent dailyEvent = context.getDailyEvent();
        dailyEventDiscount += dailyEvent.calculateEventDiscount(context);
        return dailyEventDiscount;
    }

    public int calculateSpecialEventDiscount(EventContext context) {
        int specialDiscount = 0;
        Order order = context.getOrder();
        checkAvailability(order);
        SpecialEvent specialEvent = context.getSpecialEvent();
        specialDiscount += specialEvent.calculateEventDiscount(context);
        return specialDiscount;
    }

    public int calculateGiveawayEventDiscount(EventContext context) {
        int giveAwayDiscount = 0;
        Order order = context.getOrder();
        GiveawayEvent giveawayEvent = context.getGiveawayEvent();
        checkAvailability(order);
        giveAwayDiscount += giveawayEvent.calculateEventDiscount(context);
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

    public void showBenefits(EventContext context, VisitingDate visitingDate) {
        int ddayBenefit = calculateDdayEventDiscount(context);
        int dailyBenefit = calculateDailyEventDiscount(context);
        int specialBenefit = calculateSpecialEventDiscount(context);
        int giveAwayDiscount = calculateGiveawayEventDiscount(context);

        if (ddayBenefit == 0 && dailyBenefit  == 0 && specialBenefit == 0 && giveAwayDiscount == 0) {
            OutputView.printMessage("<혜택 내역>");
            OutputView.printMessage("없음");
            OutputView.printEmptyLine();
        }
        if (ddayBenefit != 0 || dailyBenefit != 0 || specialBenefit != 0 || giveAwayDiscount != 0) {
            OutputView.printDdayBenefit(ddayBenefit);
            if (visitingDate.isWeekend()) {
                OutputView.printDailyBenefit_Weekend(dailyBenefit);
            }
            if (!visitingDate.isWeekend()) {
                OutputView.printDailyBenefit_Weekday(dailyBenefit);
            }
            OutputView.printSpecialBenefit(specialBenefit);
            OutputView.printGiveawayBenefit(giveAwayDiscount);
            OutputView.printEmptyLine();
        }
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

    public int applyAllEvents(EventManager manager, EventContext context, Order order) {
        int discount = 0;
        discount += manager.calculateDdayEventDiscount(context);
        discount += manager.calculateDailyEventDiscount(context);
        discount += manager.calculateSpecialEventDiscount(context);
        discount += manager.calculateGiveawayEventDiscount(context);
        order.applyDiscount(discount);
        return discount;
    }
}
