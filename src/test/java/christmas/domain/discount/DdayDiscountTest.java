package christmas.domain.discount;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DdayDiscountTest {

    @DisplayName("크리스마스 디데이 할인 적용 확인(25일까지만 적용)")
    @ParameterizedTest
    @CsvSource({"1, 1_000", "10, 1_900", "25, 3_400", "31, 0"})
    void checkDdayDiscount(int visitDate, int discount) {
        DdayDiscount ddayDiscount = new DdayDiscount();
        int result = ddayDiscount.calculateDdayDiscount(visitDate);
        assertThat(result).isEqualTo(discount);
    }
}