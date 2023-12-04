package christmas.domain;

import christmas.utils.Calculation;
import java.util.Map;

public enum Menu {
    MUSHROOM_SOUP("양송이수프", 6000),
    TAPAS("타파스", 5500),
    CAESAR_SALAD("시저샐러드", 8000),
    T_BONE_STREAK("티본스테이크", 55000),
    BARBEQUE_LIP("바비큐립", 54000),
    SEAFOOD_PASTA("해산물파스타", 35000),
    CHRISTMAS_PASTA("크리스마스파스타", 25000),
    CHOCOLATE_CAKE("초코케이크", 15000),
    ICE_CREAM("아이스크림", 5000),
    ZERO_COLA("제로콜라", 3000),
    READ_WINE("레드와인", 60000),
    CHAMPAGNE("샴페인", 25000);

    private final String name;
    private final int price;

    Menu(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }

    public static Menu getMenuByName(String name) {
        for (Menu menu : Menu.values()) {
            if (menu.name.equals(name)) {
                return menu;
            }
        }
        throw new IllegalArgumentException("Invalid menu name: " + name);
    }

    public static String showMenuInfos(Menu menu) {
        return menu.name + "(" + Calculation.showMoneyWithComma(menu.price) + ")";
    }

    public static int countMainMenu(Map<Menu, Integer> orderMenu) {
        int count = 0;
        for (Menu menu : orderMenu.keySet()) {
            if (MenuType.isMain(menu)) {
                count += orderMenu.get(menu);
            }
        }
        return count;
    }

    public static int countDessertMenu(Map<Menu, Integer> orderMenu) {
        int count = 0;
        for (Menu menu : orderMenu.keySet()) {
            if (MenuType.isDessert(menu)) {
                count += orderMenu.get(menu);
            }
        }
        return count;
    }
}
