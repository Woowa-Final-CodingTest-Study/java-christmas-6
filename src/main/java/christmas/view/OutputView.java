package christmas.view;

public class OutputView {
    public void printPlannerIntro() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printEventPrecautions() {
        System.out.println("************************************[ 이벤트 주의사항 ]************************************");
        System.out.println("* 총주문 금액 10,000원 이상부터 이벤트가 적용됩니다.");
        System.out.println("* 음료만 주문 시, 주문할 수 없습니다.");
        System.out.println("* 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.");
    }

    public void requestVisitDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
    }

    public void printMenu(String menus) {
        System.out.println("\n************************************[ 메뉴판 ]************************************");
        System.out.println(menus);
    }

    public void requestOrderMenu() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
    }
}
