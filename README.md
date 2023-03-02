# Tetris
java swing을 이용한 tetris

### 조작키
- "→" : 미노를 오른쪽을 이동
- "←" : 미노를 왼쪽으로 이동
- "↓" : 미노를 한칸 아래로 이동
- "Space" : 미노를 맨 아래로 이동 
- "↑" or "x" : 미노를 오른쪽 방향으로 회전
- "Control", "z" : 미노를 왼쪽 방향으로 회전
- "Shift", "c" : 미노를 저장 / 저장한 미노 가져오기
- "e" : 게임 종료



### 구현 사항
- #### 블록 미리보기, 미노 저장, 미노의 그림자 구현

  미노가 떨어질 위치를 알려주는 그림자를 구현
  
  <img width="691" alt="image" src="https://user-images.githubusercontent.com/105146508/222311095-bb4c6441-277c-48be-8b33-7a2603779726.png">

- #### T-Spin 구현

  미노가 오른쪽, 왼쪽으로 회전하지 못하는 상태에서 회전 가능한 위치를 찾아서 미노를 회전

  - 회전이 불가능한 위치
  ![image](https://user-images.githubusercontent.com/105146508/222311937-d2242633-d85e-44e7-a7cd-41dc73df89a9.png)
  
  - 회전이 가능한 위치를 찾아 회전
    <img width="839" alt="image" src="https://user-images.githubusercontent.com/105146508/222312053-9a47b756-e36f-44f0-b2bf-2ddf0b630831.png">

     T-Spin 관련 참고 문서: <<https://tetris.fandom.com/wiki/SRS>>

### 플레이 화면
<img width="676" alt="image" src="https://user-images.githubusercontent.com/105146508/222309611-6440d63c-cf18-4040-a4bd-98164bd424de.png">



### Diagram  



