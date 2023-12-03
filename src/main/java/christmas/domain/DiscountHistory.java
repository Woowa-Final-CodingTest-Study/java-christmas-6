package christmas.domain;

public class DiscountHistory {

    private final String discountCategory;
    private final int discountPrice;

    public DiscountHistory(String discountCategory, int discountPrice) {
        this.discountCategory = discountCategory;
        this.discountPrice = discountPrice;
    }

    public String getDiscountCategory() {
        return discountCategory;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }
}
