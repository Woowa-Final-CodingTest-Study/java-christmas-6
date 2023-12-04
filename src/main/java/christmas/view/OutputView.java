package christmas.view;

public class OutputView {
    public static void printPlannerIntro() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public static void printEventPrecautions() {
        System.out.println("************************************[ 이벤트 주의사항 ]************************************");
        System.out.println("* 총주문 금액 10,000원 이상부터 이벤트가 적용됩니다.");
        System.out.println("* 음료만 주문 시, 주문할 수 없습니다.");
        System.out.println("* 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.");
    }

    public static void requestVisitDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
    }

    public static void printMenu(String menus) {
        System.out.println("\n************************************[ 메뉴판 ]************************************");
        System.out.println(menus);
    }

    public static void requestOrderMenu() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
    }
}
