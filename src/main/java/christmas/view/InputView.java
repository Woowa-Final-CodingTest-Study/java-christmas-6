package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.constants.ErrorMessage;
import christmas.domain.VisitingDate;

public class InputView {
    public static VisitingDate getVisitingDateInput() {
        String input = Console.readLine();
        validateVisitngDateInput(input);
        int day = Integer.parseInt(input);
        return new VisitingDate(day);
    }

    public static void validateVisitngDateInput(String input) {
        if (!isInteger(input)) {
            throw new IllegalArgumentException(ErrorMessage.invalidDateError);
        }
        if (!containsNoSpace(input)) {
            throw new IllegalArgumentException(ErrorMessage.invalidDateError);
        }
        if (!notStartsWithZero(input)) {
            throw new IllegalArgumentException(ErrorMessage.invalidDateError);
        }
    }

    private static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private static boolean containsNoSpace(String input) {
        if (input.contains(" ") || input == null) {
            return false;
        }
        return true;
    }

    private static boolean notStartsWithZero(String input) {
        if (input.startsWith("0")) {
            return false;
        }
        return true;
    }

//    private static boolean isValidFormatForDateInput(String input) {
//        String regex = "([가-힣]+-\\d+)(,([가-힣]+-\\d+))*";
//        if (input.matches(regex)) {
//            return true;
//        }
//        return false;
//    }
}
