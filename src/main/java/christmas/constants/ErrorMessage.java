package christmas.constants;

public enum ErrorMessage {
    INVALID_NUMBER("올바른 숫자가 아닙니다."),
    MISSING_INPUT("입력이 누락되었습니다.");
    // 다른 에러 메시지 추가 가능

    private final String message;

    private ErrorMessage(String message) {
        this.message = message;
    }

    public String getErrorMessage() {
        return "[ERROR] " + message;
    }
}
