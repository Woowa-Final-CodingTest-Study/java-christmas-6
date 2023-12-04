package christmas.domain.event.badge;

public class GrantedEventBadge {
    private final String badge;

    private GrantedEventBadge(String badge) {
        this.badge = badge;
    }

    public String getBadge() {
        return this.badge;
    }
}
