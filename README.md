# JavaFinalProject
12223704 김도훈 자바기반응용프로그래밍 기말 대체과제

게임 설명
"Subway game"

안뇽이는 오늘 코딩 테스트를 보는 날이지만 늦잠을 잤다. 최대한 빠르게 지하철을 타고 코딩 테스트를 보러 가야 한다.
지하철 노선도에는 1호선, 2호선, 3호선이 지나는 역이 점마다 놓여있다.
안뇽이는 출발역과 도착역을 골라야한다.
시작 버튼을 누르면 출발역부터 안뇽이는 지하철을 운행한다.
안뇽이가 늦지 않으려면 최단 경로로 가야한다. 
최단경로는 BFS를 이용하여 계산되고 그보다 많은 역을 지날 경우 안뇽이는 입장할 수 없다 ㅠㅠ.
도착역에 도착하면 안뇽이의 코딩테스트 참가 여부를 알 수 있다!

구성
Mainframe
TopPanel
Middlepanel
BottomPanel
ActionListeners

*()는 각 컴포넌트에 추가된 ActionListener입니다.

MainFrame 구성

TopPanel , MiddlePanel, BottomPanel 세 개의 패널을 BorderLayout으로 각각 북,중앙,남쪽에 배치한 후 
ActionListener들을 이용하여 panel마다 유기적인 상호작용을 하도록 구현하였습니다.

TopPanel 구성

TopPanel은 FlowLayout을 통해 한 줄에 컴포넌트들이 배치되도록 하였습니다.

Components
reset JButton /*(resetGameActionListener)*/ 게임을 다시 시작하고 싶을 때 누르는 버튼입니다.
title JLabel "Subway Game" 이라는 title을 중앙에 배치하였습니다.
file JButton (익명 클래스) 지하철의 이미지를 바꾸고 싶을 때 누르는 버튼입니다.

ActionListeners
//resetGameActionListener 구현 x
imageUploadActionListener 익명 클래스에서 MiddlePanel의 load 함수를 호출하여 원하는 이미지를 선택하고 MiddlePanel의 지하철(JLabel subway)의 이미지를 재설정합니다. subway와 동일하지는 않지만 적당한 사이즈로 이미지의 크기를 재설정하였습니다.(getScaledInstance를 인터넷 참고하였습니다. 파일 업로드를 하기 전 초기 이미지도 설정하고 싶어 JLabel을 이용하였고 JLabel에 이미지의 사이즈를 맞추는 데 어려움을 겪어 참고하였습니다.)(MiddlePanel 안에 있습니다.)

MiddlePanel 구성

MiddlePanel은 본래 GridLayout으로 설정하려했지만 subway의 잦은 좌표 재설정이 필요하고 지하철역과 지하철역을 연결하는 노선의 좌표를 임의로 설정하고 싶어 Layout을 null로 설정하였습니다. 
지하철역과 간선이 표시되어 있고 조종할 수 있는 지하철이 있습니다. 게임 시작 전 출발역과 도착역을 설정할 수 있고 게임이 시작하면 지하철을 직접 움직여 도착역까지 이동시킬 수 있습니다.

Components
JLabel subway 게임이 시작하면 조종하는 지하철입니다. 초기에는 "src/images/thomas.jpg" 이미지로 설정되어있으며, TopPanel의 file JButton을 누르면 원하는 이미지로 교체가능합니다. 게임이 시작하면 출발역으로 지하철의 좌표가 재설정되며 BottomPanel의 MoveSubKeyListener을 이용하여 지하철을 이동시킬 수 있습니다.
JLabel result 지하철이 도착역에 도착했을 경우 게임 성공 여부에 따라 "SUCCESS"와 "FAIL"이 나타난다. 기본적으로 setVisible(false) 되어있으며 도착과 함께 나타난다. //(아직 JLabel 속 문자 크기 설정이 되지 않음)
Line[] lines 지하철역을 연결하는 간선을 표시한다. class Line을 만들어 각 간선에 색을 저장하고 paintComponent 함수에서 각 간선의 좌표를 지정하고 MiddlePanel에 간선을 그렸다.
JPanel Station JButton station, JButton depart, JButton arrive이 저장되어 있는 하나의 지하철역 JPanel이다.
JButton station(curStationActionListener) 현 지하철 역이름을 저장하고 있는 버튼이다. 버튼을 누를 경우 출발역이나 도착역으로 지정할 수 있는 버튼이 나오고 고를 수 있다.
JButton depart(setStationActionListener) 버튼을 누를 경우 현 지하철 역을 출발역으로 설정한다.
JButton arrive(setStationActionLsitener) 버튼을 누를 경우 현 지하철 역을 도착역으로 설정한다.

