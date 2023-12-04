package christmas.controller;

import christmas.domain.MenuType;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Map;

public class EventPlanner {
    InputView inputView = new InputView();


    public void start() {
        introducePlanner();
        int visitDate = enrollVisitDate();
        Map<String, Integer> orderMenu = enrollOrderMenu();
    }

    public void introducePlanner() {
        OutputView.printPlannerIntro();
        OutputView.printEventPrecautions();
    }

    public int enrollVisitDate() {
        OutputView.requestVisitDate();
        return inputView.readVisitDate();
    }

    public Map<String, Integer> enrollOrderMenu() {
        OutputView.printMenu(MenuType.showMenu());
        OutputView.requestOrderMenu();
        return inputView.readOrderMenu();
    }
}
