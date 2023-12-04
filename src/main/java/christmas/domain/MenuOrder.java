package christmas.domain;

public class MenuOrder {

    private final String menuName;
    private final int count;

    public MenuOrder(String menuName, int count) {
        this.menuName = menuName;
        this.count = count;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getCount() {
        return count;
    }
}