ActionListeners
curStationActionListener 역버튼을 누르면 현재 역의 이름과 좌표를 변수에 저장합니다. 만약 똑같은 버튼을 다시 한 번 누를 경우 depart와 arrive 버튼이 사라집니다. MiddlePanel의 모든 컴포넌트를 검사하여 사라지거나 나타나게 설정하였습니다.
setStationActionListener 현재 역을 출발역 또는 도착역으로 설정할 수 있습니다. 누른 버튼이 depart인지 arrive인지 검사하고 현재역 이름이 저장된 변수를 받아 BottomPanel의 Jtextfield에서 나타냅니다. 그리고 모든 컴포넌트를 검사하여 depart버튼과 arrive버튼이 안 보이게 설정해줍니다. startX,startY,targetX,targetY 변수는 지하철의 초기,목적 좌표를 지정하는 것인데 y좌표를 -50씩 해준 이유는 버튼보다 지하철의 좌표는 y가 -50이기 때문입니다.

BottomPanel 구성

BottomPanel에는 제가 설정한 출발역, 도착역의 정보가 나타나며 출발버튼을 누를 경우 최소경로수가 나타납니다. 목적지에 도착할 때까지 지하철이 움직인 수가 실시간으로 바뀝니다. 만약 도착역에 도착하면 성공 여부가 표시됩니다.

Components
JTextField departStation 설정한 출발역의 이름이 나타납니다.
JTextField arriveStation 설정한 도착역의 이름이 나타납니다.
JButton start(StartGameActionListener) 시작 버튼을 누르면 게임을 진행할 수 있고, 최단경로수가 계산됩니다.
JTextField currentMoveNumField 0으로 설정되어있는 현재 이동 경로 수가 나타납니다. 게임이 시작하면 움직인 횟수만큼 다시 이동횟수가 나타납니다.
JTextField targetMoveNumField 게임이 시작하면 설정한 출발역부터 도착역까지의 최단경로수가 계산되어 나타납니다.

ActionListeners
StartGameActionListener 만약 출발역이나 도착역이 설정되지 않았거나, 출발역과 도착역이 같은 경우 게임을 실행하지 않습니다. 본래 각 값의 초기상태를 null로 하였으나, nullException이 계속 생겨 ""로 초기화해놓고 비교하였습니다. 지정된 출발역과 도착역의 정보를 가지고 dfs계산을 합니다. 계산된 최소경로수를 targetMoveNumField에 표시합니다. MiddlePanel의 숨겨지지않은 depart와 arrive버튼들을 모두 숨겨줍니다. 저장된 출발역 좌표에 지하철을 옮기고, 컨탠트팬에 MoveSubKeyListener을 추가하여 게임이 시작될 수 있도록 합니다.
MoveSubKeyListener 게임이 시작되면 키보드를 이용해 지하철을 이동시킵니다. 출발좌표는 역의 정보를 받아 조정되었지만, 따로 연관된 부분이 없어서 역의 정보를 사용하지 않고 독립적으로 지하철의 좌표를 재설정합니다. 상하좌우 키를 이용해서 지하철을 움직일 수 있고, 복잡하지만 각 키와 각 좌표에 따라 이동할 수 있는 지 여부를 저장한 배열을 만들었고 좌표를 검색했을 때 1이면 그 방향으로 이동할 수 있도록 설정하였습니다. 만약 지하철이 이동하였다면 현재 이동 횟수를 업데이트합니다. 그리고 arriveCheck함수를 이용하여 목표좌표에 도착했는지 확인하고, 도착했다면 현재 이동횟수와 목표 이동횟수를 비교하여 성공 여부를 판단하고 MiddlePanel에 여부를 표시합니다.

