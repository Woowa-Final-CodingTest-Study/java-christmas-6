package christmas.domain.discount;

import christmas.constant.MenuCategory;
import christmas.constant.WeekDiscount;
import christmas.domain.MenuOrder;
import christmas.domain.OrderMenuRepository;
import java.util.List;

public class WeekDayDiscount {

    public int calculateWeekDayDiscount(OrderMenuRepository orderMenuRepository) {
        List<MenuOrder> menuOrder = orderMenuRepository.getOrderMenu();

        int weekDayDiscount = 0;

        for(MenuOrder menu : menuOrder) {
            weekDayDiscount += calculateWeekDayDiscountMenuPrice(menu.getMenuName()) * menu.getCount();
        }

        return weekDayDiscount;
    }

    public int calculateWeekDayDiscountMenuPrice(String menuName) {
        String menuCategory = MenuCategory.findByMenuCategory(menuName).name();

        if(menuCategory.equals(WeekDiscount.WEEKDAY_DISCOUNT.getDiscountMenuCategory())) {
            return WeekDiscount.WEEKDAY_DISCOUNT.getDiscountPrice();
        }
        return 0;
    }

}
