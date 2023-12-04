package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GiftEventTest {

    @DisplayName("할인 전 총주문 금액이 120,000원 이상인 경우 샴페인 반환 120,000원보다 작은 경우 없음 반환")
    @ParameterizedTest
    @CsvSource({"120_000, 샴페인", "8_500, 없음"})
    void checkGift(int totalPrice, String gift) {
        GiftEvent giftEvent = new GiftEvent();
        String result = giftEvent.judgeGiftEvent(totalPrice);
        assertThat(result).isEqualTo(gift);
    }
}