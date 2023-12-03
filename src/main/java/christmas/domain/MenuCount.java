package christmas.domain;

public class MenuCount {

    private final String menuName;
    private final int count;

    public MenuCount(String menuName, int count) {
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
