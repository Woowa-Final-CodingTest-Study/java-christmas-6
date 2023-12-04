package christmas.constant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class StarDiscountTest {

    @DisplayName("날짜를 입력하면 해당 날짜가 특별 할인에 해당하는지 확인")
    @ParameterizedTest
    @CsvSource({"1, 없음", "3, 특별 할인", "25, 특별 할인"})
    void checkStarDiscount(int visitDate, String discountCategory) {
        String result = StarDiscount.findBySpecialDiscount(visitDate).getDiscountCategory();
        assertThat(result).isEqualTo(discountCategory);
    }
}