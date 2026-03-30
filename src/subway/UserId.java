package subway;

import java.util.Objects;

/**
 * 사용자 식별자 (Mobile App의 UserId 속성에 대응)
 */
public record UserId(String value) {

    public UserId {
        Objects.requireNonNull(value, "UserId value는 null일 수 없습니다.");
        if (value.isBlank()) {
            throw new IllegalArgumentException("UserId value는 빈 값일 수 없습니다.");
        }
    }

    @Override
    public String toString() {
        return "UserId(" + value + ")";
    }
}
