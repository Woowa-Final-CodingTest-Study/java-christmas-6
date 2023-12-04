package christmas.constant;

public enum Menu {

    MUSHROOM_SOUP("양송이수프", 6_000),
    TAPAS("타파스", 5_500),
    CAESAR_SALAD("시저샐러드", 8_000),
    T_BONE_STEAK("티본스테이크", 55_000),
    BARBECUE_LIBS("바비큐립", 54_000),
    SEAFOOD_PASTA("해산물파스타", 35_000),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000),
    CHOCOLATE_CAKE("초코케이크", 15_000),
    ICE_CREAM("아이스크림", 5_000),
    ZERO_COLA("제로콜라", 3_000),
    RED_WHINE("레드와인", 60_000),
    CHAMPAGNE("샴페인", 25_000),
    NOTHING("없음", 0);

    private final String menuName;
    private final int price;

    Menu(String menuName, int price) {
        this.menuName = menuName;
        this.price = price;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getPrice() {
        return price;
    }

    public static boolean containMenu(String input) {
        for(Menu menu : Menu.values()) {
            if(input.equals(menu.getMenuName())) {
                return true;
            }
        }
        return false;
    }

    public static int findMenuPrice(String name) {
        for(Menu menu : Menu.values()) {
            if(name.equals(menu.getMenuName())) {
                return menu.price;
            }
        }
        return NOTHING.price;
    }
}
