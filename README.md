# #Extends Othello Game

> 프로그램 종류 : 게임, GUI ,AI competitive
>
> 사용 언어 : Java 8 JDK 1.8.0_181
>
> 사용 라이브러리 : None
>
> 프로젝트 소스 링크 : https://github.com/murbong/ExtendOthello

------

## 설명

> **오델로 게임이 뭔가요?**
>
> 오델로는 자기가  놓을 돌과 자기의 돌 사이에 상대편의 돌이 있으면  상대편의 돌을 내  돌로 바꾸는 게임입니다.
>
> 돌은 가로, 세로, 대각선으로 탐지하고, 만약 자기 차례가 되었는데, 놓을 곳이 없다면 패스합니다.
>
> 흑과 백 모두 돌을 놓을 수 없으면 게임이 끝나고, 돌이 많은쪽이 승리합니다.

------

![othello](C:\Users\wc100\Pictures\othello.PNG)

깔끔한 GUI를 자랑한다.

![init](C:\Users\wc100\Pictures\init.PNG)

Play Offline을 누르면 16X16의 게임 화면이 뜬다. 

![default](C:\Users\wc100\Pictures\default.PNG)

몇번 수를 둔 상황. 흑돌은 Player, 흰돌은 Computer이다. Computer 알고리즘은 따로 후술하겠다.

![흑돌승리](D:\eclipse-workspace\Othello\흑돌승리.png)

![흰돌승리](D:\eclipse-workspace\Othello\흰돌승리.png)

![무승부](D:\eclipse-workspace\Othello\무승부.png)

> 만약 게임이 끝나면, 텍스트이미지로 되어있는 WinDialog가 뜨면서 메인 화면으로 돌아가게 된다.



------



## 소스 코드 / 알고리즘

![map](C:\Users\wc100\Pictures\map.png)

16x16의 맵을 흑돌 1, 백돌 -1로 표현했다.

![알고리줌](C:\Users\wc100\Pictures\알고리줌.PNG)

PUT에 흑돌을 넣는다. 만약 주변에 다른 색 돌이 있다면 T=1로 두고, T가 1일때 같은 색 돌을 만나면 사이의 돌 색깔을 바꾼다. 만약 끝까지 다른색 돌이라면 아무 기능도 하지 않는다.

![알고리즘ㄴ](C:\Users\wc100\Pictures\알고리즘ㄴ.PNG)

Seeking 알고리즘. 이 알고리즘은 윗방향으로 검사한다. 이러한 알고리즘이 8개가 있다. (전후좌우, 대각선)

알고리즘의 모드는 3가지가 있는데, 기초적인 is_putable을 return하는 모드, 사이의 돌 색깔을 바꾸는 SetMapComponent 모드, 현재 자리에서 몇개의 돌을 먹을수 있는지 확인하는 Count return 모드가 있다.

![알](C:\Users\wc100\Pictures\알.PNG)

다음은 ComputeClick(int x,int y) 메소드 기능중 하나로, 현재 위치에 착석가능하고 비어있다면, Seeking 알고리즘을 실행한다. 그리고 맵 전체를 분석해 현재 돌의 갯수와 착석 할 수 있는 위치가 몇개인지 살펴본다.

![고](C:\Users\wc100\Pictures\고.PNG)

만약 둘다 놓을 곳이 없거나, 흑돌, 백돌 총합이 maxXY의 제곱을 넘거나 같다면, 승자를 정한다.

![리](C:\Users\wc100\Pictures\리.PNG)

아니면, 턴에 -1를 곱해서(흑은 1, 백은 -1) 턴을 바꾼다. 만약 현재 턴이 백돌인데 백돌이 놓을 곳이 없다면 다시 턴을 바꾼다.

![즘](C:\Users\wc100\Pictures\즘.PNG)

마우스 리스너를 이용하여 클릭한 좌표를 맵핑한다. 여기서 maxsize는 한 칸의 크기이고, 이것으로 마우스 좌표를 나눈 값이 Map의 위치를 의미한다. 이 좌표로 ComputeClick(x,y) 메소드를 실행한다.

만약 Winner가 정해진다면 모든 컨테이너를 지운 뒤, Title로 돌아간다.

아니면 **Computer** 객체인 com의 가중치를 설정하는 작업을 한다.

Timer를 사용하여 사용자가 클릭 한 뒤 1초 뒤에 상대 AI가 가장 높은 가중치를 가진 좌표로 ComputeClick(x,y)   메소드를 실행한다.

![gui](C:\Users\wc100\Pictures\gui.PNG)

다음은 GUI 부분 소스코드이다. **JPanel**의 **paintComponent**를 오버라이딩하여 격자무늬와 돌을 그린다.

_turn+3을 하여 애니메이션인지 구분한다. 2이면 White의 애니메이션, 4이면 Black의 애니메이션이다.

turn이 흑돌이면 배경도 흑색, 백돌이면 백색이 된다.

그리고 애니메이션을 계속해서 동작시키려면 GUI를 계속 새로고쳐야하는데, 이는 **Timer**를 사용하여 해결했다.

![타임](C:\Users\wc100\Pictures\타임.PNG)

![리페인트](C:\Users\wc100\Pictures\리페인트.PNG)

How to make AI?

![전략](C:\Users\wc100\Pictures\전략.PNG)

8x8 오델로의 전략이다. 만약에 X를 먹으면 견제를 받지 않고 돌을 뒤집을 수 있어 매우 좋은 자리이다.

하지만 ※모양의 자리는 상대방이 X를 먹을 가능성이 커져서 피하는 것이 좋은 자리이다.

그리고 모퉁이를 먹을 확률은 첫 인덱스를 (0,0)이라 가정하면 짝수 자리에서 커진다. 

> 가중치의 초기 값은 Seeking 알고리즘 모드 2의 리턴값 '현재 위치에서 몇개의 돌을 먹을수 있나?' 이다.

다음은 클래스 Computer의 구현 방법이다.

![가중치](C:\Users\wc100\Pictures\가중치.PNG)

이렇게 프로그래밍 하면...

![악랄함](C:\Users\wc100\Pictures\악랄함.PNG)

> 흰 돌은 무조건 모서리 우선으로 먹게 된다. 얼마나 악랄한가? 생각없이 두면 질 정도다.

