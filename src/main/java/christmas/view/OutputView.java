package christmas.view;

import static christmas.constants.GameMessage.EVENT_PREVIEW_MESSAGE;

import christmas.domain.VisitingDate;
import java.text.NumberFormat;

public class OutputView {
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
            OutputView.printEmptyLine();
        }
        if (count >= 1) {
            printMessage("<증정 메뉴>");
            printMessage("샴페인 " + count + "개");
            printEmptyLine();
        }
    }

    public static void printBenefitList_noBenefit() {
        printMessage("<혜택 내역>");
        printMessage("없음");
        OutputView.printEmptyLine();
    }

    public static void showAllBenefits(int ddayBenefit, int dailyBenefit, int specialBenefit, int giveAwayDiscount,
                                       VisitingDate visitingDate) {
        OutputView.printDdayBenefit(ddayBenefit);
        OutputView.showDdayBenefit(visitingDate, dailyBenefit);
        OutputView.printSpecialBenefit(specialBenefit);
        OutputView.printGiveawayBenefit(giveAwayDiscount);
        OutputView.printEmptyLine();
    }

    public static void printDdayBenefit(int Ddaybenefit) {
        NumberFormat formatter = NumberFormat.getInstance();
        String formattedBenefit = formatter.format(Ddaybenefit);
        printMessage("<혜택 내역>");
        printMessage("크리스마스 디데이 할인: " + formattedBenefit);
    }

    public static void showDdayBenefit(VisitingDate visitingDate, int dailyBenefit) {
        if (visitingDate.isWeekend()) {
            printDailyBenefit_Weekend(dailyBenefit);
        }
        if (!visitingDate.isWeekend()) {
            printDailyBenefit_Weekday(dailyBenefit);
        }
    }

    public static void printDailyBenefit_Weekend(int dailyBenefit) {
        NumberFormat formatter = NumberFormat.getInstance();
        String formattedBenefit = formatter.format(dailyBenefit);
        printMessage("주말 할인: " + formattedBenefit + "원");
    }

    public static void printDailyBenefit_Weekday(int dailyBenefit) {
        NumberFormat formatter = NumberFormat.getInstance();
        String formattedBenefit = formatter.format(dailyBenefit);
        printMessage("평일 할인: " + formattedBenefit + "원");
    }

    public static void printSpecialBenefit(int specialBenefit) {
        if (specialBenefit == 0) {
            printMessage("특별 할인: 없음");
        }
        if (specialBenefit < 0) {
            NumberFormat formatter = NumberFormat.getInstance();
            String formattedBenefit = formatter.format(specialBenefit);
            printMessage("특별 할인: " + formattedBenefit + "원");
        }
    }

    public static void printGiveawayBenefit(int giveAwayBenefit) {
        NumberFormat formatter = NumberFormat.getInstance();
        String formattedBenefit = formatter.format(giveAwayBenefit);
        printMessage("증정 이벤트: " + formattedBenefit + "원");
    }

    public static void printTotalDiscount(int totalDiscount) {
        NumberFormat formatter = NumberFormat.getInstance();
        String formattedBenefit = formatter.format(totalDiscount);
        printMessage("<총혜택 금액>");
        printMessage(formattedBenefit + "원");
        OutputView.printEmptyLine();
    }

    public static void printPriceAfterDiscount(int PriceAfterDiscount) {
        NumberFormat formatter = NumberFormat.getInstance();
        String formattedBenefit = formatter.format(PriceAfterDiscount);
        printMessage("<할인 후 예상 결제 금액>");
        printMessage(formattedBenefit + "원");
        OutputView.printEmptyLine();
    }

    public static void printBadge(String badge) {
        printMessage("<12월 이벤트 배지>");
        printMessage(badge);
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printEmptyLine() {
        System.out.println();
    }
}
