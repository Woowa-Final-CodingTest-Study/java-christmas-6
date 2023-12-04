package christmas.controller;

import christmas.domain.event.GiftEvent;
import christmas.view.OutputView;

public class GiftEventController {

    GiftEvent giftEvent = new GiftEvent();
    OutputView outputView = new OutputView();

    public String getGift(int totalPrice) {
        String giftMenu = giftEvent.judgeGiftEvent(totalPrice);
        outputView.printGift(giftMenu);

        return giftMenu;
    }
}
