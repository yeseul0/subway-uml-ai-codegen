package subway;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 승차 기록 (SeoulMetro의 rideRecords Map 의 값 타입)
 * TagIn 호출 시 생성되어 UserId를 키로 저장됨
 */
public record RideRecord(Station boardingStation, LocalDateTime boardingTime) {

    public RideRecord {
        Objects.requireNonNull(boardingStation, "승차역은 null일 수 없습니다.");
        Objects.requireNonNull(boardingTime, "승차 시간은 null일 수 없습니다.");
    }

    @Override
    public String toString() {
        return "RideRecord{승차역=" + boardingStation + ", 승차시간=" + boardingTime + "}";
    }
}
