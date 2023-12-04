package christmas.controller;

import christmas.view.InputView;
import christmas.view.OutputView;

public class PromotionController {
    private static PromotionController promotionController;

    private final InputView inputView;
    private final OutputView outputView;

    private PromotionController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public static PromotionController getInstance(InputView inputView, OutputView outputView) {
        if (promotionController == null) {
            promotionController = new PromotionController(inputView, outputView);
        }
        return promotionController;
    }

    public void start() {
        outputView.notifyInputVisitDate();
        String visitDate = inputView.readVisitDate();
    }
}
