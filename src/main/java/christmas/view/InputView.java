package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.MenuBoard;
import christmas.domain.MenuType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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

    public Map<MenuBoard, Integer> getOrder() {
        Map<MenuBoard, Integer> order = new HashMap<>();
        String orderInput = getInput();
        validateOrderFormat(orderInput);

        List<String> splitOrders = splitString(orderInput);
        order = addOrderToMap(order, splitOrders);
        validateQuantity(order);
        validateBeverageType(order);
        return order;
    }

    private void validateQuantity(Map<MenuBoard, Integer> order) {
        int totalQuantity = order.values().stream().mapToInt(Integer::intValue).sum();
        if (totalQuantity < 1 || totalQuantity > 20) {
            throw new IllegalArgumentException("주문 수량은 1개에서 20개 사이어야 합니다.");
        }
    }

    private void validateBeverageType(Map<MenuBoard, Integer> order) {
        boolean containsNonBeverage = order.keySet().stream()
                .anyMatch(menu -> menu.getType() != MenuType.BEVERAGE);
        if (!containsNonBeverage) {
            throw new IllegalArgumentException("주문에 음료 메뉴만 포함되어 있습니다.");
        }
    }

    private Map<MenuBoard, Integer> addOrderToMap(Map<MenuBoard, Integer> order, List<String> splitOrder) {
        for (String orderItem : splitOrder) {
            String[] parts = orderItem.split("-");
            if (parts.length == 2) {
                String menuName = parts[0];
                int quantity = Integer.parseInt(parts[1]);

                MenuBoard menu = getMenuByName(menuName);
                // 메뉴가 유효하고 수량이 1 이상이면 Map에 추가합니다.
                if (menu != null && quantity > 0) {
                    order.put(menu, quantity);
                }
            }
        }
        return order;
    }

    private MenuBoard getMenuByName(String menuName) {
        for (MenuBoard menu : MenuBoard.values()) {
            if (menu.name().equalsIgnoreCase(menuName)) {
                return menu;
            }
        }
        throw new IllegalArgumentException("메뉴판에 없는 메뉴는 등록할 수 없습니다.");
    }


    public List<String> splitString(String input) {
        List<String> result = new ArrayList<>();

        if (input.contains(",")) {
            String[] splitStrings = input.split(",");
            result.addAll(Arrays.asList(splitStrings));
        }

        return result;
    }

    public void validateOrderFormat(String input) {
        try {
            String pattern = "^(\\w+-\\d+)(,\\w+-\\d+)*$";
            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(input);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("주문에 대한 올바른 입력 형식이 아닙니다.");
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
