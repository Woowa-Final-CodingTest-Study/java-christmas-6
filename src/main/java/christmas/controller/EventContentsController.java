package christmas.controller;

import christmas.constant.Badge;
import christmas.constant.Menu;
import christmas.constant.NumberConstant;
import christmas.domain.DiscountHistory;
import christmas.domain.OrderMenu;
import christmas.service.Calculator;
import christmas.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class EventContentsController {
    private final TotalPriceController totalPriceController;
    private final GiftEventController giftEventController;
    private final DiscountController discountController;

    public EventContentsController() {
        totalPriceController = new TotalPriceController();
        giftEventController = new GiftEventController();
        discountController = new DiscountController();
    }

    OutputView outputView = new OutputView();
    Calculator calculator = new Calculator();

    public void generateEventContents(int visitDate, OrderMenu orderMenu) {
        int totalPrice = getTotalPriceBeforeDiscount(orderMenu);
        String gift = getGiftEventHistory(totalPrice);
        List<DiscountHistory> discountHistories = getTotalBenefitHistory(visitDate, orderMenu, gift);

        if(totalPrice < NumberConstant.MIN_EVENT_PRICE) {
            discountHistories.removeAll(discountHistories);
            discountHistories.add(new DiscountHistory(Menu.NOTHING.getMenuName(), Menu.NOTHING.getPrice()));
        }

        generateTotalPay(totalPrice, discountHistories);
        generateBadgeEvent(discountHistories);
    }

    public int getTotalPriceBeforeDiscount(OrderMenu orderMenu) {

        return totalPriceController.getTotalPrice(orderMenu);
    }

    public String getGiftEventHistory(int totalPrice) {

        return giftEventController.getGift(totalPrice);
    }

    public List<DiscountHistory> getTotalBenefitHistory(int visitDate, OrderMenu orderMenu, String gift) {
        outputView.printTotalBenefitHistory();
        int totalPrice = calculator.calculateTotalPrice(orderMenu);

        if(totalPrice >= NumberConstant.MIN_EVENT_PRICE) {
            List<DiscountHistory> discountHistories = discountController.generateTotalBenefitHistory(visitDate, orderMenu, gift);
            generateTotalDiscount(discountHistories);

            return discountHistories;
        }
        outputView.printNoBenefitHistory();
        List<DiscountHistory> discountHistories = new ArrayList<>();
        generateTotalDiscount(discountHistories);
        discountHistories.add(new DiscountHistory(Menu.NOTHING.getMenuName(), Menu.NOTHING.getPrice()));
        return discountHistories;
    }

    public void generateTotalDiscount(List<DiscountHistory> discountHistories) {
        int totalDiscount = calculator.calculateTotalDiscount(discountHistories);
        outputView.printTotalBenefitDiscount(totalDiscount);
    }

    public void generateTotalPay(int totalPrice, List<DiscountHistory> discountHistories) {
        int totalPay = calculator.calculateTotalPay(totalPrice, discountHistories);
        outputView.printExpectedPayAmount(totalPay);
    }

    public void generateBadgeEvent(List<DiscountHistory> discountHistories) {
        int totalDiscount = calculator.calculateTotalDiscount(discountHistories);

        String badge = Badge.findBadge(totalDiscount);

        outputView.printBadgeEvent(badge);
    }
}
