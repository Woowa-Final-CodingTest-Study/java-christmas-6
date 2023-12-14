package christmas.controller;

import christmas.constants.GameMessage;
import christmas.domain.MenuBoard;
import christmas.domain.Order;
import christmas.domain.VisitingDate;
import christmas.domain.badge.Badge;
import christmas.domain.service.BadgeManager;
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
        printPriceBeforeDiscount(order);

        EventManager eventManager = new EventManager();
        eventManager.calculateDiscounts(visitingDate, order);
        int totalDiscount = eventManager.getTotalDiscount();
        order.applyDiscount(totalDiscount);

        printGiveawayInformation(order);
        printDiscountList(eventManager, visitingDate, order);

        printTotalDiscount(eventManager);
        calculateTotalPriceAfterDiscount(eventManager, order);

        printPriceAfterDiscount(order);

        BadgeManager badgeManager = new BadgeManager();
        badgeManager.grantBadge(order);
        printBadge(badgeManager);
    }

    public VisitingDate registerVisitingDate() {
        outputView.printMessage(GameMessage.ASK_VISITING_DATE_MESSAGE.getMessage());
        int visitingDate = inputView.getVisitingDate();
        return new VisitingDate(visitingDate);
    }

    public Order registerOrder() {
        outputView.printMessage(GameMessage.ASK_ORDER_MESSAGE.getMessage());
        HashMap<MenuBoard, Integer> order = inputView.getOrder();
        return new Order(order);
    }

    public void printPriceBeforeDiscount(Order order) {
        String priceBeforeDiscount = order.givePriceBeforeDiscountMessage();
        outputView.printMessage(priceBeforeDiscount);
    }

    public void printGiveawayInformation(Order order) {
        outputView.printEmptyLine();
        String freeChampagneInfo = order.giveFreeChampagneInfo();
        outputView.printMessage(freeChampagneInfo);
        outputView.printEmptyLine();
    }

    public void printDiscountList(EventManager eventManager, VisitingDate visitingDate, Order order) {
        String discountList = eventManager.giveDiscountList(visitingDate);
        outputView.printMessage(discountList);
    }

    public void printTotalDiscount(EventManager eventManager) {
        String totalDiscount = eventManager.giveTotalDiscount();
        outputView.print(totalDiscount);
    }
    public void calculateTotalPriceAfterDiscount(EventManager eventManager, Order order) {
        int totalDiscount = eventManager.getTotalDiscount();
        order.calculatePriceAfterDiscount(totalDiscount);
    }

    public void printPriceAfterDiscount(Order order) {
        outputView.printEmptyLine();
        String price = order.givePriceAfterDiscountInformation();
        outputView.printMessage(price);
    }

    public void printBadge(BadgeManager badgeManager) {
        String badge = badgeManager.giveBadgeResult();
        outputView.printMessage(badge);
    }
}
