package christmas.domain;

import christmas.constant.Badge;

public class BadgeEvent {

    public String judgeBadgeEvent(int totalDiscount) {
        return Badge.findBadge(totalDiscount);
    }
}
