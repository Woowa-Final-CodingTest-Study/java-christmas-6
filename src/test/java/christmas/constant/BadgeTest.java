package christmas.constant;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BadgeTest {

    @DisplayName("총할인 금액에 따른 배지 반환")
    @ParameterizedTest
    @CsvSource({"24_000, 산타", "13_000, 트리", "7_000, 별", "1_000, 없음"})
    void checkBadge(int totalDiscount, String badge) {
        String result = Badge.findBadge(totalDiscount);
        assertThat(result).isEqualTo(badge);
    }
}