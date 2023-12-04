package christmas.constant;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MenuTest {

    @DisplayName("메뉴가 메뉴판에 포함되어 있는지 확인")
    @ParameterizedTest
    @CsvSource({"타파스, true", "바질페스토스파게티, false"})
    void checkMenuContain(String menu, boolean checkContainMenu) {
        boolean result = Menu.containMenu(menu);
        assertThat(result).isEqualTo(checkContainMenu);
    }

    @DisplayName("해당 메뉴의 가격을 반환")
    @ParameterizedTest
    @CsvSource({"타파스, 5_500", "티본스테이크, 55_000", "제로콜라, 3_000"})
    void checkMenuPrice(String menu, int menuPrice) {
        int result = Menu.findMenuPrice(menu);
        assertThat(result).isEqualTo(menuPrice);
    }
}