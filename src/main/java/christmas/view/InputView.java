package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    OutputView outputView = new OutputView();

    public String getInput() {
        String input = Console.readLine();
        return input;
    }

    public int getVisitingDate() {
        try {
            String visitingDateInput = getInput();
            validateVisitingDateInput(visitingDateInput);
            int visitingDate = Integer.parseInt(visitingDateInput);
            validateDateRange(visitingDate);
            return visitingDate;
        } catch (IllegalArgumentException e) {
            outputView.printMessage(e.getMessage());
            return getVisitingDate();
        }
    }

    public void validateDateRange(int input) {
        if (input < 1 || input > 31) {
            throw new IllegalArgumentException("범위가 잘못되었습니다. 12월은 1일부터 31일까지입니다.");
        }
    }

    public void validateVisitingDateInput(String input) {
        validateNull(input);
        validateSpace(input);
        validateInteger(input);
    }

    public void validateInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("정수를 입력해야 합니다.");
        }
    }

    public void validateNull(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("입력이 비어있습니다.");
        }
    }

    public void validateSpace(String input) {
        if (input.contains(" ")) {
            throw new IllegalArgumentException("공백은 포함될 수 없습니다.");
        }
    }
}
