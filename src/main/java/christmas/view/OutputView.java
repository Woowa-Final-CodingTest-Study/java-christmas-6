package christmas.view;

import christmas.constant.OutputMessage;
import christmas.model.Badge;
import christmas.model.Benefit;
import christmas.model.Order;
import christmas.model.VisitDate;

import java.util.List;

import static christmas.util.CustomFormat.currencyFormat;

public class OutputView {
    private static OutputView outputView;

    private OutputView() {
    }

    public static OutputView getInstance() {
        if (outputView == null) {
            outputView = new OutputView();
        }
        return outputView;
    }

    private void printf(String message, Object... args) {
        System.out.printf(message, args);
        System.out.println();
    }

    private void println() {
        System.out.println();
    }

    private void println(String... messages) {
        for (String message : messages)
            System.out.println(message);
    }

    public void notifyInputVisitDate() {
        println(OutputMessage.NOTIFY_INPUT_VISIT_DATE);
    }

    public void notifyInputOrder() {
        println(OutputMessage.NOTIFY_INPUT_ORDER);
    }

    public void printResult(VisitDate visitDate, Order order, Benefit benefit) {
        printVisitDate(visitDate);
        println();

        printOrder(order);
        println();

        int totalCost = order.getTotalCost();
        printTotalCostBeforeDiscount(totalCost);
        printGift(benefit.hasGift());
        println();

        printBenefit(benefit.toStrings());
        println();

        int totalBenefitAmount = benefit.totalBenefitAmount();
        printTotalBenefitAmount(totalBenefitAmount);
        println();

        printExpectedCost(totalCost, benefit.totalDiscount());
        println();

        printBadge(totalBenefitAmount);
    }

    private void printVisitDate(VisitDate visitDate) {
        printf(OutputMessage.PRINT_VISIT_DATE, visitDate.value());
    }

    private void printOrder(Order order) {
        println(OutputMessage.RESULT_TITLE_ORDER);
        order.toStrings().forEach(this::println);
    }

    private void printTotalCostBeforeDiscount(int totalCost) {
        println(OutputMessage.RESULT_TITLE_TOTAL_COST_BEFORE_DISCOUNT);
        println(currencyFormat.format(totalCost));
        println();
    }

    private void printGift(boolean hasGift) {
        println(OutputMessage.RESULT_TITLE_GIFT);
        if(hasGift) {
            println(OutputMessage.RESULT_GIFT_CHAMPAGNE);
            return;
        }
        println(OutputMessage.RESULT_NOTHING);
    }

    private void printBenefit(List<String[]> benefit) {
        println(OutputMessage.RESULT_TITLE_BENEFIT);
        if (benefit.isEmpty()) {
            println(OutputMessage.RESULT_NOTHING);
            return;
        }
        benefit.forEach(e -> printf(OutputMessage.RESULT_BENEFIT, e));
    }

    private void printTotalBenefitAmount(int totalBenefitAmount) {
        println(OutputMessage.RESULT_TITLE_TOTAL_BENEFIT_AMOUNT);
        printf("-" + currencyFormat.format(totalBenefitAmount));
    }

    private void printExpectedCost(int totalCost, int totalDiscount) {
        println(OutputMessage.RESULT_TITLE_EXPECTED_TOTAL_COST_AFTER_DISCOUNT);
        printf(currencyFormat.format(totalCost - totalDiscount));
    }

    private void printBadge(int totalBenefitAmount) {
        println(OutputMessage.RESULT_TITLE_BADGE);
        Badge badge = Badge.of(totalBenefitAmount);
        if (badge.equals(Badge.NOTHING)) {
            println(OutputMessage.RESULT_NOTHING);
            return;
        }
        println(badge.value);
    }
}
