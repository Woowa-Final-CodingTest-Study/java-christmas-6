package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.constant.Menu;
import christmas.constant.NumberConstant;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class InputView {

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
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    private int validateNumeric(String input) {
        try {
            return Integer.parseInt(input);
        } catch(IllegalArgumentException illegalArgumentException) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    private void validateDate(int date) {
        Calendar cal = Calendar.getInstance();
        cal.set(NumberConstant.EVENT_YEAR, NumberConstant.EVENT_MONTH-1, 1);

        int lastDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        if(date < NumberConstant.FIRST_DATE || date > lastDate) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    private void validateCorrectForm(String input) {
        String deleteCharacters = input.replaceAll("[가-힣0-9,-]", "");
        if(!deleteCharacters.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
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
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
        }
    }

    private void validateDuplicateMenu(String[] order) {
        Set<String> duplicateSet= new HashSet<>();
        for(String menu : order) {
            if(!duplicateSet.add(menu)) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
        }
    }

    private void validateOnlyBeverage(String input) {
        String deleteMenu = input.replaceAll("제로콜라", "").replaceAll("레드와인", "")
                .replaceAll("샴페인", "").replaceAll("[0-9,-]", "");

        if(deleteMenu.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
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
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
        }
    }

    private void validateOrderCountSum(String[] deleteExceptNumber) {
        int sum = 0;
        for(String number : deleteExceptNumber) {
            sum += Integer.parseInt(number);
        }

        if(sum < NumberConstant.MIN_ORDER_COUNT || sum > NumberConstant.MAX_ORDER_COUNT) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

}
