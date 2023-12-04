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

        for(MenuOrder menuOrder : menuOrders) {
            totalPrice += calculateMenuPrice(menuOrder);
        }

        return totalPrice;
    }

    public int calculateMenuPrice(MenuOrder menuOrder) {
        String menuName = menuOrder.getMenuName();
        int menuPrice = menuOrder.getCount();
        return Menu.findMenuPrice(menuName) * menuPrice;
    }

    public int calculateTotalDiscount(List<DiscountHistory> discountHistories) {
        int totalDiscount = discountHistories.stream()
                .filter(discountHistory -> discountHistory.getDiscountPrice() != 0)
                .mapToInt(DiscountHistory::getDiscountPrice)
                .sum();

        return totalDiscount;
    }

    public int calculateTotalPay(int totalPrice, List<DiscountHistory> discountHistories) {
        int discount = discountHistories.stream()
                .filter(discountHistory -> discountHistory.getDiscountPrice() != 0 &&
                        !discountHistory.getDiscountCategory().equals((SystemMessageConstant.GIFT_EVENT)))
                .mapToInt(DiscountHistory::getDiscountPrice)
                .sum();

        return totalPrice - discount;
    }
}
