package christmas.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import christmas.constant.StarDiscount;
import christmas.constant.WeekDiscount;
import christmas.constant.message.SystemMessageConstant;
import christmas.domain.DiscountHistory;
import christmas.domain.MenuOrder;
import christmas.domain.OrderMenuRepository;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.internal.bytebuddy.implementation.bind.annotation.Argument;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @DisplayName("메뉴의 종류와 개수가 주어지면 그 메뉴의 가격합을 반환")
    @ParameterizedTest
    @MethodSource("provideMenuPrice")
    void checkMenuPrice(MenuOrder menuOrder, int price) {
        int result = calculator.calculateMenuPrice(menuOrder);
        assertThat(result).isEqualTo(price);
    }

    public static Stream<Arguments> provideMenuPrice() {
        return Stream.of(
                Arguments.of(new MenuOrder("타파스", 3), 16_500),
                Arguments.of(new MenuOrder("티본스테이크", 2), 110_000),
                Arguments.of(new MenuOrder("초코케이크", 3), 45_000)
        );
    }

    @DisplayName("주문한 메뉴 가격의 총합을 반환")
    @ParameterizedTest
    @MethodSource("provideTotalMenuPrice")
    void checkTotalPrice(List<MenuOrder> order, int totalPrice) {
        OrderMenuRepository orderMenuRepository = new OrderMenuRepository(order);
        int result = calculator.calculateTotalPrice(orderMenuRepository);
        assertThat(result).isEqualTo(totalPrice);
    }

    public static Stream<Arguments> provideTotalMenuPrice() {
        return Stream.of(
                Arguments.of(Arrays.asList(
                        new MenuOrder("타파스", 2),
                        new MenuOrder("제로콜라", 1)),
                        14_000
                ),
                Arguments.of(Arrays.asList(
                        new MenuOrder("티본스테이크", 1),
                        new MenuOrder("바비큐립", 1),
                        new MenuOrder("초코케이크", 2),
                        new MenuOrder("제로콜라", 1)),
                        142_000
                )
        );
    }

    @DisplayName("할인 받은 총혜택 금액을 반환")
    @ParameterizedTest
    @MethodSource("provideTotalDiscountMenu")
    void checkTotalDiscount(List<DiscountHistory> discountHistories, int totalDiscount) {
        int result = calculator.calculateTotalDiscount(discountHistories);
        assertThat(result).isEqualTo(totalDiscount);
    }

    public static Stream<Arguments> provideTotalDiscountMenu() {
        return Stream.of(
                Arguments.of(Arrays.asList(
                        new DiscountHistory(SystemMessageConstant.DDAY_DISCOUNT_CATEGORY, 1_200),
                        new DiscountHistory(WeekDiscount.WEEKDAY_DISCOUNT.getDiscountCategory(), 4_046),
                        new DiscountHistory(StarDiscount.SPECIAL_DISCOUNT.getDiscountCategory(), 1_000),
                        new DiscountHistory(SystemMessageConstant.GIFT_EVENT, 25_000)),
                        31_246
                ), Arguments.of(Collections.emptyList(), 0)
                );
    }

    @DisplayName("할인 후 예상 결제 금액 반환")
    @ParameterizedTest
    @MethodSource("provideTotalPay")
    void checkTotalPay(List<DiscountHistory> discountHistories, int totalPrice, int totalPay) {
        int result = calculator.calculateTotalPay(totalPrice, discountHistories);
        assertThat(result).isEqualTo(totalPay);
    }

    public static Stream<Arguments> provideTotalPay() {
        return Stream.of(
                Arguments.of(Arrays.asList(
                                new DiscountHistory(SystemMessageConstant.DDAY_DISCOUNT_CATEGORY, 1_200),
                                new DiscountHistory(WeekDiscount.WEEKDAY_DISCOUNT.getDiscountCategory(), 4_046),
                                new DiscountHistory(StarDiscount.SPECIAL_DISCOUNT.getDiscountCategory(), 1_000),
                                new DiscountHistory(SystemMessageConstant.GIFT_EVENT, 25_000)),
                        142_000, 135_754
                ), Arguments.of(Collections.emptyList(), 8_500, 8_500)
        );
    }
}