package christmas.domain;

import java.util.Collections;
import java.util.List;

public class BenefitHistoryRepository {

    private final List<DiscountHistory> totalBenefitHistory;

    public BenefitHistoryRepository(List<DiscountHistory> totalBenefitHistory) {
        this.totalBenefitHistory = Collections.unmodifiableList(totalBenefitHistory);
    }

    public List<DiscountHistory> getBenefitHistory() {
        return totalBenefitHistory;
    }

    public int getSize() {
        return totalBenefitHistory.size();
    }
}
