package christmas.domain.event;

import christmas.constant.Menu;
import christmas.constant.NumberConstant;

public class GiftEvent {

    public String judgeGiftEvent(int totalPriceBeforeDiscount) {
        if(totalPriceBeforeDiscount > NumberConstant.GIFT_STANDARD_PRICE) {
            return Menu.CHAMPAGNE.getMenuName();
        }
        return Menu.NOTHING.getMenuName();
    }
}
