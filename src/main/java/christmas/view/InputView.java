package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.MenuBoard;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public HashMap<MenuBoard, Integer> getOrder() {
        try {
            HashMap<MenuBoard, Integer> order = new HashMap<>();
            String orderInput = getInput();
            validateOrderFormat(orderInput);
            List<String> menuNames = List.of(orderInput.split(","));

            validateNoExtraSpaces(menuNames);
            validateLengthBetweenHyphen(menuNames);
            validateSingleQuantityCount(menuNames);
            validateMenuInMenuBoard(menuNames);
            validateMenuDuplication(menuNames);
            validateTotalQuantityCount(menuNames);
            validateAllBeverage(menuNames);
            return order;
        } catch (IllegalArgumentException e) {
            outputView.printMessage(e.getMessage());
            return getOrder();
        }
    }

    public void validateOrderFormat(String orderInput) {
        String MENU_ITEM_PATTERN = "^[가-힣]+-\\d+(,[가-힣]+-\\d+)*$";
        Pattern pattern = Pattern.compile(MENU_ITEM_PATTERN);
        Matcher matcher = pattern.matcher(orderInput);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("주문 형식이 올바르지 않습니다.");
        }
    }

    public void validateNoExtraSpaces(List<String> menuNames) {
        for (String str : menuNames) {
            if (str.contains(" ")) {
                throw new IllegalArgumentException("공백을 포함할 수 없습니다.");
            }
        }
    }

    public void validateLengthBetweenHyphen(List<String> menuNames) {
        for (String str : menuNames) {
            String[] parts = str.split("-");
            if (!(parts.length == 2)) {
                throw new IllegalArgumentException("주문 형식이 올바르지 않습니다.");
            }
        }
    }

    public void validateSingleQuantityCount(List<String> menuNames) {
        for (String str : menuNames) {
            String[] parts = str.split("-");
            String quantity = parts[1];
            int quantityValue = Integer.parseInt(quantity);
            if (quantityValue < 1 || quantityValue > 20) {
                throw new IllegalArgumentException("수량은 최소 1개, 최대 20개까지 입력 가능합니다.");
            }
        }
    }


    public void validateMenuInMenuBoard(List<String> menuNames) {
        for (String str : menuNames) {
            String[] parts = str.split("-");
            String menuName = parts[0];
            boolean found = false;
            for (MenuBoard menu : MenuBoard.values()) {
                if (menu.name().equalsIgnoreCase(menuName)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new IllegalArgumentException(menuName + " 메뉴는 존재하지 않습니다.");
            }
        }
    }

    public void validateMenuDuplication(List<String> menuNames) {
        HashSet<String> uniqueMenus = new HashSet<>();
        for (String str : menuNames) {
            String[] parts = str.split("-");
            String menuName = parts[0];
            if (!uniqueMenus.add(menuName)) {
                throw new IllegalArgumentException("메뉴는 중복될 수 없습니다.");
            }
        }
    }

    public void validateTotalQuantityCount(List<String> menuNames) {
        int menuCount = 0;
        for (String str : menuNames) {
            String[] parts = str.split("-");
            int quantityValue = Integer.parseInt(parts[1]);
            menuCount += quantityValue;
            if (menuCount > 20) {
                throw new IllegalArgumentException("총 주문 개수는 20개를 넘을 수 없습니다.");
            }
        }
    }

    public void validateAllBeverage(List<String> menuNames) {
        int beverageCounter = 0;
        List<String> beverageMenu = MenuBoard.getBeverageMenuItems();
        for (String str : menuNames) {
            String[] parts = str.split("-");
            String menuName = parts[0];
            if (beverageMenu.contains(menuName)) {
                beverageCounter++;
            }
        }
        if (beverageCounter == menuNames.size()) {
            throw new IllegalArgumentException("음료만 주문할 수 없습니다.");
        }
    }


    private void validateVisitingDateInput(String input) {
        validateNull(input);
        validateSpace(input);
        validateInteger(input);
    }

    private void validateInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("정수를 입력해야 합니다.");
        }
    }

    private void validateNull(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("입력이 비어있습니다.");
        }
    }

    private void validateSpace(String input) {
        if (input.contains(" ")) {
            throw new IllegalArgumentException("공백은 포함될 수 없습니다.");
        }
    }

    private void validateDateRange(int input) {
        if (input < 1 || input > 31) {
            throw new IllegalArgumentException("범위가 잘못되었습니다. 12월은 1일부터 31일까지입니다.");
        }
    }
}
