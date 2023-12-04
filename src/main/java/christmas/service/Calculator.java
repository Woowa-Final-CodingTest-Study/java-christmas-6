package christmas.service;

import christmas.constant.Menu;
import christmas.constant.message.SystemMessageConstant;
import christmas.domain.DiscountHistory;
import christmas.domain.MenuOrder;
import christmas.domain.OrderMenuRepository;
import java.util.List;

public class Calculator {

    public int calculateTotalPrice(OrderMenuRepository orderMenuRepository) {
        List<MenuOrder> menuOrders = orderMenuRepository.getOrderMenu();
        int totalPrice = 0;

        for(int i = 0; i< orderMenuRepository.getSize(); i++) {
            totalPrice += calculateMenuPrice(menuOrders.get(i));
        }

        return totalPrice;
    }

    private int calculateMenuPrice(MenuOrder menuOrder) {
        String menuName = menuOrder.getMenuName();
        int menuPrice = menuOrder.getCount();
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
