package christmas.controller;

import christmas.view.InputView;
import christmas.view.OutputView;

public class EventPlanner {
    InputView inputView;

    public void start() {
        inputView = new InputView();
        introducePlanner();
        int visitDate = enrollVisitDate();
    }

    public void introducePlanner() {
        OutputView.printPlannerIntro();
    }

    public int enrollVisitDate() {
        OutputView.requestVisitDate();
        return inputView.readVisitDate();
    }
}
