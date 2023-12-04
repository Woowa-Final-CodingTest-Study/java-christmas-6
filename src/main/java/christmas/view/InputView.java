package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.constant.Menu;
import christmas.constant.MenuCategory;
import christmas.constant.NumberConstant;
import christmas.constant.message.ErrorMessageConstant;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InputView {

    private final String INVALID_DATE_ERROR_MESSAGE = ErrorMessageConstant.INVALID_DATE;
    private final String INVALID_ORDER_ERROR_MESSAGE = ErrorMessageConstant.INVALID_ORDER;

    public int inputUserVisitDate() {
        String input = Console.readLine();

        return validateInputDate(input);
    }

    public String inputUserOrder() {
        String input = Console.readLine();
        validateInputOrder(input);

        return input;
    }

    public void validateInputOrder(String input) {
        validateNull(input);
        validateCorrectForm(input);
        validateMenu(input);
        validateOnlyBeverage(input);
        validateCount(input);
    }

    private int validateInputDate(String input) {
        validateNull(input);
        int date = validateNumeric(input);
        validateDate(date);

        return date;
    }

    private void validateNull(String input) {
        if(input.isEmpty()) {
            throw new IllegalArgumentException(INVALID_DATE_ERROR_MESSAGE);
        }
    }

    private int validateNumeric(String input) {
        try {
            return Integer.parseInt(input);
        } catch(IllegalArgumentException illegalArgumentException) {
            throw new IllegalArgumentException(INVALID_DATE_ERROR_MESSAGE);
        }
    }

    private void validateDate(int date) {
        Calendar cal = Calendar.getInstance();
        cal.set(NumberConstant.EVENT_YEAR, NumberConstant.EVENT_MONTH-1, 1);

        int lastDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        if(date < NumberConstant.FIRST_DATE || date > lastDate) {
            throw new IllegalArgumentException(INVALID_DATE_ERROR_MESSAGE);
        }
    }

    private void validateCorrectForm(String input) {
        String deleteCharacters = input.replaceAll("[가-힣0-9,-]", "");
        if(!deleteCharacters.isEmpty()) {
            throw new IllegalArgumentException(INVALID_ORDER_ERROR_MESSAGE);
        }
    }

    private void validateMenu(String input) {
        String[] order = input.replaceAll("[0-9-]", "").split(",");

        validateContainMenu(order);
        validateDuplicateMenu(order);
    }

    private void validateContainMenu(String[] order) {
        for(String menu : order) {
            if(!Menu.containMenu(menu)) {
                throw new IllegalArgumentException(INVALID_ORDER_ERROR_MESSAGE);
            }
        }
    }

    private void validateDuplicateMenu(String[] order) {
        Set<String> duplicateSet= new HashSet<>();
        for(String menu : order) {
            if(!duplicateSet.add(menu)) {
                throw new IllegalArgumentException(INVALID_ORDER_ERROR_MESSAGE);
            }
        }
    }

    private void validateOnlyBeverage(String input) {
        List<String> beverageMenu = MenuCategory.getMenuInCategory(MenuCategory.BEVERAGE);
        String[] inputValue = input.split(",");

        for(String menu : inputValue) {
            String[] menuName = menu.split("-");
            if(!beverageMenu.contains(menuName[0])) {
                return;
            }
        }
        throw new IllegalArgumentException(INVALID_ORDER_ERROR_MESSAGE);

    }

    private void validateCount(String input) {
        String[] deleteExceptNumber = input.replaceAll("[가-힣-]", "").split(",");

        validateOrderCount(deleteExceptNumber);
        validateOrderCountSum(deleteExceptNumber);
    }

    private void validateOrderCount(String[] deleteExceptNumber) {
        for(String number : deleteExceptNumber) {
            int count = Integer.parseInt(number);

            if(count < NumberConstant.MIN_ORDER_COUNT || count > NumberConstant.MAX_ORDER_COUNT) {
                throw new IllegalArgumentException(INVALID_ORDER_ERROR_MESSAGE);
            }
        }
    }

    private void validateOrderCountSum(String[] deleteExceptNumber) {
        int sum = Arrays.stream(deleteExceptNumber)
                .mapToInt(Integer::parseInt)
                .sum();

        if(sum < NumberConstant.MIN_ORDER_COUNT || sum > NumberConstant.MAX_ORDER_COUNT) {
            throw new IllegalArgumentException(INVALID_ORDER_ERROR_MESSAGE);
        }
    }

}
