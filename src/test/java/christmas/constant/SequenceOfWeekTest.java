package christmas.constant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SequenceOfWeekTest {

    @DisplayName("날짜를 입력하면 해당 날짜가 그 주의 몇번째 해당하는 날짜인지 숫자로 반환")
    @ParameterizedTest
    @CsvSource({"1, 5", "2, 6", "3, 7", "4, 1", "5, 2", "6, 3", "7, 4"})
    void checkSequenceOfWeek(int visitDate, int sequenceOfWeek) {
        int result = SequenceOfWeek.calculateSequenceOfDay(visitDate);
        assertThat(result).isEqualTo(sequenceOfWeek);
    }
}