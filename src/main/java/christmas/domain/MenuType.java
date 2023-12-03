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

public class MenuType {
    public final List<Menu> appetizer = Arrays.asList(MUSHROOM_SOUP, TAPAS, CAESAR_SALAD);
    public final List<Menu> main = Arrays.asList(T_BONE_STREAK, BARBEQUE_LIP, SEAFOOD_PASTA, CHRISTMAS_PASTA);
    public final List<Menu> dessert = Arrays.asList(CHOCOLATE_CAKE, ICE_CREAM);
    public final List<Menu> drinks = Arrays.asList(ZERO_COLA, READ_WINE, CHAMPAGNE);

    public boolean isAppetizer(Menu menu) {
        return appetizer.contains(menu);
    }

    public boolean isMain(Menu menu) {
        return main.contains(menu);
    }

    public boolean isDessert(Menu menu) {
        return dessert.contains(menu);
    }

    public boolean isDrinks(Menu menu) {
        return drinks.contains(menu);
    }
}
