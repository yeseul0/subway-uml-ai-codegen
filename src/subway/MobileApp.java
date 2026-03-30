package subway;

import java.util.Objects;

/**
 * 모바일 앱 (Class Diagram의 Mobile App 추상 클래스에 대응)
 * - UML에서 이탤릭체로 표기 → abstract class
 * - UserId 속성을 보유
 * - TicketGate에 Tag 요청을 보내는 주체
 */
public abstract class MobileApp {

    private final UserId userId;

    protected MobileApp(UserId userId) {
        this.userId = Objects.requireNonNull(userId, "UserId는 null일 수 없습니다.");
    }

    public UserId getUserId() {
        return userId;
    }

    /**
     * Sequence Diagram: 사용자가 개찰구에 태그 요청
     */
    public void requestTag(TicketGate ticketGate) {
        ticketGate.Tag(userId);
    }
}
