package subway;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 카드 결제 (Class Diagram의 Card Payment에 대응)
 *
 * 메서드:
 *   + Settlement(UserId, Fare, RequestTime) : void  → public
 *   - SaveDB(UserId, Fare, SettlementTime) : void   → private
 *
 * Sequence Diagram 흐름:
 *   SeoulMetro → Settlement() 호출
 *     └─ (내부) SaveDB() 호출
 *        └─ CardPayment → SeoulMetro : return
 */
public class CardPayment {

    /**
     * 정산 요청 처리
     * SeoulMetro의 TagOut에서 요금 계산 후 호출됨
     *
     * @param userId      결제 대상 사용자
     * @param fare        청구 요금
     * @param requestTime 정산 요청 시각
     */
    public void Settlement(UserId userId, Fare fare, LocalDateTime requestTime) {
        Objects.requireNonNull(userId, "UserId는 null일 수 없습니다.");
        Objects.requireNonNull(fare, "Fare는 null일 수 없습니다.");
        Objects.requireNonNull(requestTime, "RequestTime은 null일 수 없습니다.");

        System.out.printf("[CardPayment] 정산 요청 수신 - %s, 요금: %s, 요청시각: %s%n",
                userId, fare, requestTime);

        LocalDateTime settlementTime = LocalDateTime.now();
        SaveDB(userId, fare, settlementTime);
    }

    /**
     * 정산 결과를 DB에 저장 (private)
     *
     * @param userId         결제 대상 사용자
     * @param fare           청구 요금
     * @param settlementTime 실제 정산 완료 시각
     */
    private void SaveDB(UserId userId, Fare fare, LocalDateTime settlementTime) {
        // 실제 구현에서는 DB 저장 로직
        System.out.printf("[CardPayment] DB 저장 완료 - %s, 요금: %s, 정산시각: %s%n",
                userId, fare, settlementTime);
    }
}
