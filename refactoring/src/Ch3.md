# Ch3. Bad smells in Code

켄트 벡은 when을 smell이라는 용어로 설명했다. 아이의 냄새.

### Mysterious Name

- 좋은 이름이 생각나지 않으면 더 깊은 디자인이 필요하다는 신호이다. 이름을 변경하는 것이 아닌 다른 작업이 먼저 필요하기도.

## Duplicated Code

- Slide Statements는 코드를 비교해보기 좋은 리팩터링이다.
- IntelliJ의 코드 중복 체크 기능이 유용하다. 리팩터링도 한방에.

## Long Function

- 네비게이션이 편한 모던 IDE의 등장은 함수를 쪼개는 것을 두렵지 않게 만들어준다. 
- 무엇을 하는지를 설명해주지 않는, 어떻게 하는지만 보이는 코드는 이름을 가진 함수로 분리한다.
- 조건문과 루프는 분리하기 좋은 대상

## Long Parameter List

## Global Data

## Mutable Data

- 계속 나오는 거지만 class를 만드는 것을 두려워하지 말자.
 
## Divergenet Change

## Shotgun Surgery

- 어설프게 분리된 로직을 끌어와서 일단 Long method, Large class로 만든 뒤에 의미있는 단위로 추출!
- 중간단계로 가는 리팩터링을 잘 쓸 줄 알아야겠다.

## Feature Envy

- 전략 패턴과 비지터 패턴은 변경 행위를 한 곳으로 모으로 고립시킨다. 특별한 기법.

## Data Clumps

## Primitive Obsession

- VO를 만들자

## Repeated Switches

- 유사한 Switch가 반복되면 위험한 신호. 무조건 다형성을 쓰지 말고, 대신 switch는 분리해두자.

## Loops

## Lazy Element

## Speculative Generality

- 확장성을 제거하는 것도 리팩터링이다.

## Temporary Field

## Message Chains

## Middle Man

## Insider Trading

## Large Class

- 클래스의 클라이언트가 클래스를 쪼개는 단서가 된다. 클라이언트를 보는 게 중요하다.

## Alternative Classes with Different Interfaces

## Data Class

- 원격 호출 결과를 담은 데이터는 예외. 이런 건 immutable이다.

## Refused Bequest

- 구현만 상속하고 있다면 이건 delegate로 바꾸자.

## Comments

- 코멘트가 필요한 시점은 뭘 할지 모를때. 미래의 수정을 위한 정보, 자칫 잊기 쉬운 고민들을 기록해두자.
































