package christmas.utils;

import java.text.DecimalFormat;

public class Calculation {
    public static String showMoneyWithComma(int money) {
        DecimalFormat formatter = new DecimalFormat("###,###");
        return formatter.format(money);
    }
}
