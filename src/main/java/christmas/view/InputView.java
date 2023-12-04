package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.constant.ErrorMessage;
import christmas.model.Category;
import christmas.model.Menu;
import christmas.model.Order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

    public void printError(Exception e) {
        System.out.println(ErrorMessage.PREFIX + e.getMessage());
    }

    public String readVisitDate() {
        while (true) {
            try {
                String input = Console.readLine();
                int visitDate = Integer.parseInt(input);
                validateVisitDateRange(visitDate);
                return input;
            } catch (NumberFormatException e) {
                printError(new IllegalArgumentException(ErrorMessage.VISIT_DATE_NOT_NUMBER));
            } catch (IllegalArgumentException e) {
                printError(e);
            }
        }
    }

    private void validateVisitDateRange(int visitDate) {
        if (visitDate < 1 || visitDate > 31)
            throw new IllegalArgumentException(ErrorMessage.VISIT_DATE_OUT_OF_RANGE);
    }

    public Order readOrder() {
        String input = Console.readLine();
        List<String> parsedInputs = List.of(input.split(","));

        Map<Menu, Integer> order = getOrderFromInput(parsedInputs);
        validateOrderNotOnlyBeverage(order);
        validateOrderTotalCount(order);

        return new Order(order);
    }

    private Map<Menu, Integer> getOrderFromInput(List<String> parsedInputs) {
        Map<Menu, Integer> order = new HashMap<>();
        for (String parsedInput : parsedInputs) {
            String[] s = parsedInput.split("-");
            validateOrderFormat(s);

            Menu menu = Menu.get(s[0]);
            validateMenuUnique(order, menu);

            int count = Integer.parseInt(s[1]);
            validateMenuCount(count);

            order.put(menu, count);
        }
        return order;
    }

    private void validateOrderFormat(String[] s) {
        if (s.length != 2)
            throw new IllegalArgumentException(ErrorMessage.ORDER_INVALID_FORM);
    }

    private void validateMenuUnique(Map<Menu, Integer> order, Menu menu) {
        if (order.containsKey(menu))
            throw new IllegalArgumentException(ErrorMessage.MENU_DUPLICATED);
    }

    private void validateOrderNotOnlyBeverage(Map<Menu, Integer> order) {
        Set<Menu> menus = order.keySet();
        Set<Menu> beverages = menus.stream()
                .filter(menu -> menu.category.equals(Category.BEVERAGE))
                .collect(Collectors.toSet());
        if (menus.size() == beverages.size())
            throw new IllegalArgumentException(ErrorMessage.ORDER_ONLY_BEVERAGE);
    }

    private void validateMenuCount(int i) {
        if (i < 1)
            throw new IllegalArgumentException(ErrorMessage.MENU_COUNT_OUT_OF_RANGE);
    }

    private void validateOrderTotalCount(Map<Menu, Integer> order) {
        Integer totalCount = order.values().stream()
                .reduce(0, Integer::sum);
        if (totalCount > 20)
            throw new IllegalArgumentException(ErrorMessage.ORDER_TOTAL_COUNT_OUT_OF_RANGE);
    }
}
