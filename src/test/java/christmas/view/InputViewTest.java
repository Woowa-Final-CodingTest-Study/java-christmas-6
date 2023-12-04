package christmas.view;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import com.sun.nio.sctp.IllegalReceiveException;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class InputViewTest {

    private InputView inputView;

    @BeforeEach
    void setUp() {
        inputView = new InputView();
    }

    @DisplayName("아무값도 입력하지 않은 경우 에러 발생")
    @Test
    void checkNull() {
        String input = "";
        assertThatThrownBy(() -> inputView.validateNull(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("날짜 입력시 숫자가 아닌 값을 입력한 경우 에러 발생")
    @ParameterizedTest
    @ValueSource(strings = {"가나다", "12일"})
    void checkNumeric(String input) {
        assertThatThrownBy(() -> inputView.validateNumeric(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("입력한 날짜가 1보다 작거나 해당 월의 마지막 날보다 큰 경우 에러 발생")
    @ParameterizedTest
    @ValueSource(ints = {0, 32})
    void checkOutOfDate(int input) {
        assertThatThrownBy(() -> inputView.validateDate(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴 입력 시 입력 예시에서 사용한 구분자를 사용하지 않은 경우 에러 발생")
    @ParameterizedTest
    @ValueSource(strings = {"타파스-1/제로콜라-1", "타파스:1,제로콜라:1"})
    void checkCorrectSeparator(String input) {
        assertThatThrownBy(() -> inputView.validateCorrectForm(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴판에 없는 메뉴를 입력시 에러 발샹")
    @Test
    void checkContainMenu() {
        String[] input = {"타파스", "바질페스토스파게티", "제로콜라"};
        assertThatThrownBy(() -> inputView.validateContainMenu(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("중복되는 메뉴를 입력시 에러 발생")
    @Test
    void checkDuplicateMenu() {
        String[] input = {"타파스", "타파스", "제로콜라"};
        assertThatThrownBy(() -> inputView.validateDuplicateMenu(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("음료만 주문한 경우 에러 발생")
    @Test
    void checkOnlyOrderBeverage() {
        String input = "제로콜라-1,레드와인-1,샴페인-1";
        assertThatThrownBy(() -> inputView.validateOnlyBeverage(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문한 메뉴의 개수를 0보다 작거나 20보다 큰 수로 입력한 경우 에러 발생")
    @ParameterizedTest
    @MethodSource("provideOrderCount")
    void checkOrderCount(String[] input) {
        assertThatThrownBy(() -> inputView.validateOrderCount(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    public static Stream<Arguments> provideOrderCount() {
        return Stream.of(
                Arguments.of((Object) new String[]{"0, 1"}),
                Arguments.of((Object) new String[]{"21, 1"})
        );
    }

    @DisplayName("주문한 메뉴 개수의 합이 20을 넘어가는 경우 에러 발생")
    @Test
    void checkOrderCountSum() {
        String[] orderCount = {"1", "2", "13", "5"};
        assertThatThrownBy(() -> inputView.validateOrderCountSum(orderCount))
                .isInstanceOf(IllegalArgumentException.class);
    }
}