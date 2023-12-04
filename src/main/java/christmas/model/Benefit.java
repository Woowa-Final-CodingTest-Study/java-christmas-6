package christmas.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static christmas.util.CustomFormat.currencyFormat;

public class Benefit {
    private final Map<String, Integer> benefit = new HashMap<>();
    private static final String christmas = "크리스마스 디데이 할인";
    private static final String weekend = "주말 할인";
    private static final String weekday = "평일 할인";
    private static final String special = "특별 할인";
    private static final String gift = "증정 이벤트";
    private static final int D_DAY_DISCOUNT_INIT = 1000;
    private static final int D_DAY_DISCOUNT_UNIT = 100;
    private static final int WEEKEND_DISCOUNT = 2023;
    private static final int WEEKDAY_DISCOUNT = 2023;
    private static final int SPECIAL_DISCOUNT = 2023;
    private static final int GIFT_THRESHOLD = 120000;
    private static final String GIFT_MENU = "샴페인";


    public Benefit(VisitDate visitDate, Order order) {
        if (visitDate.value() <= VisitDate.CHRISTMAS)
            add(christmas, D_DAY_DISCOUNT_INIT + D_DAY_DISCOUNT_UNIT * visitDate.value());
        if (visitDate.isWeekend()) {
            int count = order.getCountOf(Category.MAIN_DISH);
            if (count > 0)
                add(weekend, count * WEEKEND_DISCOUNT);
        } else {
            int count = order.getCountOf(Category.DESSERT);
            if (count > 0)
                add(weekday, count * WEEKDAY_DISCOUNT);
        }
        if (visitDate.isSpecialDate())
            add(special, SPECIAL_DISCOUNT);
        if (order.getTotalCost() >= GIFT_THRESHOLD)
            add(gift, Menu.get(GIFT_MENU).price);
    }

    private void add(String name, int amount) {
        benefit.put(name, amount);
    }

    public boolean hasGift() {
        return benefit.containsKey(gift);
    }

    public List<String[]> toStrings() {
        return benefit.entrySet().stream()
                .map(e -> new String[]{
                        e.getKey(),
                        currencyFormat.format(e.getValue())
                })
                .collect(Collectors.toList());
    }

    public int totalBenefitAmount() {
        return benefit.values().stream().reduce(0, Integer::sum);
    }

    public int totalDiscount() {
        return benefit.entrySet().stream()
                .filter(e -> !e.getKey().equals(gift))
                .map(Map.Entry::getValue)
                .reduce(0, Integer::sum);
    }
}
