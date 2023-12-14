package christmas.domain.service;

import christmas.domain.Order;
import christmas.domain.VisitingDate;
import christmas.domain.badge.Badge;
import christmas.domain.event.DailyDiscount;
import christmas.domain.event.DdayDiscount;
import christmas.domain.event.GiveawayEvent;
import christmas.domain.event.SpecialDiscount;

public class EventManager {
    private int giveawayEventDiscount;
    private int specialEventDiscount;
    private int dailyEventDiscount;
    private int ddayEventDiscount;
    private int totalDiscount;

    private GiveawayEvent giveawayEvent = new GiveawayEvent();
    private SpecialDiscount specialDiscount = new SpecialDiscount();
    private DailyDiscount dailyDiscount = new DailyDiscount();
    private DdayDiscount ddayDiscount = new DdayDiscount();

    public void calculateDiscounts(VisitingDate visitingDate, Order order) {
        giveawayEventDiscount = calculateGiveawayEventDiscount(order);
        specialEventDiscount = calculateSpecialDiscount(visitingDate, order);
        dailyEventDiscount = calculateDailyDiscount(visitingDate, order);
        ddayEventDiscount = calculateDdayDiscount(visitingDate, order);
        totalDiscount = giveawayEventDiscount + specialEventDiscount + dailyEventDiscount + ddayEventDiscount;
    }


    public String giveDiscountList(VisitingDate visitingDate) {
        if (giveawayEventDiscount == 0 && specialEventDiscount == 0 && dailyEventDiscount == 0 && ddayEventDiscount ==0) {
            StringBuilder sb = new StringBuilder();
            sb.append("<혜택 내역>\n");
            sb.append("없음\n");
            return sb.toString();
        }
        if (!(giveawayEventDiscount == 0 && specialEventDiscount == 0 && dailyEventDiscount == 0 && ddayEventDiscount ==0)) {
            StringBuilder sb = new StringBuilder();
            sb.append("<혜택 내역>\n");
            sb.append("크리스마스 디데이 할인: -" + formatPrice(ddayEventDiscount) + "원\n");
            if (visitingDate.isWeekend()) {
                sb.append("주말 할인: -" + formatPrice(dailyEventDiscount) + "원\n");
            }
            if (!visitingDate.isWeekend()) {
                sb.append("평일 할인: -" + formatPrice(dailyEventDiscount) + "원\n");
            }
            sb.append("특별 할인: -" + formatPrice(specialEventDiscount) + "원\n");
            sb.append("증정 이벤트: -" + formatPrice(giveawayEventDiscount) + "원\n");
            return sb.toString();
        }
        return null;
    }

    public String giveTotalDiscount() {
        if (!(totalDiscount == 0)) {
            StringBuilder sb = new StringBuilder();
            sb.append("<총혜택 금액>\n");
            sb.append("-" + formatPrice(totalDiscount) + "원\n");
            return sb.toString();
        }
        if (totalDiscount == 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("<총혜택 금액>\n");
            sb.append("없음");
            return sb.toString();
        }
        return null;
    }

    public int getTotalDiscount() {
        return totalDiscount;
    }

    public int calculateGiveawayEventDiscount(Order order) {
        return giveawayEvent.calculateGiveawayEvent(order);
    }

    public int calculateSpecialDiscount(VisitingDate visitingDate, Order order) {
        return specialDiscount.calculateSpecialDiscount(visitingDate, order);
    }

    public int calculateDailyDiscount(VisitingDate visitingDate, Order order) {
        return dailyDiscount.calculateDailyDiscount(visitingDate, order);
    }

    public int calculateDdayDiscount(VisitingDate visitingDate, Order order) {
        return ddayDiscount.calculateDdayDiscount(visitingDate, order);
    }

    private String formatPrice(int price) {
        return String.format("%,d", Math.abs(price));
    }
}
