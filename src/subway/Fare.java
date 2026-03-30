package subway;

/**
 * 요금 (calculateFare 반환 타입 및 Settlement 매개변수에 대응)
 */
public record Fare(int amount) {

    public Fare {
        if (amount < 0) {
            throw new IllegalArgumentException("요금은 0원 이상이어야 합니다.");
        }
    }

    @Override
    public String toString() {
        return amount + "원";
    }
}
