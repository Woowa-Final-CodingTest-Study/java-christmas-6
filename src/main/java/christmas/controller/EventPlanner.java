package christmas.controller;

import christmas.domain.Benefit;
import christmas.domain.MenuType;
import christmas.domain.Reservation;
import christmas.utils.Calculation;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Map;

public class EventPlanner {
    InputView inputView = new InputView();
    OutputView outputView = new OutputView();

    public void start() {
        introducePlanner();
        Reservation reservation = enrollReserveInfo();

        showOrderMenu(reservation.showOrderMenu());

        int totalAmount = reservation.calculateTotalAmount();
        showTotalAmount(Calculation.showMoneyWithUnit(totalAmount));

        Benefit benefit = new Benefit(totalAmount, reservation.getVisitDate(), reservation.getOrderMenu());
        showGift(benefit.showGift());

        showBenefitResult(benefit.showBenefitResult());
        showTotalBenefit(Calculation.showMoneyDiscount(benefit.calculateTotalBenenfit()));
        showPriceAfterDiscount(Calculation.showMoneyWithUnit(benefit.calculatePriceAfterDiscount(totalAmount)));
    }

    public void introducePlanner() {
        outputView.printPlannerIntro();
        outputView.printEventPrecautions();
    }

    public Reservation enrollReserveInfo() {
        int visitDate = enrollVisitDate();
        while (true) {
            try {
                Map<String, Integer> orderMenu = enrollOrderMenu();
                return new Reservation(visitDate, orderMenu);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public int enrollVisitDate() {
        outputView.requestVisitDate();
        return inputView.readVisitDate();
    }

    public Map<String, Integer> enrollOrderMenu() {
        outputView.printMenu(MenuType.showMenu());
        outputView.requestOrderMenu();
        return inputView.readOrderMenu();
    }

    public void showOrderMenu(String orderMenus) {
        outputView.printOrderMenu(orderMenus);
    }

    public void showTotalAmount(String totalAmount) {
        outputView.printTotalAmount(totalAmount);
    }

    public void showGift(String gift) {
        outputView.printGift(gift);
    }

    public void showBenefitResult(String benefitResult) {
        outputView.printBenefitResult(benefitResult);
    }

    public void showTotalBenefit(String totalBenefit) {
        outputView.printTotalBenefit(totalBenefit);
    }

    public void showPriceAfterDiscount(String priceAfterDiscount) {
        outputView.printPriceAfterDiscount(priceAfterDiscount);
    }
}
