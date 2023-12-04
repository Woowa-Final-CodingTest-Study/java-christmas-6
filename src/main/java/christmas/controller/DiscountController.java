package christmas.controller;

import christmas.constant.Menu;
import christmas.constant.SequenceOfWeek;
import christmas.constant.StarDiscount;
import christmas.constant.message.SystemMessageConstant;
import christmas.constant.WeekDiscount;
import christmas.domain.DiscountHistory;
import christmas.domain.OrderMenuRepository;
import christmas.domain.BenefitHistoryRepository;
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

    public List<DiscountHistory> generateTotalBenefitHistory(int visitDate, OrderMenuRepository orderMenuRepository, String gift) {
        BenefitHistoryRepository benefitHistoryRepository = gatherTotalBenefitHistory(visitDate, orderMenuRepository, gift);
        List<DiscountHistory> discountHistories = benefitHistoryRepository.getBenefitHistory();

        for(int i = 0; i< benefitHistoryRepository.getSize(); i++) {
            DiscountHistory discountHistory = discountHistories.get(i);
            if(discountHistory.getDiscountPrice()!=0) {
                outputView.printBenefitHistory(discountHistory);
            }
        }
        return discountHistories;
    }

    public BenefitHistoryRepository gatherTotalBenefitHistory(int visitDate, OrderMenuRepository orderMenuRepository, String gift) {
        List<DiscountHistory> benefitHistory = new ArrayList<>();

        int sequenceOfDay = SequenceOfWeek.calculateSequenceOfDay(visitDate);
        String discountCategory = WeekDiscount.findByWeekDiscount(sequenceOfDay).getDiscountCategory();

        benefitHistory.add(getDdayDiscountHistory(visitDate));
        benefitHistory.add(getWeekDiscountHistory(discountCategory, orderMenuRepository));
        benefitHistory.add(getSpecialDiscountHistory(visitDate));

        if(Menu.findMenuPrice(gift) != 0) {
            benefitHistory.add(getGiftEventHistory(gift));
        }

        return new BenefitHistoryRepository(benefitHistory);
    }

    public DiscountHistory getDdayDiscountHistory(int visitDate) {
        int totalDdayDiscount = ddayDiscount.calculateDdayDiscount(visitDate);

        return new DiscountHistory(SystemMessageConstant.DDAY_DISCOUNT_CATEGORY, totalDdayDiscount);
    }

    public DiscountHistory getWeekDiscountHistory(String discountCategory, OrderMenuRepository orderMenuRepository) {
        if(discountCategory.equals(WeekDiscount.WEEKDAY_DISCOUNT.getDiscountMenuCategory())) {
            return getWeekDayDiscountHistory(orderMenuRepository);
        }
        return getWeekendDiscountHistory(orderMenuRepository);
    }

    public DiscountHistory getWeekDayDiscountHistory(OrderMenuRepository orderMenuRepository) {
        int totalWeekDayDiscount = weekDayDiscount.calculateWeekDayDiscount(orderMenuRepository);

        return new DiscountHistory(WeekDiscount.WEEKDAY_DISCOUNT.getDiscountCategory(), totalWeekDayDiscount);
    }

    public DiscountHistory getWeekendDiscountHistory(OrderMenuRepository orderMenuRepository) {
        int totalWeekendDiscount = weekendDiscount.calculateWeekendDiscount(orderMenuRepository);

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
