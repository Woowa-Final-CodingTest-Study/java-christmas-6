package christmas;

import christmas.controller.PromotionController;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = InputView.getInstance();
        OutputView outputView = OutputView.getInstance();

        PromotionController controller = PromotionController.getInstance(inputView, outputView);

        controller.start();
    }
}
