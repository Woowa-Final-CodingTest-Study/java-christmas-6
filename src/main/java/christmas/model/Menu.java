package christmas.model;

import christmas.constant.ErrorMessage;

import java.util.HashSet;
import java.util.Set;

public class Menu {

    private final String name;
    private final Integer price;
    public final Category category;

    private Menu(String name, Integer price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public static final Set<Menu> menuList = new HashSet<>();

    public static void init() {
        if (!menuList.isEmpty())
            throw new IllegalStateException("Menu가 이미 초기화되어 있습니다.");
        menuList.add(new Menu("양송이수프", 6000, Category.APPETIZER));
        menuList.add(new Menu("타파스", 5500, Category.APPETIZER));
        menuList.add(new Menu("시저샐러드", 8000, Category.APPETIZER));
        menuList.add(new Menu("티본스테이크", 55000, Category.MAIN_DISH));
        menuList.add(new Menu("바비큐립", 54000, Category.MAIN_DISH));
        menuList.add(new Menu("해산물파스타", 35000, Category.MAIN_DISH));
        menuList.add(new Menu("크리스마스파스타", 25000, Category.MAIN_DISH));
        menuList.add(new Menu("초코케이크", 15000, Category.DESSERT));
        menuList.add(new Menu("아이스크림", 5000, Category.DESSERT));
        menuList.add(new Menu("제로콜라", 3000, Category.BEVERAGE));
        menuList.add(new Menu("레드와인", 60000, Category.BEVERAGE));
        menuList.add(new Menu("샴페인", 25000, Category.BEVERAGE));
    }

    public static Menu get(String name) {
        return menuList.stream()
                .filter(menu -> menu.name.equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.MENU_NAME_NOT_EXIST));
    }
}
