package christmas.domain;

import static christmas.domain.MenuType.APPETIZER;
import static christmas.domain.MenuType.BEVERAGE;
import static christmas.domain.MenuType.DESSERT;
import static christmas.domain.MenuType.MAIN;

public enum MenuBoard {
    양송이수프(APPETIZER, 6_000), 타파스(APPETIZER, 5_500), 시저샐러드(APPETIZER, 8_000),
    티본스테이크(MAIN, 55_000), 바비큐립(MAIN, 54_000), 해산물파스타(MAIN, 35_000), 크리스마스파스타(MAIN, 25_000),
    초코케이크(DESSERT, 15_000), 아이스크림(DESSERT, 5_000),
    제로콜라(BEVERAGE, 3_000), 레드와인(BEVERAGE, 60_000), 샴페인(BEVERAGE, 25_000);

    private final MenuType type;
    private final int price;

    MenuBoard(MenuType type, int price) {
        this.type = type;
        this.price = price;
    }

    public MenuType getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }
}
