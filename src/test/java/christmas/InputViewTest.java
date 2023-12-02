package christmas;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import christmas.view.InputView;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class InputViewTest {

    @ParameterizedTest
    @ValueSource(strings = {"25", "1", "30"})
    void should_PassValidation_When_VisitingDateInputIsValid(String validInput) {
        assertDoesNotThrow(() -> InputView.validateVisitngDateInput(validInput));
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "05", "abc", " ", "", "1 2"})
    void should_ThrowException_When_VisitingDateInputIsInValid(String invalidInput) {
        assertThrows(IllegalArgumentException.class, () -> InputView.validateVisitngDateInput(invalidInput));
    }

    @ParameterizedTest
    @ValueSource(strings = {"양송이수프-2,바비큐립-1", "양송이수프-1"})
    void should_PassValidation_When_OrderInputIsValid(String validInput) {
        assertDoesNotThrow(() -> InputView.validateOrderInput(validInput));
    }

    @ParameterizedTest
    @ValueSource(strings = {"양송이수프-1, 바비큐립-1"," 양송이수프-1,바비큐립-1", "양송이수프-1 / 바비큐립-1", "양송이수프-1,바비큐립-1,"})
    void should_ThrowException_When_OrderInputIsInValid(String invalidInput) {
        assertThrows(IllegalArgumentException.class, () -> InputView.validateVisitngDateInput(invalidInput));
    }

}
