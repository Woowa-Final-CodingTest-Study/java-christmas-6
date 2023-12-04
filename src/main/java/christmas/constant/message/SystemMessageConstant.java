package christmas.constant.message;

public class SystemMessageConstant {

    public static final String START_PLANNER = "안녕하세요! 우테코 식당 %d월 이벤트 플래너입니다.\n";
    public static final String INPUT_VISIT_DATE = "%d월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)\n";
    public static final String INPUT_ORDER_MENU = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    public static final String PREVIEW_BENEFIT = "%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";
    public static final String ORDER_MENU = "\n<주문 메뉴>";
    public static final String ORDER_MENU_RECEIPT = "%s %d개\n";
    public static final String TOTAL_PRICE_BEFORE_DISCOUNT = "\n<할인 전 총주문 금액>";
    public static final String PRICE = "%,d원";
    public static final String GIFT_MENU = "\n\n<증정 메뉴>";
    public static final String DDAY_DISCOUNT_CATEGORY = "크리스마스 디데이 할인";
    public static final String BENEFIT_HISTORY = "\n<혜택 내역>";
    public static final String BENEFIT_HISTORY_DISCOUNT = "%s : -%,d원\n";
    public static final String NO_BENEFIT_HISTORY = "없음";
    public static final String GIFT_EVENT = "증정 이벤트";
    public static final String TOTAL_BENEFIT_PRICE = """
            \n<총혜택 금액>
            -%,d원\n
            """;
    public static final String NO_BENEFIT_PRICE = """
            \n<총혜택 금액>
            0원
            """;
    public static final String EXPECT_PAY_AMOUNT = """
            <할인 후 예상 결제 금액>
            %,d원\n
            """;

    public static final String BADGE_EVENT = """
            <%d월 이벤트 배지>
            %s
            """;
}
