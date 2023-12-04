package christmas.service;

import christmas.constant.Menu;
import christmas.constant.message.SystemMessageConstant;
import christmas.domain.DiscountHistory;
import christmas.domain.MenuCount;
import christmas.domain.OrderMenu;
import java.util.List;

public class Calculator {

    public int calculateTotalPrice(OrderMenu orderMenu) {
        List<MenuCount> menuCounts = orderMenu.getOrderMenu();
        int totalPrice = 0;

        for(int i=0; i< orderMenu.getSize(); i++) {
            totalPrice += calculateMenuPrice(menuCounts.get(i));
        }

        return totalPrice;
    }

    private int calculateMenuPrice(MenuCount menuCount) {
        String menuName = menuCount.getMenuName();
        int menuPrice = menuCount.getCount();
        return Menu.findMenuPrice(menuName) * menuPrice;
    }

    public int calculateTotalDiscount(List<DiscountHistory> discountHistories) {
        int totalDiscount = 0;
        for(int i=0; i< discountHistories.size(); i++) {
            DiscountHistory discountHistory = discountHistories.get(i);
            if(discountHistory.getDiscountPrice()!=0) {
                totalDiscount += discountHistory.getDiscountPrice();
            }
        }
        return totalDiscount;
    }

    public int calculateTotalPay(int totalPrice, List<DiscountHistory> discountHistories) {
        int discount = 0;
        for(int i=0; i<discountHistories.size(); i++) {
            DiscountHistory discountHistory = discountHistories.get(i);
            if(discountHistory.getDiscountPrice()!=0 &&
                    !discountHistory.getDiscountCategory().equals(SystemMessageConstant.GIFT_EVENT)) {
                discount += discountHistory.getDiscountPrice();
            }
        }
        return totalPrice - discount;
    }
}
