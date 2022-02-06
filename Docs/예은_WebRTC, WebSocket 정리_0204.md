# openvidu

- 쿠렌토 미디어서버를 기본으로 사용. 쿠렌토를 좀더 쉽게 쓸 수 있도록.
- 구현이 쉬움. 대신 커스터마이징이 어려움.
  - 화상 화면에 ar 띄우기 등
- 채팅 기능도 있는 것 같음



# 궁금한 부분

- 도커에 오픈바이두, STUN, TURN 서버 설치했는지 궁금
- 시그널링 서버는 직접 구축하는지, 아님 솔루션을 쓰는지(아마존의 [Kinesis Video Stream](https://github.com/awslabs/amazon-kinesis-video-streams-webrtc-sdk-js), 구글의 [AppRTC](https://github.com/webrtc/apprtc))
  - 쿠렌토 미디어 서버 개념 안에 시그널링 서버가 포함되는 것.
- ICE 프레임워크 사용 (후보 찾기)

- 명세서3 5p에 "공용 websocket 모듈"이 뭘 말하는거지?



# 프론트 세팅

1. WebSocket, WebRTC에 필요한 의존 라이브러리 `index.html`에 추가

   >  명세서2 24p 하단

2. https 활성화

   WebRTC를 활용하기 위해선 http 프로토콜은 제약이 많기 때문에 https를 활성화 시켜야한다.

   (1) vue.config.js 수정

   - 스켈레톤 코드엔 vue.config.js 파일이 있음

   - https 속성 true로, proxy를 8443 포트로 변경

   - ```
     # vue.config.js  # 변경한 상태
     // Vue3 관련 설정 파일
     module.exports = {
       devServer: {
         https: true,
         port: 8083,
         open: true,
         proxy: {
           '/api/v1': {
             target: 'http://localhost:8443/'
           },
           '/webjars': {
             target: 'http://localhost:8443/'
           },
           '/group-call': {
             target: 'http://localhost:8443/'
           },
           '/upload': {
             target: 'http://localhost:8443/'
           }
         },
         historyApiFallback: true,
         hot: true
       },
       css: {
         requireModuleExtension: false // import 시에 경로에 .module 포함 안해도 됨.
       },
       transpileDependencies: [
         'element-plus'
       ],
       lintOnSave: false,
       outputDir: '../backend/src/main/resources/dist'
     }
     ```



# WebSocket

WebSocket을 통해 

- 채팅 기능 구현
- 미디어 서버와 WebRTC 연결을 위한 메시지 송수신 기능 구현

## 1) SockJS

WebSocket Polyfill 라이브러리

## 2) Stomp

단순 텍스트 기반의 메시지 프로토콜. WebSocket 같은 양방향 통신 프로토콜에 사용할 수 있다.



# WebRTC

- Adapter.js 사용 강력 추천 -> 크로스 브라우징 이슈를 사전에 처리해줌



- 두 피어 간의 커넥션은 [`RTCPeerConnection`](https://developer.mozilla.org/en-US/docs/Web/API/RTCPeerConnection)인터페이스를 통해 이루어진다. 커넥션이 이루어지고 열리면, 미디어 스트림들 ([`MediaStream`](https://www.gitbook.com/book/gustnxodjs/webrtc-mdn-kor/edit#)) 과 데이터 채널([`RTCDataChannel`](https://www.gitbook.com/book/gustnxodjs/webrtc-mdn-kor/edit#))들을 커넥션에 연결할 수 있다.
- 미디어 스트림들은 미디어 정보를 가지는 다수의 트랙들로 구성될 수 있다. [`MediaStreamTrack`](https://developer.mozilla.org/en-US/docs/Web/API/MediaStreamTrack)인터페이스 object를 베이스로 하는 트랙은 음성, 영상 및 텍스트(제목 또는 챕터 이름 조차도 포함 가능하다)를 포함하는 다양한 미디어 데이터의 타입 중 하나를 포함 할 수 있다. 대부분의 스트림들은 적어도 한 개 이상의 음성(영상) 트랙으로 구성되어 있고, live 미디어(웹 캠 등)나 저장된(스트리밍) 미디어들을 전송하고 받을 수 있다.



- WebRTC는 시그널링이라는 통신 절차를 통해 P2P로 통신함.