package christmas.controller;

import christmas.domain.OrderMenu;
import christmas.service.Calculator;
import christmas.view.OutputView;

public class TotalPriceController {

    Calculator calculator = new Calculator();
    OutputView outputView = new OutputView();

    public int getTotalPrice(OrderMenu orderMenu) {
        int totalPriceBeforeDiscount = calculator.calculateTotalPrice(orderMenu);
        outputView.printTotalPrice(totalPriceBeforeDiscount);

        return totalPriceBeforeDiscount;
    }
}
