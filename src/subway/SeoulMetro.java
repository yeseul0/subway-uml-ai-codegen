package subway;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 서울 지하철 시스템 (Class Diagram의 Seoul Metro에 대응)
 *
 * 속성:
 *   - rideRecords : Map<UserId, RideRecord>   → UserId를 키로 승차 정보 관리
 *
 * 메서드:
 *   + TagIn(UserId, TagTime, Station) : void
 *   + TagOut(UserId, TagTime, Station) : void
 *   - calculateFare(src, dest) : Fare          → private
 *
 * Sequence Diagram 흐름:
 *   [승차] TicketGate → TagIn()  → rideRecords에 저장 → return
 *   [하차] TicketGate → TagOut() → calculateFare() → Settlement() → return
 */
public class SeoulMetro {

    /** UserId를 키로 승차 정보를 관리 */
    private final Map<UserId, RideRecord> rideRecords = new HashMap<>();

    /** CardPayment와의 연관관계 (Class Diagram 하향 실선 화살표) */
    private final CardPayment cardPayment;

    public SeoulMetro(CardPayment cardPayment) {
        this.cardPayment = Objects.requireNonNull(cardPayment, "CardPayment는 null일 수 없습니다.");
    }

    /**
     * 승차 처리
     * Sequence Diagram [승차] 분기에서 TicketGate가 호출
     *
     * @param userId  사용자 식별자
     * @param tagTime 태그 시각
     * @param station 승차역
     */
    public void TagIn(UserId userId, LocalDateTime tagTime, Station station) {
        Objects.requireNonNull(userId, "UserId는 null일 수 없습니다.");
        Objects.requireNonNull(tagTime, "TagTime은 null일 수 없습니다.");
        Objects.requireNonNull(station, "Station은 null일 수 없습니다.");

        rideRecords.put(userId, new RideRecord(station, tagTime));
        System.out.printf("[SeoulMetro] 승차 기록 - %s, 역: %s, 시각: %s%n",
                userId, station, tagTime);
    }

    /**
     * 하차 처리
     * Sequence Diagram [하차] 분기에서 TicketGate가 호출
     * 내부적으로 calculateFare → cardPayment.Settlement 순서로 실행
     *
     * @param userId  사용자 식별자
     * @param tagTime 태그 시각
     * @param station 하차역
     */
    public void TagOut(UserId userId, LocalDateTime tagTime, Station station) {
        Objects.requireNonNull(userId, "UserId는 null일 수 없습니다.");
        Objects.requireNonNull(tagTime, "TagTime은 null일 수 없습니다.");
        Objects.requireNonNull(station, "Station은 null일 수 없습니다.");

        RideRecord record = rideRecords.get(userId);
        if (record == null) {
            throw new IllegalStateException("승차 기록이 없습니다: " + userId);
        }

        System.out.printf("[SeoulMetro] 하차 처리 - %s, 역: %s, 시각: %s%n",
                userId, station, tagTime);

        // Sequence Diagram: calculateFare(src, dest) 내부 호출
        Fare fare = calculateFare(record.boardingStation(), station);

        // Sequence Diagram: Settlement(UserId, Fare, RequestTime) 호출
        cardPayment.Settlement(userId, fare, tagTime);

        rideRecords.remove(userId);
    }

    /**
     * 요금 계산 (private)
     * 승차역과 하차역의 역 번호 차이를 기준으로 서울 지하철 요금 정책 적용
     *
     * 기본요금: 1,400원
     * 10km 초과 시 5km마다 100원 추가
     * (역 번호 차이를 거리(km)로 근사)
     *
     * @param src  승차역
     * @param dest 하차역
     * @return 계산된 요금
     */
    private Fare calculateFare(Station src, Station dest) {
        int distance = Math.abs(dest.stationNumber() - src.stationNumber());

        int basefare = 1_400;
        int extra = 0;
        if (distance > 10) {
            // 10km 초과분을 5km 단위로 100원 추가
            extra = ((distance - 10 + 4) / 5) * 100;
        }

        Fare fare = new Fare(basefare + extra);
        System.out.printf("[SeoulMetro] 요금 계산 - %s → %s, 거리: %dkm, 요금: %s%n",
                src, dest, distance, fare);
        return fare;
    }

    /**
     * 해당 사용자의 승차 기록 존재 여부 확인
     * TicketGate.Tag()에서 승차/하차 분기 판단에 사용
     */
    public boolean hasRideRecord(UserId userId) {
        return rideRecords.containsKey(userId);
    }
}
