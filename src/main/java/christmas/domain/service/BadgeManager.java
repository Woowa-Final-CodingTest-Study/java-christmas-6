package christmas.domain.service;

import christmas.domain.Order;
import christmas.domain.badge.Badge;

public class BadgeManager {
    private Badge badgeGranted;

    public void grantBadge(EventManager eventManager) {
        int totalDiscount = eventManager.getTotalDiscount();
        Badge badge = Badge.getBadge(totalDiscount);
        badgeGranted = badge;
    }

    public String giveBadgeResult() {
        StringBuilder sb = new StringBuilder();
        sb.append("<12월 이벤트 배지>\n");
        sb.append(badgeGranted.getName());
        return sb.toString();
    }
}
