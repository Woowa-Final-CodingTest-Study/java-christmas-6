package christmas;

import christmas.domain.MenuBoard;
import christmas.domain.Order;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {
    @Test
    void should_Pass_When_MenuItemsExistInMenuBoard() {
        HashMap<MenuBoard, Integer> orderMap = new HashMap<>();
        orderMap.put(MenuBoard.타파스, 1);
        Order order = new Order(orderMap);
        assertDoesNotThrow(() -> order.checkOrderValidity());
    }

    @Test
    void should_Pass_When_MenuItemsAreUnique() {
        HashMap<MenuBoard, Integer> orderMap = new HashMap<>();
        orderMap.put(MenuBoard.타파스, 1);
        orderMap.put(MenuBoard.샴페인, 2);
        Order order = new Order(orderMap);
        assertDoesNotThrow(() -> order.checkOrderValidity());
    }

    @Test
    void should_Pass_When_OrderIncludesNonBeverageItem() {
        HashMap<MenuBoard, Integer> orderMap = new HashMap<>();
        orderMap.put(MenuBoard.타파스, 1); // '타파스' is a non-beverage item
        Order order = new Order(orderMap);
        assertDoesNotThrow(() -> order.checkOrderValidity());
    }

    @Test
    void should_Fail_When_OrderOnlyIncludeNonBeverageItem() {
        HashMap<MenuBoard, Integer> orderMap = new HashMap<>();
        orderMap.put(MenuBoard.샴페인, 15);
        orderMap.put(MenuBoard.레드와인, 2);
        Order order = new Order(orderMap);
        assertThrows(IllegalArgumentException.class, () -> order.checkOrderValidity());
    }

    @Test
    void should_Pass_When_TotalQuantityIsTwentyOrLess() {
        HashMap<MenuBoard, Integer> orderMap = new HashMap<>();
        orderMap.put(MenuBoard.타파스, 1);
        orderMap.put(MenuBoard.샴페인, 19);
        Order order = new Order(orderMap);
        assertDoesNotThrow(() -> order.checkOrderValidity());
    }

    @Test
    void should_Fail_When_TotalQuantityIsMoreThanTwenty() {
        HashMap<MenuBoard, Integer> orderMap = new HashMap<>();
        orderMap.put(MenuBoard.타파스, 1);
        orderMap.put(MenuBoard.샴페인, 20); // total quantity is 21
        Order order = new Order(orderMap);
        assertThrows(IllegalArgumentException.class, () -> order.checkOrderValidity());
    }
}
