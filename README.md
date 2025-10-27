# 자동차 경주

여러 대의 자동차가 주어진 횟수 동안 전진하거나 멈추는 **자동차 경주 게임**이다.  

- 각 자동차는 **이름을 가지며**, 이름은 쉼표(`,`)로 구분되고 **5자 이하**여야 한다.

- 매 시도마다 각 자동차에 대해 **0부터 9 사이의 무작위 값**을 생성한다.  
- 이 값이 **4 이상이면 해당 자동차는 전진**하고 미만이면 멈춘다.

- **입력한 횟수만큼 반복**하여 **가장 많이 전진한 자동차(들)** 가 우승한다.  
- 우승자가 여러 명일 경우 쉼표(`,`)로 구분해 함께 출력한다.

---

## 입출력 요구 사항

### 입력
- 경주할 자동차 이름(이름은 쉼표(,) 기준으로 구분)
- 시도할 횟수

### 출력
- 차수별 실행 결과
- 최종 우승자 안내 문구

### 실행 결과 예시
```text
경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)
pobi,woni,jun
시도할 횟수는 몇 회인가요?
5

실행 결과
pobi : -
woni :
jun : -

pobi : --
woni : -
jun : --

pobi : ---
woni : --
jun : ---

pobi : ----
woni : ---
jun : ----

pobi : -----
woni : ----
jun : -----

최종 우승자 : pobi, jun
```
---
## 프로그래밍 요구 사항
- `Java 21`에서 실행 가능해야 한다.
- 프로그램 종료 시 `System.exit()`를 호출하지 않는다.
- `camp.nextstep.edu.missionutils.Randoms`의 `pickNumberInRange(0, 9)` 사용.
- `camp.nextstep.edu.missionutils.Console`의 `readLine()` 사용.
- `Application.main()`에서 프로그램이 시작되어야 한다.
- 들여쓰기 `depth`는 `2` 이내로 유지한다.
- 3항 연산자를 쓰지 않는다.
- 한 메서드는 한 가지 일만 수행하도록 분리한다.
- `JUnit 5`와 `AssertJ`를 사용해 기능별 테스트를 작성한다.

---

## 기능 목록

### 이름 입력 및 검증
- [x] 쉼표("," )로 구분된 이름 문자열을 입력받는다.
- [x] 이름 목록(`Names`)을 생성하고, 내부에서 검증을 수행한다.

### 경주 횟수 입력 및 검증
- [x] 숫자 문자열로 시도 횟수를 입력받는다.
- [x] 경주 횟수(`Round`)를 생성하고, 내부에서 유효성 검증을 수행한다.

### 게임 진행
- [x] 입력받은 `Names`로부터 `Cars`를 생성한다.
- [x] 이동 전략(`MoveStrategy`)과 난수 생성기(`NumberGenerator`)를 준비한다.
- [x] 라운드 수(`Round`)만큼 다음을 반복한다:
    - [x] 각 자동차에 대해 전략 평가 후 전진 여부를 결정한다.
    - [x] 현재 라운드 상태를 스냅샷(`RoundDto`)으로 기록한다.
- [x] 최종 우승자(복수 가능)를 판정하고 `ResultDto`로 변환한다.

### 결과 출력
- [x] 차수별 실행 결과를 출력한다.
- [x] 최종 우승자를 쉼표(",")로 구분해 출력한다.
- [x] 잘못된 입력 등 예외 발생 시 에러 메시지를 출력한다.

---

## 예외 상황

### 이름(Name)
- 이름이 null인 경우
- 이름이 빈 문자열("")이거나 공백만 포함된 경우
- 이름 길이가 5자를 초과하는 경우

### 이름 목록(Names)
- 입력이 null, 빈 문자열, 공백만 포함된 경우
- 이름이 2개 미만인 경우
- 중복된 이름이 존재하는 경우

### 라운드(Round)
- 입력이 null, 빈 문자열, 공백만 포함된 경우
- 숫자가 아닌 값이 입력된 경우
- 숫자가 1 미만인 경우

---

## 프로젝트 구조

```markdown
├─ src/main/java/
│  ├─ racingcar/
│  │  ├─ Application.java
│  │  ├─ config/
│  │  │  └─ AppConfig.java
│  │  ├─ controller/
│  │  │  └─ RacingController.java
│  │  ├─ dto/
│  │  │  ├─ CarDto.java
│  │  │  ├─ ResultDto.java
│  │  │  └─ RoundDto.java
│  │  ├─ model/
│  │  │  ├─ game/
│  │  │  │  ├─ RacingGame.java
│  │  │  │  ├─ car/
│  │  │  │  │  ├─ Car.java
│  │  │  │  │  └─ Cars.java
│  │  │  │  ├─ name/
│  │  │  │  │  ├─ Name.java
│  │  │  │  │  └─ Names.java
│  │  │  │  └─ round/
│  │  │  │     └─ Round.java
│  │  │  ├─ generator/
│  │  │  │  ├─ NumberGenerator.java
│  │  │  │  └─ RandomNumberGenerator.java
│  │  │  └─ strategy/
│  │  │     ├─ MoveStrategy.java
│  │  │     └─ RacingMoveStrategy.java
│  │  └─ view/
│  │     ├─ InputView.java
│  │     └─ OutputView.java
```