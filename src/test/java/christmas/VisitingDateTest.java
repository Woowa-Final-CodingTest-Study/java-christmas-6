package christmas;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import christmas.domain.VisitingDate;
import org.junit.jupiter.api.Test;

public class VisitingDateTest {
    @Test
    public void validateVisitingDateTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            new VisitingDate(0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new VisitingDate(32);
        });
        assertDoesNotThrow(() -> {
            new VisitingDate(1);
            new VisitingDate(31);
        });
    }

    @Test
    public void calculateDiscountPriceForDdayEventTest() {
        VisitingDate visitingDate = new VisitingDate(1);
        assertEquals(-1000, visitingDate.calculateDiscountPriceForDdayEvent());

        visitingDate = new VisitingDate(2);
        assertEquals(-1100, visitingDate.calculateDiscountPriceForDdayEvent());
    }

    @Test
    public void isAfterChristmasTest() {
        VisitingDate visitingDate = new VisitingDate(25);
        assertFalse(visitingDate.isAfterChristmas());

        visitingDate = new VisitingDate(26);
        assertTrue(visitingDate.isAfterChristmas());

        visitingDate = new VisitingDate(31);
        assertTrue(visitingDate.isAfterChristmas());
    }

    @Test
    public void isWeekendTest() {
        VisitingDate visitingDate = new VisitingDate(1);
        assertTrue(visitingDate.isWeekend());

        visitingDate = new VisitingDate(2);
        assertTrue(visitingDate.isWeekend());

        visitingDate = new VisitingDate(3);
        assertFalse(visitingDate.isWeekend());
    }

    @Test
    public void isAvailableForSpecialEventTest() {
        VisitingDate visitingDate = new VisitingDate(3);
        assertTrue(visitingDate.isAvailableForSpecialEvent());

        visitingDate = new VisitingDate(25);
        assertTrue(visitingDate.isAvailableForSpecialEvent());

        visitingDate = new VisitingDate(1);
        assertFalse(visitingDate.isAvailableForSpecialEvent());
    }
}
