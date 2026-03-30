## 분석 결과

---

### 잘 된 부분

**1. 클래스 구조 충실히 반영**
- `MobileApp`, `TicketGate`, `SeoulMetro`, `CardPayment` 4개 클래스 모두 존재
- `rideRecords : Map<UserId, RideRecord>` 속성 정확히 구현
- `calculateFare`, `SaveDB` private 접근제어 정확

**2. 시퀀스 흐름 정확**
- `Tag()` → `TagIn()/TagOut()` → `calculateFare()` → `Settlement()` → `SaveDB()` 순서 완벽
- alt 분기를 `hasRideRecord()`로 깔끔하게 구현

---

### UML과 다른 부분

**1. MobileApp이 abstract class로 구현됨**
- UML 다이어그램에서 이탤릭체는 abstract class를 의미, but 내 의도는 아니었음
- `SubwayMobileApp`이라는 구체 클래스가 추가됨 → UML에 없는 클래스
> 나보다 UML 표기법을 제대로 이해하고 있는 AI가 맞았다고 생각. 
**2. `RideRecord`가 별도 클래스로 구현됨**
- Map 속성으로만 표현했으나 AI가 자체적으로 클래스로 분리함
- 설계적으로는 좋은 선택이기도 하고 실제로 UML에 RideRecord 클래스에 대한 정보를 나타내야하는지 고민했다가 넘겼던 부분이었음.
> UML 작성 시 타입명이 AI 코드 생성에 직접적인 영향을 미친다는 점. 

**3. `Station`이 별도 클래스로 구현됨**
- UML에서 Station은 속성값이었으나 독립 클래스로 분리됨
> 아마도 calculateFare 함수에서 계산을 해야하기 때문에 Station마다 계산 가능한 어떤 int가 필요하다고 생각해서 Station 클래스를 분리한 것 같다. 

**4. `hasRideRecord()` 메서드 추가**
- UML에 없는 메서드이나 승/하차 분기를 위해 AI가 자체 추가
> alt 에 분기 조건 [승차]/[하차]로만 해놔서 그런 것 같음. 태그기 카드 태그 위치에 따라서 TagIn, TagOut 함수를 따로 뒀어야 했다. 

---

### 💡 흥미로운 점

**AI가 UML의 빈틈을 스스로 채웠다.**

UML에서 승/하차를 어떻게 구분할지 명시하지 않았음에도,
AI가 `hasRideRecord()`를 추가하여 분기 로직을 스스로 설계함.

이는 UML이 구조와 흐름을 정의하지만, 구체적인 구현 판단은
여전히 개발자의 몫임을 보여준다고 생각함.

지금은 시스템의 규모가 작고 예상가능한 흐름이기에 잘 마무리 되었지만,
더 크고 복잡한 시스템에 있어서는 위험할 수 있다. 

---

### 결론 (나의 생각)

AI는 UML의 설계 의도를 전반적으로 잘 이해하고 구현했으나,
명시되지 않은 부분에서는 자체적인 판단으로 클래스와 메서드를 추가했다.
UML 규칙에 맞춰서 나의 모든 의도를 다이어그램화 할수만 있다면 자연어 바이브 코딩보다 훨씬 높은 정확도의 코드를 얻을 수 있을 것으로 생각함.
가장 중요한 것은 나또한 UML 규칙을 제대로 이해하고 있어야하며 정확한 설계 능력이 필요하다는 점이다. 