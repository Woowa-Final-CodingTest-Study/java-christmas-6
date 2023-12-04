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

    public void printError(String message) {
        System.out.println(ErrorMessage.PREFIX + message);
    }

    public String readVisitDate() {
        while (true) {
            try {
                String input = Console.readLine();
                int visitDate = Integer.parseInt(input);
                validateRange(visitDate);
                return input;
            } catch (IllegalArgumentException e) {
                printError(ErrorMessage.VISIT_DATE_MUST_WITHIN_RANGE);
            }
        }
    }

    private void validateRange(int visitDate) {
        if (visitDate < 1 || visitDate > 31)
            throw new IllegalArgumentException();
    }
}
