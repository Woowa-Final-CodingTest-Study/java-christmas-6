package christmas.model;

import christmas.constant.ErrorMessage;

import java.util.HashSet;
import java.util.Set;

public class Menu {

    public final String name;
    public final Integer price;
    public final Category category;

    private Menu(String name, Integer price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public static final Set<Menu> menuList = new HashSet<>();

    public static void init() {
        if (!menuList.isEmpty())
            return;
        add("양송이수프", 6000, Category.APPETIZER);
        add("타파스", 5500, Category.APPETIZER);
        add("시저샐러드", 8000, Category.APPETIZER);
        add("티본스테이크", 55000, Category.MAIN_DISH);
        add("바비큐립", 54000, Category.MAIN_DISH);
        add("해산물파스타", 35000, Category.MAIN_DISH);
        add("크리스마스파스타", 25000, Category.MAIN_DISH);
        add("초코케이크", 15000, Category.DESSERT);
        add("아이스크림", 5000, Category.DESSERT);
        add("제로콜라", 3000, Category.BEVERAGE);
        add("레드와인", 60000, Category.BEVERAGE);
        add("샴페인", 25000, Category.BEVERAGE);
    }

    private static void add(String name, Integer price, Category category) {
        menuList.add(new Menu(name, price, category));
    }

    public static Menu get(String name) {
        return menuList.stream()
                .filter(menu -> menu.name.equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.MENU_NAME_NOT_EXIST));
    }
}
