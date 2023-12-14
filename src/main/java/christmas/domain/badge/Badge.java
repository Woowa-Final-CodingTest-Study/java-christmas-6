package christmas.domain.badge;

public enum Badge {
    NONE("없음",0),
    STAR("산타", 5000),
    TREE("트리", 10000),
    SANTA("산타", 20000);

    private final String name;
    private final int baseAmount;

    Badge(String name, int baseAmount) {
        this.name = name;
        this.baseAmount = baseAmount;
    }

    public String getName() {
        return name;
    }

    public int getBaseAmount() {
        return baseAmount;
    }

    public static Badge getBadge(int amount) {
        if (amount >= 20000) {
            return SANTA;
        }
        if (amount >= 10000) {
            return TREE;
        }
        if (amount >= 5000) {
            return STAR;
        }
        return NONE;
    }

}

