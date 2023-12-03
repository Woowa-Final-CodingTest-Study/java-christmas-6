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
        sb.append(totalPrice).append("원\n");
        printMessage(sb.toString());
    }

    public static void printGiveaway(int count) {
        if (count == 0) {
            printMessage("<증정 메뉴>");
            printMessage("없음");
        }
        if (count >= 1) {
            printMessage("<증정 메뉴>");
            printMessage("샴페인 " + count + "개");
            printEmptyLine();
        }
    }

    public static void printDdayBenefit(int Ddaybenefit) {
        printMessage("<혜택 내역>");
        printMessage("크리스마스 디데이 할인: " + Ddaybenefit);
    }

    public static void printDailyBenefit_Weekday(int dailyBenefit) {
        printMessage("평일 할인: " + dailyBenefit);
    }

    public static void printDailyBenefit_Weekend(int dailyBenefit) {
        printMessage("주말 할인: " + dailyBenefit);
    }

    public static void printSpecialBenefit(int specialBenefit) {
        if (specialBenefit == 0) {
            printMessage("특별 할인: 없음");
        }
        if (specialBenefit < 0) {
            printMessage("특별 할인: " + specialBenefit);
        }
    }

    public static void printGiveawayBenefit(int giveAwayBenefit) {
        printMessage("증정 이벤트: " + giveAwayBenefit);
    }

    public static void printTotalDiscount(int totalDiscount) {
        printMessage("<총혜택 금액>");
        printMessage(totalDiscount + "원");
    }

    public static void printPriceAfterDiscount(int PriceAfterDiscount) {
        printMessage("<할인 후 예상 결제 금액>");
        printMessage(PriceAfterDiscount + "원");
    }

    public static void printEmptyLine() {
        System.out.println();
    }
}
