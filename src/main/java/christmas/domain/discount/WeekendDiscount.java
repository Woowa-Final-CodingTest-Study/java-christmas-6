package christmas.domain.discount;

import christmas.constant.MenuCategory;
import christmas.constant.WeekDiscount;
import christmas.domain.MenuOrder;
import christmas.domain.OrderMenuRepository;
import java.util.List;

public class WeekendDiscount {

    public int calculateWeekendDiscount(OrderMenuRepository orderMenuRepository) {
        List<MenuOrder> menuOrder = orderMenuRepository.getOrderMenu();

        int weekendDiscount = 0;

        for(int i = 0; i< orderMenuRepository.getSize(); i++) {
            MenuOrder menu = menuOrder.get(i);
            weekendDiscount += calculateWeekendDiscountMenuPrice(menu.getMenuName()) * menu.getCount();
        }

        return weekendDiscount;
    }

    public int calculateWeekendDiscountMenuPrice(String menuName) {
        String menuCategory = MenuCategory.findByMenuCategory(menuName).name();

        if(menuCategory.equals(WeekDiscount.WEEKEND_DISCOUNT.getDiscountMenuCategory())) {
            return WeekDiscount.WEEKEND_DISCOUNT.getDiscountPrice();
        }
        return 0;
    }
}
