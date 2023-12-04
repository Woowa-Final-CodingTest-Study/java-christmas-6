package christmas.domain.discount;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SpecialDiscountTest {

    @DisplayName("별이 포함된 날짜에 방문하면 1_000원 특별 할인 적용")
    @ParameterizedTest
    @CsvSource({"1, 0", "3, 1_000", "25, 1_000", "30, 0"})
    void checkSpecialDiscount(int visitDate, int discount) {
        SpecialDiscount specialDiscount = new SpecialDiscount();
        int result = specialDiscount.calculateSpecialDiscount(visitDate);
        assertThat(result).isEqualTo(discount);
    }
}