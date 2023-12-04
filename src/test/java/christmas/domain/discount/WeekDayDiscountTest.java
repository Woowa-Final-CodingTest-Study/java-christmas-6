package christmas.domain.discount;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import christmas.domain.MenuOrder;
import christmas.domain.OrderMenuRepository;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class WeekDayDiscountTest {

    private WeekDayDiscount weekDayDiscount;

    @BeforeEach
    void setUp() {
        weekDayDiscount = new WeekDayDiscount();
    }

    @DisplayName("평일 할인에 포함된 각 메뉴의 할인 가격을 반환")
    @ParameterizedTest
    @CsvSource({"티본스테이크, 0", "바비큐립, 0", "초코케이크, 2023"})
    void checkWeekDayDiscount(String menu, int discount) {
        int result = weekDayDiscount.calculateWeekDayDiscountMenuPrice(menu);
        assertThat(result).isEqualTo(discount);
    }

    @DisplayName("평일 할인에 포함된 메뉴의 할인 가격 총합을 반환")
    @ParameterizedTest
    @MethodSource("provideWeekDayDiscount")
    void checkTotalWeekDayDiscount(List<MenuOrder> menuOrders, int totalWeekDayDiscount) {
        OrderMenuRepository orderMenuRepository = new OrderMenuRepository(menuOrders);
        int result = weekDayDiscount.calculateWeekDayDiscount(orderMenuRepository);
        assertThat(result).isEqualTo(totalWeekDayDiscount);
    }

    public static Stream<Arguments> provideWeekDayDiscount() {
        return Stream.of(
                Arguments.of(Arrays.asList(
                                new MenuOrder("티본스테이크", 2),
                                new MenuOrder("바비큐립", 1),
                                new MenuOrder("초코케이크", 1)),
                        2_023),
                Arguments.of(Arrays.asList(
                                new MenuOrder("티본스테이크", 2),
                                new MenuOrder("제로콜라", 1)),
                        0)
        );
    }

}