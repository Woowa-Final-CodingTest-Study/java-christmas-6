package christmas.view;

import static christmas.constants.GameMessage.EVENT_PREVIEW_MESSAGE;

public class OutputView {
    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printEventPreview(int visitingDay) {
        System.out.printf(EVENT_PREVIEW_MESSAGE, visitingDay);
        printEmptyLine();
        printEmptyLine();
    }

    public static void printPriceBeforeDiscount(int totalPrice) {
        StringBuilder sb = new StringBuilder();
        sb.append("<할인 전 총주문 금액>\n");
        sb.append(totalPrice).append("원");
        printMessage(sb.toString());
    }

    public static void printEmptyLine() {
        System.out.println();
    }
}
