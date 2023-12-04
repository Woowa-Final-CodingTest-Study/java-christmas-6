package christmas.controller;

import christmas.constant.Menu;
import christmas.constant.SequenceOfWeek;
import christmas.constant.StarDiscount;
import christmas.constant.message.SystemMessageConstant;
import christmas.constant.WeekDiscount;
import christmas.domain.DiscountHistory;
import christmas.domain.OrderMenu;
import christmas.domain.TotalBenefitHistory;
import christmas.domain.discount.DdayDiscount;
import christmas.domain.discount.SpecialDiscount;
import christmas.domain.discount.WeekDayDiscount;
import christmas.domain.discount.WeekendDiscount;
import christmas.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class DiscountController {

    DdayDiscount ddayDiscount = new DdayDiscount();
    WeekDayDiscount weekDayDiscount = new WeekDayDiscount();
    WeekendDiscount weekendDiscount = new WeekendDiscount();
    SpecialDiscount specialDiscount = new SpecialDiscount();
    OutputView outputView = new OutputView();

    public List<DiscountHistory> generateTotalBenefitHistory(int visitDate, OrderMenu orderMenu, String gift) {
        TotalBenefitHistory totalBenefitHistory = gatherTotalBenefitHistory(visitDate, orderMenu, gift);
        List<DiscountHistory> discountHistories = totalBenefitHistory.getBenefitHistory();

        for(int i=0; i< totalBenefitHistory.getSize(); i++) {
            DiscountHistory discountHistory = discountHistories.get(i);
            if(discountHistory.getDiscountPrice()!=0) {
                outputView.printBenefitHistory(discountHistory);
            }
        }
        return discountHistories;
    }

    public TotalBenefitHistory gatherTotalBenefitHistory(int visitDate, OrderMenu orderMenu, String gift) {
        List<DiscountHistory> benefitHistory = new ArrayList<>();

        int sequenceOfDay = SequenceOfWeek.calculateSequenceOfDay(visitDate);
        String discountCategory = WeekDiscount.findByWeekDiscount(sequenceOfDay).getDiscountCategory();

        benefitHistory.add(getDdayDiscountHistory(visitDate));
        benefitHistory.add(getWeekDiscountHistory(discountCategory, orderMenu));
        benefitHistory.add(getSpecialDiscountHistory(visitDate));

        if(Menu.findMenuPrice(gift) != 0) {
            benefitHistory.add(getGiftEventHistory(gift));
        }
        return new TotalBenefitHistory(benefitHistory);
    }

    public DiscountHistory getDdayDiscountHistory(int visitDate) {
        int totalDdayDiscount = ddayDiscount.calculateDdayDiscount(visitDate);

        return new DiscountHistory(SystemMessageConstant.DDAY_DISCOUNT_CATEGORY, totalDdayDiscount);
    }

    public DiscountHistory getWeekDiscountHistory(String discountCategory, OrderMenu orderMenu) {
        if(discountCategory.equals(WeekDiscount.WEEKDAY_DISCOUNT.getDiscountMenuCategory())) {
            return getWeekDayDiscountHistory(orderMenu);
        }
        return getWeekendDiscountHistory(orderMenu);
    }

    public DiscountHistory getWeekDayDiscountHistory(OrderMenu orderMenu) {
        int totalWeekDayDiscount = weekDayDiscount.calculateWeekDayDiscount(orderMenu);

        return new DiscountHistory(WeekDiscount.WEEKDAY_DISCOUNT.getDiscountCategory(), totalWeekDayDiscount);
    }

    public DiscountHistory getWeekendDiscountHistory(OrderMenu orderMenu) {
        int totalWeekendDiscount = weekendDiscount.calculateWeekendDiscount(orderMenu);

        return new DiscountHistory(WeekDiscount.WEEKEND_DISCOUNT.getDiscountCategory(), totalWeekendDiscount);
    }

    public DiscountHistory getGiftEventHistory(String gift) {
        int giftEventDiscount = Menu.findMenuPrice(gift);

        return new DiscountHistory(SystemMessageConstant.GIFT_EVENT, giftEventDiscount);
    }

    public DiscountHistory getSpecialDiscountHistory(int visitDate) {
        int totalSpecialDiscount = specialDiscount.calculateSpecialDiscount(visitDate);

        return new DiscountHistory(StarDiscount.SPECIAL_DISCOUNT.getDiscountCategory(), totalSpecialDiscount);
    }
}
