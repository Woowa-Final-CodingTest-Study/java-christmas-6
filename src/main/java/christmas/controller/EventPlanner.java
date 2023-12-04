package christmas.controller;

import christmas.domain.MenuType;
import christmas.domain.Reservation;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Map;

public class EventPlanner {
    InputView inputView = new InputView();
    OutputView outputView = new OutputView();

    public void start() {
        introducePlanner();
        Reservation reservation = enrollReserveInfo();

    }

    public void introducePlanner() {
        outputView.printPlannerIntro();
        outputView.printEventPrecautions();
    }

    public Reservation enrollReserveInfo() {
        int visitDate = enrollVisitDate();
        while (true) {
            try {
                Map<String, Integer> orderMenu = enrollOrderMenu();
                return new Reservation(visitDate, orderMenu);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public int enrollVisitDate() {
        outputView.requestVisitDate();
        return inputView.readVisitDate();
    }

    public Map<String, Integer> enrollOrderMenu() {
        outputView.printMenu(MenuType.showMenu());
        outputView.requestOrderMenu();
        return inputView.readOrderMenu();
    }
}
