package subway;

import java.util.Objects;

/**
 * 지하철 역 정보 (TicketGate의 Station 속성에 대응)
 */
public record Station(String name, int stationNumber) {

    public Station {
        Objects.requireNonNull(name, "역 이름은 null일 수 없습니다.");
        if (name.isBlank()) {
            throw new IllegalArgumentException("역 이름은 빈 값일 수 없습니다.");
        }
        if (stationNumber < 0) {
            throw new IllegalArgumentException("역 번호는 0 이상이어야 합니다.");
        }
    }

    @Override
    public String toString() {
        return name + "역(#" + stationNumber + ")";
    }
}
