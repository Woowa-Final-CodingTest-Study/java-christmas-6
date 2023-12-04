package christmas.view;

import christmas.constant.Menu;
import christmas.constant.NumberConstant;
import christmas.constant.message.SystemMessageConstant;
import christmas.domain.DiscountHistory;
import christmas.domain.MenuCount;
import christmas.domain.OrderMenu;
import java.util.List;

public class OutputView {

    int eventMonth = NumberConstant.EVENT_MONTH;

    public void printEventPlanner() {
        System.out.printf(SystemMessageConstant.START_PLANNER, eventMonth);
    }

    public void printVisitDatePrompt() {
        System.out.printf(SystemMessageConstant.INPUT_VISIT_DATE, eventMonth);
    }

    public void printOrderPrompt() {
        System.out.println(SystemMessageConstant.INPUT_ORDER_MENU);
    }

    public void printPreviewEventBenefit(int date) {
        System.out.printf(SystemMessageConstant.PREVIEW_BENEFIT, eventMonth, date);
    }

    public void printOrderMenu(OrderMenu orderMenu) {
        System.out.println(SystemMessageConstant.ORDER_MENU);

        List<MenuCount> order = orderMenu.getOrderMenu();

        for(int i=0; i<orderMenu.getSize(); i++) {
            System.out.printf(SystemMessageConstant.ORDER_MENU_RECEIPT, order.get(i).getMenuName(), order.get(i).getCount());
        }
    }

    public void printTotalPrice(int totalPrice) {
        System.out.println(SystemMessageConstant.TOTAL_PRICE_BEFORE_DISCOUNT);
        System.out.printf(SystemMessageConstant.PRICE, totalPrice);
    }

    public void printGift(String menuName) {
        System.out.println(SystemMessageConstant.GIFT_MENU);
        if(!menuName.equals(Menu.NOTHING.getMenuName())) {
            System.out.println(menuName + " 1개");
            return;
        }
        System.out.println(menuName);
    }

    public void printTotalBenefitHistory() {
        System.out.println(SystemMessageConstant.BENEFIT_HISTORY);
    }

    public void printNoBenefitHistory() {
        System.out.println(SystemMessageConstant.NO_BENEFIT_HISTORY);
    }

    public void printBenefitHistory(DiscountHistory discountHistory) {
        System.out.printf(SystemMessageConstant.BENEFIT_HISTORY_DISCOUNT,
                discountHistory.getDiscountCategory(), discountHistory.getDiscountPrice());
    }

    public void printTotalBenefitDiscount(int totalBenefitDiscount) {
        if(totalBenefitDiscount == 0) {
            System.out.println(SystemMessageConstant.NO_BENEFIT_PRICE);
            return;
        }
        System.out.printf(SystemMessageConstant.TOTAL_BENEFIT_PRICE, totalBenefitDiscount);
    }

    public void printExpectedPayAmount(int payAmount) {
        System.out.printf(SystemMessageConstant.EXPECT_PAY_AMOUNT, payAmount);
    }

    public void printBadgeEvent(String badge) {
        System.out.printf(SystemMessageConstant.BADGE_EVENT, eventMonth, badge);
    }

}

