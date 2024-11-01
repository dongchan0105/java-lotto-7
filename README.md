# Java Lotto Precourse

## 기능 요구 사항

간단한 로또 발매기를 구현합니다.

- **로또 번호의 숫자 범위**: 1~45
- **1개의 로또 발행 시**: 중복되지 않는 6개의 숫자를 뽑습니다.
- **당첨 번호 추첨**: 중복되지 않는 숫자 6개와 보너스 번호 1개를 뽑습니다.
- **당첨 등급**은 1등부터 5등까지 있으며, 당첨 기준과 금액은 아래와 같습니다:

| 등급 | 기준                           | 당첨 금액       |
| ---- | ------------------------------ | --------------- |
| 1등  | 6개 번호 일치                  | 2,000,000,000원 |
| 2등  | 5개 번호 + 보너스 번호 일치    | 30,000,000원    |
| 3등  | 5개 번호 일치                  | 1,500,000원     |
| 4등  | 4개 번호 일치                  | 50,000원        |
| 5등  | 3개 번호 일치                  | 5,000원         |

- **로또 구입 금액**을 입력하면 해당 금액만큼 로또를 발행합니다. (로또 1장의 가격은 1,000원)
- **당첨 번호**와 **보너스 번호**를 입력받아, 사용자가 구매한 로또 번호와 당첨 번호를 비교하여 **당첨 내역 및 수익률**을 출력하고 로또 게임을 종료합니다.
- **예외 처리**: 사용자가 잘못된 값을 입력할 경우 `IllegalArgumentException`을 발생시키며, `[ERROR]`로 시작하는 에러 메시지를 출력한 후 해당 입력을 다시 받습니다.
    - `Exception`이 아닌 `IllegalArgumentException`, `IllegalStateException` 등과 같은 명확한 유형을 처리합니다.

---

## 입출력 요구 사항

### 입력

- 로또 구입 금액을 입력받습니다.
    - **구입 금액**은 1,000원 단위로 입력받으며, 1,000원으로 나누어 떨어지지 않는 경우 예외 처리를 합니다.
- 당첨 번호를 입력받습니다.
    - **번호는 쉼표(,)를 기준**으로 구분합니다.
- **보너스 번호**를 입력받습니다.

### 출력

- 발행한 로또의 수량과 번호를 출력합니다.
    - 로또 번호는 **오름차순**으로 정렬하여 보여줍니다.
- **당첨 내역**을 출력합니다.
- **수익률**은 소수점 둘째 자리에서 반올림하여 출력합니다.
    - 예시: `100.0%`, `51.5%`, `1,000,000.0%`
- 예외 상황 발생 시, **에러 메시지는 `[ERROR]`로 시작**하여 출력합니다.

## 구현할 기능 목록

- 입력 기능사항: 
  1. 사용자로부터 구입금액을 입력받아온다 로또 1장의 가격은 1000원이다(이때 1000원 단위로 나눠지지 않으면 예외처리한다)
  2. 로또 당첨 6자리 번호를 쉼표로 구분해 입력한다. 이때(1~45 숫자 범위를 벗어나면 예외처리한다)
  3. 보너스 번호를 입력한다(마찬가지로 숫자의 범위가 벗어나거나 2개이상의 보너스 번호가 들어오면 예외처리한다)

- 데이터 처리 기능사항:
  1. 입력받은 가격에 맞춰서 로또를 발행한다 이때 로또는 1~45 숫자중 중복되지 않는 6개의 숫자이다
     -이때 Random 값 추출은 camp.nextstep.edu.missionutils.Randoms의 pickUniqueNumbersInRange()를 활용한다.
  2. 로또결과를 다음 조건표와 같이 순위표를 Enum Class로 생성한다
    - 이때 Enum에는 로또의 일치 개수,상금,메시지가 담겨있어야한다
    - 조건표:
      1등: 6개 번호 일치 / 2,000,000,000원
      2등: 5개 번호 + 보너스 번호 일치 / 30,000,000원
      3등: 5개 번호 일치 / 1,500,000원
      4등: 4개 번호 일치 / 50,000원
      5등: 3개 번호 일치 / 5,000원
  3. 생성 해둔 로또를 당첨번호와 보너스 번호와 비교하며 조건표 기준 등수를 확인한다
  4. 등수별로 몇개의 로또가 당첨되었는지 확인하고 이를 당첨금액과 곱해 총 상금을 계산한다
  5. 투입한 금액 대비 몇퍼센트의 수익률을 얻었는지 계산한다

- 출력 기능사항:
  1. 구입금액에 맞는 로또의 수와 무작위로 만들어진 로또들을 배열로 반환한다.
  2. 당첨번호와 갖고있는 로또를 비교해 위의 조견표와 비교하여 로또 당첨 등수 각각 개수를 조건표 내용과 함께 출력한다
  3. 총 수익률을 반환한다

- 예외처리 기능사항: 
  1. 사용자 구입금액 입력이 1000원단위가 아닐때
  2. 사용자의 로또 번호가 1~45자리수를 벗어날때
  3. 로또 번호에 중복된 숫자가 입력되었을때
  4. 사용자에게 입력받는 번호가 6자리를 넘을때
  5. 보너스 숫자가 2개이상 들어올때
  6. 입력에 숫자 이외에 다른 숫자가 들어왔을때
  



