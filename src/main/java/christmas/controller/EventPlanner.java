package christmas.controller;

import christmas.domain.MenuType;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventPlanner {
    InputView inputView;

    public void start() {
        inputView = new InputView();
        introducePlanner();
        int visitDate = enrollVisitDate();
        enrollOrderMenu();
    }

    public void introducePlanner() {
        OutputView.printPlannerIntro();
        OutputView.printEventPrecautions();
    }

    public int enrollVisitDate() {
        OutputView.requestVisitDate();
        return inputView.readVisitDate();
    }

    public void enrollOrderMenu() {
        OutputView.printMenu(MenuType.showMenu());
    }
}
