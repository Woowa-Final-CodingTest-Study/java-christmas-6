package christmas;

import christmas.domain.event.badge.EventBadge;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EventBadgeTest {
    @Test
    public void getBadgeByTotalBenefitAmountTest() {
        assertEquals("산타", EventBadge.getBadgeByTotalBenefitAmount(-20000));
        assertEquals("트리", EventBadge.getBadgeByTotalBenefitAmount(-10000));
        assertEquals("별", EventBadge.getBadgeByTotalBenefitAmount(-5000));
        assertEquals("없음", EventBadge.getBadgeByTotalBenefitAmount(0));
    }
}

