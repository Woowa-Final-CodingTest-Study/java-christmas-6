package christmas.model;

public enum Badge {
    SANTA("산타", 20000),
    TREE("트리", 10000),
    STAR("별", 5000),
    NOTHING;


    public String value;
    public int benefitAmount;

    Badge(String value, int benefitAmount) {
        this.value = value;
        this.benefitAmount = benefitAmount;
    }

    Badge() {}

    public static Badge of(int benefitAmount) {
        for (Badge value : Badge.values()) {
            if (benefitAmount >= value.benefitAmount)
                return value;
        }
        return Badge.NOTHING;
    }
}
