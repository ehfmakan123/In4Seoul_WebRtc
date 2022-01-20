websocket 정리







1. websocket 이란?


 - TCP 통신 방식으로 서버와 클라이언트 사이에 데이터를 주고 받을 수 있는 기술
(클라이언트간 양방향 통신 기술)
 
- 내가 원하는 정보에 대해 구독을 신청하고, 토픽에 대한 메시지를 발행하면 해당 토픽을 구독하고 있는 모든 사용자들에게 보내는 방식.

- 서버와 브라우저간 양방향 소통이가능 , 하나의 HTTP접속으로 양방향 메시지 제한 X



2. why use?

 - 기존의 양방향 통신(url을 통한 요청{새로운 페이지 갱신} -> ajax통신{부분 갱신 가능})은 HTTP를 이용하기 때문에 변경된 데이터를 가져오기 위해 call ,request 가 필요함
(ajax는 요청이 없는 경우 갱신 x -> http )

- 서버가 각각의 클라이언트에 다른 TCP 연결 사용(정보 , 메시지 수신)

- HTTP 헤더를 가진 메시지의 오버헤드가 높음(간접 처리시간 증가)

- 응답을 받기위해 클라이언트쪽 스크립트가 강제로 매핑을 유지해야함 

-> 위의 이유로 자원이 낭비되므로 이를 해결하기 위해 websocket을 사용

* 기존 HTTP를 이용한 통신방법 -> polling , longpolling , streaming 


3. 동작 원리

- 서버와 클라이언트간 HTTP프로토콜을 이용하여 이루어진다 

- 정상적으로 연결이 이루어진다면 서버와 클라이언트간 연결 , 이후 일정 시간이 지나면 HTTP 연결이 끊어짐


- HTTP를 이용해 HANDSHAKE(websocket 사용 가능 여부) -> HTTP protocol을 websocket protocol 으로 바꾸는 protocol switching -> 웹 소켓을 위한 새로운 소켓이 만들어 지고 이 소켓을 이용하여 통신 (ws / wss protocol)




4. 어떻게 프로젝트에서 구현할것인가...?


 - sockjs 사용 할 예정 (springframework에서 웹 소켓 지원)

*sockjs를 이용하면 websocket을 지원하지 않는 브라우저 
(ex - IE 구버전 , 모바일 크롬) 에서는 웹소켓 방법대신 기존 http를 이용한 방법으로 타 브라우저에서도 사용이 가능하다


- STOMP 
특정 토픽의 특정 유저에게만 메시지를 보내는 방법을 담당하는 protocol


- 화상 안내시 1:1 채팅방 기능 , 유저(안내데스크)에서 호출 버튼 클릭 시 해당 지역의 안내원에게 알람이 가도록..! -> 알람받는걸 쪽지 형식으로 보내면?? 








5. 샘플코드...?

5-1 채팅방

1) dependecny 추가

dependencies{
	compile('org.projectlombok:lombok') // lombok
	compile("org.springframework.boot:spring-boot-starter-websocket")
	compile("org.webjars:webjars-locator")
	compile("org.webjars:sockjs-client:1.0.2") // scokjs
	compile("org.webjars:stomp-websocket:2.3.3") // stomp
	compile("org.webjars:bootstrap:3.3.7")
	compile("org.webjars:jquery:3.1.0") // jquery
	testCompile("org.springframework.boot:spring-boot-starter-test")
}

2)model 설정
username(유저 아이디) , contents ( 내용) , sendDate (메세지 시간..?) 
3) Contorller 
@MessageMapping 
@SendTo : 메소드 반환 객체를 모든 유저에게 전송 
@SendToUser : 1:1 채팅용

4)Config 설정

@EnableWebSocketMessageBroker : 웹 소켓 메시지 처리

5) 나머지 front 작업
	- vue , script 작업 
	- 소켓 연결 / 소켓 연결 끊음 / 메시지 전달 / 구독한 데이터 표현 구현


일단은 화상 미팅 화면은 따로 생각하고 화면창에 채팅방만 구현 예정






*참고자료 출처

https://adrenal.tistory.com/20 [webSocket 으로 개발하기 전에 알고 있어야 할 것들]
https://yuricoding.tistory.com/134 [웹소켓 개념과 원리]
https://heowc.tistory.com/10 [1:1채팅방 구현]

https://datatracker.ietf.org/doc/html/rfc6455#section-1.1 [ 웹소켓 프로토콜 ]




