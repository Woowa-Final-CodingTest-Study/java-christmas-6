package christmas.domain;

import static christmas.domain.Menu.BARBEQUE_LIP;
import static christmas.domain.Menu.CAESAR_SALAD;
import static christmas.domain.Menu.CHAMPAGNE;
import static christmas.domain.Menu.CHOCOLATE_CAKE;
import static christmas.domain.Menu.CHRISTMAS_PASTA;
import static christmas.domain.Menu.ICE_CREAM;
import static christmas.domain.Menu.MUSHROOM_SOUP;
import static christmas.domain.Menu.READ_WINE;
import static christmas.domain.Menu.SEAFOOD_PASTA;
import static christmas.domain.Menu.TAPAS;
import static christmas.domain.Menu.T_BONE_STREAK;
import static christmas.domain.Menu.ZERO_COLA;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum MenuType {
    APPETIZER("애피타이저", Arrays.asList(MUSHROOM_SOUP, TAPAS, CAESAR_SALAD)),
    MAIN("메인", Arrays.asList(T_BONE_STREAK, BARBEQUE_LIP, SEAFOOD_PASTA, CHRISTMAS_PASTA)),
    DESSERT("디저트", Arrays.asList(CHOCOLATE_CAKE, ICE_CREAM)),
    DRINKS("음료", Arrays.asList(ZERO_COLA, READ_WINE, CHAMPAGNE));

    private String type;
    private List<Menu> menus;

    MenuType(String type, List<Menu> menus) {
        this.type = type;
        this.menus = menus;
    }

    public static boolean isAppetizer(Menu menu) {
        return APPETIZER.menus.contains(menu);
    }

    public static boolean isMain(Menu menu) {
        return MAIN.menus.contains(menu);
    }

    public static boolean isDessert(Menu menu) {
        return DESSERT.menus.contains(menu);
    }

    public static boolean isDrinks(Menu menu) {
        return DRINKS.menus.contains(menu);
    }

    public static String showMenu() {
        return Stream.of(values())
                .flatMap(menuType -> Stream.of(
                        "<" + menuType.type + ">",
                        menuType.menus.stream().map(Menu::showMenuInfos)
                                .collect(Collectors.joining(", ")), "\n")
                )
                .collect(Collectors.joining("\n"));
    }
}