기타
현재 자료구조 수업에 배운 BFS를 활용하였습니다. 자료구조 수업의 수도코드와 제가 c++로 구현했던 그래프 및 BFS 코드를 바탕으로 Graph 클래스를 만든 후 BFS 함수를 구현하였습니다. Graph 클래스에는 각 vertex의 방문 여부와 연결 간선 정보가 저장되어있는 배열이 있고 초기화시켜주었습니다. DFS 함수는 출발 idx과 도착 idx를 받아 출발 idx부터 큐에 넣고 모든 vertex를 순환합니다. 수도코드에서는 queue를 여러 개 사용하여 경로수를 각 큐에 저장하였지만 저는 따로 distance배열을 만들고 각 idx에 도착했을 때의 distance를 모두 저장하였습니다. 그리고 distance[도착 idx]를 bottomPanel에 반환해주었습니다. edge,visited 배열에 접근하기 위해서 도착역과 출발역의 정보를 담은 hashmap을 만들고 각 역에 idx를 부여하여 구현을 원활하게 하였습니다.

문제점
현 버튼을 누르고 다른 버튼을 누른 후 다시 현 버튼을 두번 눌러야 depart와 arrive 버튼이 사라지는 문제점이 있습니다.
1 버튼을 눌러 setVisible이 되고 2 버튼을 눌러 setVisible이 되었을 때 1버튼의 출발 버튼을 누르면 2 버튼의 정보가 저장되는 오류가 있습니다.
맨 아래 두 역은 화면이 잘려 도착역으로 설정할 수 없습니다.
게임을 시작하고 도착역에 최소 경로로 도착했을 경우 SUCCESS가 뜨고도 키보드를 움직일 수 있어서 다른 역에 갔다오면 FAIL이 출력됩니다.
**게임을 한 번 진행하고 다시 출발역과 도착역을 설정하여 게임을 시작하면 지하철이 2칸,4칸씩 움직이는 오류가 있습니다. 초기화 버튼을 구현하지 못했고 이동횟수도 아직 초기화시키지 않아 지금은 한 번 실행할 경우 한 번만 게임을 진행하는 것이 목표였다고 할 수 있겠습니다..

아쉬운 점 및 느낀 점
Station 클래스에 모든 정보를 저장하고, 지하철과도 유기적인 관계를 설정했다면 더욱 깔끔하지 않았을까 하는 아쉬움이 있습니다. 코드는 개인적으로 긴 편이라고 생각했지만 readme 파일을 작성하면서 생각해보니 비효율적이라는 생각을 하였습니다. subway를 이동시킬 때 Station의 정보를 통해 움직이지 않고 따로 배열을 만들어 이동시키는 부분이 아쉬웠습니다. subway의 상하좌우 이동여부를 저장하는 배열은 subway좌표를 50으로 나눈 배열에 저장하였는데 0으로 초기화 시킨 후 원하는 부분에만 1을 부여했다면 더욱 깔끔했을 것 같습니다. 패널을 나누고 각 패널마다 정보를 넘기는 부분에서 어려움을 겪었습니다. 지금도 MainFrame안에 저장되어있는 정렬되지 않은 변수나 함수가 많고, 특히 BottomPanel에서 MiddlePanel로 정보를 옮기는 부분에서 어려움을 겪어 BottomPanel은 안에 다시 middlePanel를 선언, 받은 middlePanel를 저장했고 이 부분이 깔끔하지 않은 것 같습니다. 패널의 컴포넌트를 검사하여 arrive와 depart 버튼을 사라지게 하는 코드도 매우 중복되어있어 따로 저장하면 좋을 것 같았지만 조금씩 달라서 아직 설정하지 못했습니다. 본래 지하철 앱과 같이 출발역과 도착역을 지정하면 환승 여부 및 모든 이동 역을 표시하는 프로그램을 만드려고 계획했으나 지금 배운 BFS만을 사용하기에는 어려움이 있었고 지하철 게임으로 변화를 주어 배운 BFS를 활용해보면서 재밌었습니다. 이번 프로젝트를 하면서 오류를 해결하는 부분에서 많은 시행착오를 겪었고 해결해내면서 성취감도 느껴본 것 같고, 액션리스너를 조금 더 잘 활용할 수 있을 것 같다고 느꼈습니다. 이번 경험을 통해 다음에는 코드를 더욱 효율적으로 짜고, 깔끔하게 짜볼 수 있을 것 같습니다. 
