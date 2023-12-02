package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.constants.ErrorMessage;
import christmas.domain.MenuBoard;
import christmas.domain.Order;
import christmas.domain.VisitingDate;
import java.util.HashMap;

public class InputView {
    public static VisitingDate getVisitingDateInput() {
        String input = Console.readLine();
        validateVisitngDateInput(input);
        int day = Integer.parseInt(input);
        return new VisitingDate(day);
    }

    public static Order getOrderInput() {
        String input = Console.readLine();
        validateOrderInput(input);
        return convertToOrder(input);
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

    public static void validateOrderInput(String input) {
        if(!containsNoSpace(input)) {
            throw new IllegalArgumentException(ErrorMessage.invalidOrderError);
        }
        if (!isValidFormatForOrder(input)) {
            throw new IllegalArgumentException(ErrorMessage.invalidOrderError);
        }
    }

    public static boolean isValidFormatForOrder(String input) {
        String[] items = input.split(",");
        for (String item : items) {
            String[] splitItem = item.split("-");
            if (splitItem.length != 2) {
                return false;
            }
            String menu = splitItem[0];
            String quantityStr = splitItem[1];

            try {
                int quantity = Integer.parseInt(quantityStr);
                if (quantity < 1) {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

    private static Order convertToOrder(String input) {
        String[] orderItems = input.split(",");
        HashMap<MenuBoard, Integer> order = new HashMap<>();

        for (String item : orderItems) {
            String[] details = item.split("-");
            order.put(MenuBoard.valueOf(details[0]), Integer.parseInt(details[1]));
        }
        return new Order(order);
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
}
