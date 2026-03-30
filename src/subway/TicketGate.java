package subway;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 개찰구 (Class Diagram의 Ticket Gate에 대응)
 *
 * 속성:
 *   Station - 이 개찰구가 위치한 역
 *
 * 메서드:
 *   + Tag(UserId) : void
 *
 * Sequence Diagram 흐름:
 *   User → Tag(UserId)
 *     alt [승차]: TagIn(UserId, TagTime, Station) → return
 *     alt [하차]: TagOut(UserId, TagTime, Station) → ... → return
 */
public class TicketGate {

    /** 개찰구가 위치한 역 */
    private final Station station;

    /** SeoulMetro 의존 (실선 화살표: TicketGate → SeoulMetro) */
    private final SeoulMetro seoulMetro;

    public TicketGate(Station station, SeoulMetro seoulMetro) {
        this.station = Objects.requireNonNull(station, "Station은 null일 수 없습니다.");
        this.seoulMetro = Objects.requireNonNull(seoulMetro, "SeoulMetro는 null일 수 없습니다.");
    }

    /**
     * 사용자 태그 처리
     * Sequence Diagram의 진입점: User → Tag(UserId)
     *
     * 승차 기록 유무로 alt [승차] / [하차] 분기 결정:
     *   - 승차 기록 없음 → [승차]: seoulMetro.TagIn() 호출
     *   - 승차 기록 있음 → [하차]: seoulMetro.TagOut() 호출
     *
     * @param userId 사용자 식별자
     */
    public void Tag(UserId userId) {
        Objects.requireNonNull(userId, "UserId는 null일 수 없습니다.");

        LocalDateTime tagTime = LocalDateTime.now();
        System.out.printf("[TicketGate] Tag 수신 - %s, 역: %s, 시각: %s%n",
                userId, station, tagTime);

        if (seoulMetro.hasRideRecord(userId)) {
            // alt [하차]
            System.out.println("[TicketGate] → [하차] TagOut 호출");
            seoulMetro.TagOut(userId, tagTime, station);
        } else {
            // alt [승차]
            System.out.println("[TicketGate] → [승차] TagIn 호출");
            seoulMetro.TagIn(userId, tagTime, station);
        }
    }

    public Station getStation() {
        return station;
    }
}
