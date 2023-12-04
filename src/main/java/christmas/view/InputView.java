package christmas.view;

import camp.nextstep.edu.missionutils.Console;

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

    public String readVisitDate() {
        String input = Console.readLine();
        return input;
    }
}
