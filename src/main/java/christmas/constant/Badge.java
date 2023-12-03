package christmas.constant;

public enum Badge {
    SANTA("산타", 20_000),
    TREE("트리", 10_000),
    STAR("별", 5_000),
    NOTHING("없음", 0);

    private final String badge;
    private final int totalBenefitPrice;

    Badge(String badge, int totalBenefitPrice) {
        this.badge = badge;
        this.totalBenefitPrice = totalBenefitPrice;
    }

    public String getBadge() {
        return badge;
    }

    public int getTotalBenefitPrice() {
        return totalBenefitPrice;
    }

    public static String findBadge(int totalDiscount) {
        for(Badge badge : Badge.values()) {
            if(totalDiscount >= badge.getTotalBenefitPrice()) {
                return badge.getBadge();
            }
        }
        return NOTHING.getBadge();
    }
}
