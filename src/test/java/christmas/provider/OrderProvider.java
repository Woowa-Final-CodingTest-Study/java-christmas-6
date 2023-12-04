package christmas.provider;

import christmas.constant.ErrorMessage;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.*;

public class OrderProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                arguments(ErrorMessage.ORDER_INVALID_FORM, "제로콜라,1"),
                arguments(ErrorMessage.ORDER_INVALID_FORM, "제로콜라-1,타파스"),
                arguments(ErrorMessage.MENU_NAME_NOT_EXIST, "없는메뉴-1"),
                arguments(ErrorMessage.MENU_NAME_NOT_EXIST, "제로 콜라-1"),
                arguments(ErrorMessage.MENU_COUNT_NOT_NUMBER, "제로콜라-a"),
                arguments(ErrorMessage.MENU_COUNT_NOT_NUMBER, "제로콜라- "),
                arguments(ErrorMessage.MENU_DUPLICATED, "제로콜라-1,제로콜라-2"),
                arguments(ErrorMessage.MENU_COUNT_OUT_OF_RANGE, "제로콜라-0"),
                arguments(ErrorMessage.ORDER_ONLY_BEVERAGE, "제로콜라-1,샴페인-2"),
                arguments(ErrorMessage.ORDER_TOTAL_COUNT_OUT_OF_RANGE, "제로콜라-15", "타파스-15")
        );
    }
}
