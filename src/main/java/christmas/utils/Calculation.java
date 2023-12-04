package christmas.utils;

import java.text.DecimalFormat;

public class Calculation {
    public static String showMoneyWithComma(int money) {
        DecimalFormat formatter = new DecimalFormat("###,###");
        return formatter.format(money);
    }

    public static String showMoneyWithUnit(int money) {
        return showMoneyWithComma(money) + "원";
    }

    public static String showMoneyDiscount(int money) {
        return "-" + showMoneyWithComma(money) + "원";
    }
}
