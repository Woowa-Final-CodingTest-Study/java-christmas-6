package christmas.view;

import static christmas.utils.Constants.ORDER;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public int readVisitDate() {
        try {
            int visitDate = convertStrToInt(Console.readLine(), ORDER);
            validateDateRange(visitDate);
            return visitDate;
        } catch (IllegalArgumentException e) {
            System.out.print(e.getMessage());
            return readVisitDate();
        }
    }

    private int convertStrToInt(String str, String errorType) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 " + errorType + "입니다. 다시 입력해 주세요.");
        }
    }

    public void validateDateRange(int date) {
        if (date < 1 || date > 31) {
            throw new IllegalArgumentException("[ERROR]" + ORDER);
        }
    }
}
