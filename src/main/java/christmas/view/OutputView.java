package christmas.view;

import christmas.constant.OutputMessage;

public class OutputView {
    private static OutputView outputView;

    private OutputView() {
    }

    public static OutputView getInstance() {
        if (outputView == null) {
            outputView = new OutputView();
        }
        return outputView;
    }

    private void println() {
        System.out.println();
    }

    private void println(String... messages) {
        for (String message : messages)
            System.out.println(message);
    }

    public void notifyInputVisitDate() {
        println(OutputMessage.NOTIFY_INPUT_VISIT_DATE);
    }
}
