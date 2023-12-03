package christmas.view;

import christmas.constant.NumberConstant;
import christmas.constant.SystemMessageConstant;

public class OutputView {

    int eventMonth = NumberConstant.EVENT_MONTH;

    public void printEventPlanner() {
        System.out.printf(SystemMessageConstant.START_PLANNER, eventMonth);
    }

    public void printVisitDatePrompt() {
        System.out.printf(SystemMessageConstant.INPUT_VISIT_DATE, eventMonth);
    }

    public void printOrderPrompt() {
        System.out.println(SystemMessageConstant.INPUT_ORDER_MENU);
    }
}
