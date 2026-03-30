## 개요

UML 다이어그램을 기반으로 AI가 얼마나 정확하게 코드를 생성할 수 있는지 탐구하는 프로젝트입니다.

모바일 기기 기반 지하철 출입 및 결제 시스템을 Class Diagram과 Sequence Diagram으로 모델링한 후, AI에게 Java 코드 생성을 요청하고 그 결과를 분석합니다. 
---

## 시스템 설명

1. `MobileApp`이 `TicketGate`에 사용자 ID를 전달
2. `TicketGate`가 `SeoulMetro`에 태그 정보를 전달
   - 승차 시: 사용자 ID, 태그 시각, 역 이름
   - 하차 시: 동일 정보 + 요금 계산 요청
3. `SeoulMetro`가 승/하차 역 기준으로 요금을 계산하여 `CardPayment`에 정산 요청
4. `CardPayment`가 정산 완료 후 DB에 저장
---

## UML 다이어그램
- `uml.pdf` — Class Diagram + Sequence Diagram (draw.io로 작성)
---

## AI 코드 생성 프롬프트
```
첨부된 PDF에는 모바일기기 기반 지하철 출입 및 결제 시스템의 
UML Class Diagram과 Sequence Diagram이 포함되어 있어.

주의해야할 점은 
PDF의 클래스 다이어그램 구조를 그대로 유지할 것, 
SeoulMetro 클래스는 UserId를 키로 승차 정보를 관리할 것,
하차 시 승/하차 역을 기준으로 요금을 계산하여 CardPayment 클래스에 정산 요청할 것, 
시퀀스 다이어그램의 흐름대로 메서드 호출 순서를 구현할 것이야.

Java 17 코드로 구현해줘
```
---

## 분석 내용
* 클래스 구조 충실히 반영했는가
* 시퀀스 흐름이 정확하게 구현되었는가
* UML과 다른 부분
* insight
---

## 사용 도구

- UML 모델링: draw.io
- 코드 생성 AI: Claude Code
- 구현 언어: Java 17