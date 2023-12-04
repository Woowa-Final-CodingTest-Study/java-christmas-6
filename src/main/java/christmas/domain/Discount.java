package christmas.domain;

import christmas.utils.Calculation;
import java.util.Map;

public class Discount {
    int christmas = 0;
    int weekday = 0;
    int weekend = 0;
    int special = 0;

    Discount(int totalAmount, int visitDate, Map<Menu, Integer> orderMenu) {
        if (totalAmount >= 100000) {
            initChristmasDiscount(visitDate);
            initWeekDiscount(visitDate, orderMenu);
            initSpecialDiscount(visitDate);
        }
    }

    private void initChristmasDiscount(int visitDate) {
        if (visitDate <= 25) {
            this.christmas = (visitDate - 1) * 100 + 1000;
            return;
        }
        this.christmas = 0;
    }

    private void initWeekDiscount(int visitDate, Map<Menu, Integer> orderMenu) {
        if (Calendar.isWeekend(visitDate)) {
            this.weekday = 0;
            this.weekend = Menu.countMainMenu(orderMenu) * 2023;
            return;
        }
        this.weekday = Menu.countDessertMenu(orderMenu) * 2023;
        this.weekend = 0;
    }

    private void initSpecialDiscount(int visitDate) {
        if (Calendar.isStarday(visitDate)) {
            this.special = 1000;
            return;
        }
        this.special = 0;
    }

    public String showDiscount() {
        StringBuilder discountResult = new StringBuilder();
        if (christmas > 0) {
            discountResult.append("크리스마스 디데이 할인: ");
            discountResult.append(Calculation.showMoneyDiscount(christmas));
            discountResult.append("\n");
        }
        if (weekday > 0) {
            discountResult.append("평일 할인: ");
            discountResult.append(Calculation.showMoneyDiscount(weekday));
            discountResult.append("\n");
        }
        if (weekend > 0) {
            discountResult.append("주말 할인: ");
            discountResult.append(Calculation.showMoneyDiscount(weekend));
            discountResult.append("\n");
        }
        if (special > 0) {
            discountResult.append("특별 할인: ");
            discountResult.append(Calculation.showMoneyDiscount(special));
            discountResult.append("\n");
        }
        return discountResult.toString();
    }

    public int calculateTotalDiscount() {
        return christmas + weekday + weekend + special;
    }
}
