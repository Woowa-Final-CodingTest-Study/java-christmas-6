package christmas.constants;

public enum GameMessage {
    GREETINGS_MESSAGE("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");

    private final String message;

    GameMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
