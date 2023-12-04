package christmas.constant;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum MenuCategory {
    APPETIZER(
            Arrays.asList(Menu.MUSHROOM_SOUP.getMenuName(), Menu.TAPAS.getMenuName(), Menu.CAESAR_SALAD.getMenuName())),
    MAIN(Arrays.asList(Menu.T_BONE_STEAK.getMenuName(), Menu.BARBECUE_LIBS.getMenuName(),
            Menu.SEAFOOD_PASTA.getMenuName(),
            Menu.CHRISTMAS_PASTA.getMenuName())),
    DESSERT(Arrays.asList(Menu.CHOCOLATE_CAKE.getMenuName(), Menu.ICE_CREAM.getMenuName())),
    BEVERAGE(Arrays.asList(Menu.ZERO_COLA.getMenuName(), Menu.RED_WHINE.getMenuName(), Menu.CHAMPAGNE.getMenuName())),
    EMPTY(Collections.emptyList());

    private final List<String> menu;

    MenuCategory(List<String> menu) {
        this.menu = menu;
    }

    public static MenuCategory findByMenuCategory(String menuName) {
        return Arrays.stream(MenuCategory.values())
                .filter(menuCategory -> menuCategory.hasMenu(menuName))
                .findAny()
                .orElse(EMPTY);
    }

    public boolean hasMenu(String menuName) {
        return menu.stream()
                .anyMatch(name -> name.equals(menuName));
    }

    public static List<String> getMenuInCategory(MenuCategory menuCategory) {
        return menuCategory.getMenu();
    }

    public List<String> getMenu() {
        return menu;
    }
}
