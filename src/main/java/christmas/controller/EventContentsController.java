package christmas.controller;

import christmas.constant.Badge;
import christmas.constant.Menu;
import christmas.constant.NumberConstant;
import christmas.domain.DiscountHistory;
import christmas.domain.OrderMenuRepository;
import christmas.service.Calculator;
import christmas.view.OutputView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EventContentsController {
    private final TotalPriceController totalPriceController;
    private final GiftEventController giftEventController;
    private final DiscountController discountController;

    OutputView outputView = new OutputView();
    Calculator calculator = new Calculator();

    public EventContentsController() {
        totalPriceController = new TotalPriceController();
        giftEventController = new GiftEventController();
        discountController = new DiscountController();
    }

    public void generateEventContents(int visitDate, OrderMenuRepository orderMenuRepository) {
        int totalPrice = getTotalPriceBeforeDiscount(orderMenuRepository);
        String gift = getGiftEventHistory(totalPrice);
        List<DiscountHistory> discountHistories = getTotalBenefitHistory(visitDate, orderMenuRepository, gift);

        checkResetDiscountHistory(totalPrice);

        generateTotalDiscount(discountHistories);
        generateTotalPay(totalPrice, discountHistories);
        generateBadgeEvent(discountHistories);
    }

    public void checkResetDiscountHistory(int totalPrice) {
        if(!isEligibleEvent(totalPrice)) {
            List<DiscountHistory> discountHistories = new ArrayList<>();
            discountHistories.add(new DiscountHistory(Menu.NOTHING.getMenuName(), Menu.NOTHING.getPrice()));
            outputView.printNoBenefitHistory();
        }
    }

    public boolean isEligibleEvent(int totalPrice) {

        return totalPrice >= NumberConstant.MIN_EVENT_PRICE;
    }

    public int getTotalPriceBeforeDiscount(OrderMenuRepository orderMenuRepository) {

        return totalPriceController.getTotalPrice(orderMenuRepository);
    }

    public String getGiftEventHistory(int totalPrice) {

        return giftEventController.getGift(totalPrice);
    }

    public List<DiscountHistory> getTotalBenefitHistory(int visitDate, OrderMenuRepository orderMenuRepository, String gift) {
        outputView.printTotalBenefitHistory();
        int totalPrice = calculator.calculateTotalPrice(orderMenuRepository);

        if(isEligibleEvent(totalPrice)) {
            return discountController.generateTotalBenefitHistory(visitDate, orderMenuRepository, gift);
        }
        return Collections.emptyList();
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
