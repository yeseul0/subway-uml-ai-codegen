package subway;

/**
 * MobileApp 구체 구현체
 * 실제 모바일 앱 사용자를 나타냄
 */
public class SubwayMobileApp extends MobileApp {

    private final String deviceId;

    public SubwayMobileApp(UserId userId, String deviceId) {
        super(userId);
        this.deviceId = deviceId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    @Override
    public String toString() {
        return "SubwayMobileApp{userId=" + getUserId() + ", deviceId=" + deviceId + "}";
    }
}
