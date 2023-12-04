package christmas.domain;

import christmas.utils.Calculation;

public class Gift {
    int price;

    Gift(int totalAmount) {
        if (totalAmount >= 120000) {
            price = Menu.CHAMPAGNE.getPrice();
            return;
        }
        price = 0;
    }

    public int getPrice() {
        return this.price;
    }

    public String showGift() {
        if (price == 0) {
            return "없음";
        }
        return Menu.CHAMPAGNE.getName() + " 1개";
    }

    public String showDiscount() {
        if (price != 0) {
            return "증정 이벤트: " + Calculation.showMoneyDiscount(price) + "\n";
        }
        return "";
    }
}
