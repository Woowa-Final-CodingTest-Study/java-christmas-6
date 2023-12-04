package christmas.provider;

import christmas.constant.ErrorMessage;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.*;

public class VisitDateProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                arguments(ErrorMessage.VISIT_DATE_NOT_NUMBER, "a"),
                arguments(ErrorMessage.VISIT_DATE_NOT_NUMBER, " "),
                arguments(ErrorMessage.VISIT_DATE_OUT_OF_RANGE, "-1"),
                arguments(ErrorMessage.VISIT_DATE_OUT_OF_RANGE, "0"),
                arguments(ErrorMessage.VISIT_DATE_OUT_OF_RANGE, "32")
        );
    }
}
