package christmas.controller;

import christmas.domain.OrderMenuRepository;
import christmas.service.Calculator;
import christmas.view.OutputView;

public class TotalPriceController {

    Calculator calculator = new Calculator();
    OutputView outputView = new OutputView();

    public int getTotalPrice(OrderMenuRepository orderMenuRepository) {
        int totalPriceBeforeDiscount = calculator.calculateTotalPrice(orderMenuRepository);
        outputView.printTotalPrice(totalPriceBeforeDiscount);

        return totalPriceBeforeDiscount;
    }
}
