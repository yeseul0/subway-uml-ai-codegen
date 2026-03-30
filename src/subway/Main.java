package subway;

/**
 * 지하철 출입 및 결제 시스템 실행 예시
 *
 * Sequence Diagram 전체 흐름 시뮬레이션:
 *   1. User → TicketGate.Tag(UserId)           [승차]
 *   2. TicketGate → SeoulMetro.TagIn(...)
 *   3. User → TicketGate.Tag(UserId)           [하차]
 *   4. TicketGate → SeoulMetro.TagOut(...)
 *       └─ SeoulMetro.calculateFare(src, dest)
 *       └─ CardPayment.Settlement(...)
 *           └─ CardPayment.SaveDB(...)
 */
public class Main {

    public static void main(String[] args) {
        // ── 시스템 객체 초기화 ──────────────────────────────────────
        CardPayment cardPayment = new CardPayment();
        SeoulMetro seoulMetro = new SeoulMetro(cardPayment);

        // 역 정의 (역 번호 = 거리 근사값)
        Station gangnam  = new Station("강남",  2);
        Station hongik   = new Station("홍대입구", 18);

        // 개찰구: 강남역, 홍대입구역
        TicketGate gateGangnam = new TicketGate(gangnam,  seoulMetro);
        TicketGate gateHongik  = new TicketGate(hongik, seoulMetro);

        // 사용자 (Mobile App)
        UserId userId = new UserId("user-001");
        SubwayMobileApp app = new SubwayMobileApp(userId, "iPhone-XYZ");

        System.out.println("=".repeat(55));
        System.out.println("  지하철 출입 및 결제 시스템 시뮬레이션");
        System.out.println("=".repeat(55));

        // ── [승차] 시나리오 ─────────────────────────────────────────
        System.out.println("\n▶ [승차] 강남역 개찰구 태그");
        System.out.println("-".repeat(55));
        app.requestTag(gateGangnam);   // User → TicketGate.Tag(UserId)

        // ── [하차] 시나리오 ─────────────────────────────────────────
        System.out.println("\n▶ [하차] 홍대입구역 개찰구 태그");
        System.out.println("-".repeat(55));
        app.requestTag(gateHongik);    // User → TicketGate.Tag(UserId)

        System.out.println("\n" + "=".repeat(55));
        System.out.println("  시뮬레이션 완료");
        System.out.println("=".repeat(55));
    }
}
