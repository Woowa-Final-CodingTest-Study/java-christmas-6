package christmas.view;

import static christmas.utils.Constants.ORDER;

import camp.nextstep.edu.missionutils.Console;
import java.util.HashMap;
import java.util.Map;

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

    public Map<String, Integer> readOrderMenu() {
        try {
            String[] menusWithCount = Console.readLine().split(",");
            return getMenuCount(menusWithCount);
        } catch (IllegalArgumentException e) {
            System.out.print(e.getMessage());
            return readOrderMenu();
        }
    }

    public Map<String, Integer> getMenuCount(String[] menusWithCount) {
        Map<String, Integer> orderMenu = new HashMap<>();
        int totalCount = 0;
        for (String menuWithCount : menusWithCount) {
            String[] splitedMenuWithCount = menuWithCount.split("-");
            String menuName = splitedMenuWithCount[0];
            int menuCount = convertStrToInt(splitedMenuWithCount[1], ORDER);

            validateOrderMenuFormat(menuWithCount);
            validateDuplicateMenu(menuName, orderMenu);
            validateMenuCount(menuCount);
            orderMenu.put(menuName, menuCount);
            totalCount += menuCount;
        }
        validateTotalCount(totalCount);
        return orderMenu;
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

    public void validateOrderMenuFormat(String orderMenu) {
        if (!orderMenu.matches("[가-힣]+-[0-9]+")) {
            throw new IllegalArgumentException("[ERROR]" + ORDER);
        }
    }

    public void validateDuplicateMenu(String menu, Map<String, Integer> menus) {
        if (menus.containsKey(menu)) {
            throw new IllegalArgumentException("[ERROR]" + ORDER);
        }

    }

    public void validateMenuCount(int menuCount) {
        if (menuCount < 1) {
            throw new IllegalArgumentException("[ERROR]" + ORDER);
        }
    }

    public void validateTotalCount(int totalCount) {
        if (totalCount > 20) {
            throw new IllegalArgumentException("[ERROR]" + ORDER);
        }
    }
}
