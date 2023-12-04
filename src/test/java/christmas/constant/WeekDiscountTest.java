package christmas.constant;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WeekDiscountTest {

    @DisplayName("날짜 번호에 따른 해당하는 할인 카테고리를 반환")
    @ParameterizedTest
    @CsvSource({"1, 평일 할인", "5, 주말 할인"})
    void checkDiscountCategory(int sequenceOfWeek, String discountCategory) {
        String result = WeekDiscount.findByWeekDiscount(sequenceOfWeek).getDiscountCategory();
        assertThat(result).isEqualTo(discountCategory);
    }
}