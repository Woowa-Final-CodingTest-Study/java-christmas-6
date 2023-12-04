package christmas;

import christmas.domain.MenuBoard;
import christmas.domain.MenuType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MenuBoardTest {
    @Test
    public void getTypeTest() {
        assertEquals(MenuType.APPETIZER, MenuBoard.양송이수프.getType());
        assertEquals(MenuType.MAIN, MenuBoard.티본스테이크.getType());
        assertEquals(MenuType.DESSERT, MenuBoard.초코케이크.getType());
        assertEquals(MenuType.BEVERAGE, MenuBoard.제로콜라.getType());
    }

    @Test
    public void getPriceTest() {
        assertEquals(6_000, MenuBoard.양송이수프.getPrice());
        assertEquals(55_000, MenuBoard.티본스테이크.getPrice());
        assertEquals(15_000, MenuBoard.초코케이크.getPrice());
        assertEquals(3_000, MenuBoard.제로콜라.getPrice());
    }
}
