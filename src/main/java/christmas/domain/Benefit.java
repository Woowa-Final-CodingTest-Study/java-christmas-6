package christmas.domain;

import java.util.Map;

public class Benefit {
    Gift gift;
    Discount discount;

    public Benefit(int totalAmount, int visitDate, Map<Menu, Integer> orderMenu) {
        this.gift = new Gift(totalAmount);
        this.discount = new Discount(totalAmount, visitDate, orderMenu);
    }

    public String showGift() {
        return this.gift.showGift();
    }

    public String showBenefitResult() {
        String discountResult = discount.showDiscount();
        String giftResult = gift.showDiscount();
        String totalResult = discountResult + giftResult;
        if (totalResult.isEmpty()) {
            return "없음";
        }
        return totalResult;
    }

    public int calculateTotalBenenfit() {
        return discount.calculateTotalDiscount() + gift.getPrice();
    }

    public int calculatePriceAfterDiscount(int totalAmount) {
        return totalAmount - discount.calculateTotalDiscount();
    }
}
