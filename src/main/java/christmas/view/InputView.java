package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.constant.ErrorMessage;

public class InputView {
    private static InputView inputView;

    private InputView() {
    }

    public static InputView getInstance() {
        if (inputView == null) {
            inputView = new InputView();
        }
        return inputView;
    }

    public void printError(Exception e) {
        System.out.println(ErrorMessage.PREFIX + e.getMessage());
    }

    public String readVisitDate() {
        while (true) {
            try {
                String input = Console.readLine();
                int visitDate = Integer.parseInt(input);
                validateVisitDateRange(visitDate);
                return input;
            } catch (NumberFormatException e) {
                printError(new IllegalArgumentException(ErrorMessage.VISIT_DATE_MUST_NUMBER));
            } catch (IllegalArgumentException e) {
                printError(e);
            }
        }
    }

    private void validateVisitDateRange(int visitDate) {
        if (visitDate < 1 || visitDate > 31)
            throw new IllegalArgumentException(ErrorMessage.VISIT_DATE_MUST_WITHIN_RANGE);
    }
}
