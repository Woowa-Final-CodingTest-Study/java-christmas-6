package christmas.domain.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.MenuOrder;
import christmas.domain.OrderMenuRepository;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class WeekendDiscountTest {

    private WeekendDiscount weekendDiscount;

    @BeforeEach
    void setUp() {
        weekendDiscount = new WeekendDiscount();
    }

    @DisplayName("주말 할인에 포함된 각 메뉴의 할인 가격을 반환")
    @ParameterizedTest
    @CsvSource({"티본스테이크, 2023", "바비큐립, 2023", "초코케이크, 0"})
    void checkWeekendDiscount(String menu, int discount) {
        int result = weekendDiscount.calculateWeekendDiscountMenuPrice(menu);
        assertThat(result).isEqualTo(discount);
    }

    @DisplayName("주말 할인에 포함된 메뉴의 할인 가격 총합을 반환")
    @ParameterizedTest
    @MethodSource("provideWeekendDiscount")
    void checkWeekendTotalDiscount(List<MenuOrder> menuOrders, int totalWeekendDiscount) {
        OrderMenuRepository orderMenuRepository = new OrderMenuRepository(menuOrders);
        int result = weekendDiscount.calculateWeekendDiscount(orderMenuRepository);
        assertThat(result).isEqualTo(totalWeekendDiscount);
    }

    public static Stream<Arguments> provideWeekendDiscount() {
        return Stream.of(
                Arguments.of(Arrays.asList(
                        new MenuOrder("티본스테이크", 2),
                        new MenuOrder("바비큐립", 1),
                        new MenuOrder("초코케이크", 1)),
                        6_069),
                Arguments.of(Arrays.asList(
                        new MenuOrder("초코케이크", 2),
                        new MenuOrder("제로콜라", 1)),
                        0)
                );
    }
}