package christmas.domain.discount;

import christmas.constant.Menu;
import christmas.constant.MenuCategory;
import christmas.constant.WeekDiscount;
import christmas.domain.MenuCount;
import christmas.domain.OrderMenu;
import java.util.List;

public class WeekDayDiscount {

    public int calculateWeekDayDiscount(OrderMenu orderMenu) {
        List<MenuCount> menuCount = orderMenu.getOrderMenu();

        int weekDayDiscount = 0;

        for(int i=0; i<orderMenu.getSize(); i++) {
            MenuCount menu = menuCount.get(i);
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
