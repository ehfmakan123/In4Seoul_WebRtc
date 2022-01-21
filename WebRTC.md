# WebRTC 기술조사

**WebRTC(Web Real-Time Communication)란?**

> 웹 애플리케이션과 사이트가 중간자 없이 브라우저 간에 오디오나 영상 미디어를 포함하고 마음대로 스트림 할 뿐 아니라, 임의의 데이터도 교환할 수 있도록 하는 기술
> 

**드라이버나 플러그인 설치 없이 웹 브라우저간 P2P 연결을 통해 데이터 교환을 가능하게 하는 기술**

P2P란

- Peer-to-Peer 라는 뜻으로 중앙 서버 없이 각 단말들이 서로 동등한 입장에서 통신을 하는 네트워크를 말함

P2P연결의 장점

- 중개 서버를 거치지 않기 때문에 빠른 속도가 보장된다
- HTTPS가 강제되기 때문에 중간자 공격에 대한 보안이 보장된다
- 실시간으로 상호작용 할 수 있다는 특성을 바탕으로 더욱 개인화되고 참여 유도적인 웹 어플리케이션을 제작할 수 있다

P2P연결에서 가장 고려해야 할점

- WebRTC가 범용적으로 사용되기 위해서는 다양한 플랫폼과 브라우저에서 접속하는 사용자들에게 동일한 사용자 경험을 제공해야 한다. 때문에 WebRTC에서 브라우저와 플랫폼 간 호환성이 가장 큰 숙제

 WebRTC의 브라우저 호완성

- WebRTC는 구글이 주도한 오픈소스 프로젝트를 기반으로 하는 웹 표준
- WebRTC는 단일 브라우저 벤더에서 제공하는 API가 아니며, 브라우저와 운영체제 별로 개발되는 속도와 지원되는 버전이 다르므로 호환성과 상호 운용성을 항상 체크해야한다

### P2P 절차

 각각의 웹 브라우저가 밟아야 하는 절차

1. 각 브라우저가 P2P 커뮤니케이션에 동의
2. 서로의 주소를 공유
3. 보안 사항 및 방화벽 우회
4. 멀티미디어 데이터를 실시간으로 교환

브라우저는 웹 서버가 아니기 때문에 외부에서 접근할 수 있는 주소가 없기때문에 2번 3번 단계를 해결하기 어렵다 이런 이유로 WebRTC가 P2P 기반이긴 하지만 통신 설정 초기 단계에서는 **중재자**의 역할이 필요하다

- NAT(Network Address Translation)
    - LAN에서 사용하는 프라이빗 IP 주소와 인터넷에서 이용할 수 있는 글로벌 IP주소를 일대일로 서로 변환시키는 기술로  주로 라우터 등에 내장 되어 있다
    - 여러대의 컴퓨터가 하나의 공인 IP를 공유한다
    - 글로벌 IP주소(공인IP주소)
        - 전 세계적으로 반드시 고유한 번호가 보증되는 IP주소
        - 인터넷 상에서는 이 글로벌 IP주소를 사용하여 통신을 한다
        - IP 주소는 32비트 수치로 표현하기 때문에  중복되지 않는 번호라고 해도 그 수가 한정적이다 따라서 LAN 안과 같이 한정된 범위 내에서는 별도의 프라이빗 IP주소를 할당하여 사용하는 것이 일반적
    - 프라이빗 IP 주소 (사설 IP주소)
        - LAN과 같이 한정된 범위 안에서만 유효한 IP주소를 말한다
        - 전화로 말하자면 내선 번호와 같은 것이므로 외부세계와의 통신에는 사용할 수 없다
        - 관리자가 자유롭게 할당 가능하다
- DHCP
    
    유휴 상태의 IP를 일시적으로 임대받는 DHCP
    

### 방화벽과 NAT 트래버셜

- NAT, DHCP ,방화벽 때문에 일반적인 컴퓨터에는 공인 IP가 할당되어 있지 않다
- 단순히 공인 IP만 알아낸다고 해서 특정한 사용자를 가리킬 수 없고 공인 IP 뿐만 아니라 해당 네트워크 연결된 사설 IP 주소까지 알아내야 특정한 사용자를 지정할 수 있다
- 일반적으로 라우터가 NAT 역할을 한다.  외부에서 접근하는 공인 IP와 포트 번호를 확인하여 현재 네트워크 내의 사설 IP들을 적절히 매핑시켜준다

어떤 브라우저 두개가 서로 직접 통신하려면, 각자 현재 연결된 라우터의 공인 IP주소와 포트를 먼저 알아내야 한다 

하지만 어떤 라우터들은 특정 주소나 포트와의 연결을 차단하는 방화벽 설정이 되어 있을 수 있다

NAT 트래버셜(NAT traversal)

- 라우터를 통과해서 연결할 방법을 찾는 과정

### STUN, TURN

![image](/uploads/7d3281d729019d2c8de0e6a57cff2e86/image.png)

NAT 트래버셜 작업은 STUN(Session Traversal Utilities for NAT)서버에 의해 이루어진다 

STUN방식

- **단말이 자신의 공인 IP주소와 포트를 확인하는 과정에 대한 프로토콜**이다
- STUN 서버는 인터넷의 복잡한 주소들 속에서 유일하게 자기 자신을 식별할 수 있는 정보를 반환해준다
- 즉 WebRTC 연결을 시작하기 전에 STUN 서버를 향해 요청을 보내면, STUN 서버는 NAT 뒤에 있는 피어(Peer)들이 서로 연결할 수 있는 공인 IP와 포트를 찾아준다

만약 두개의 장치가 성공적으로 STUN 서버에서 자기 자신의 주소를 알아냈을 경우에는 P2P 연결을 시도할 두개의 고유한 주소가 생긴 셈이다

STUN 서버를 이용하더라도 항상 자신의 정보를 알아낼 수 있는것은 아니다

어떤 라우터들은 방화벽 정책을 달리할 수도 있고 , 이전에 연결된 적이 있는 네트워크만 연결할 수 있게 제한을 걸기도 한다 

이때문에 STUN 서버를 통해 자기 자신의 주소를 찾아내지 못했을 경우 TURN(Traversal Using Relay NAT)서버를 대안으로 이용하게 된다

**TURN 방식**

- TURN 방식은 네트워크 미디어를 중개하는 서버를 이용하는 것이다
- TURN 방식은 중간에 서버를 한번 거치기 때문에 엄밀히 이야기하자면 P2P통신이 아니게 되며 그 구조상 지연이 필연적으로 발생하게 된다

하지만 보안정책이 엄격한 개인 NAT 내부에 위치한 브라우저와 P2P통신을 할 수 있는 유일한 방법이기 때문에 TURN 방식은 최후의 수단으로 선택되어야 한다 

### ICE와 Candidate

여태껏 이야기한 STUN, TURN 서버를 이용해서 획득했던 IP 주소와 프로토콜, 포트의 조합으로 구성된 연결 가능한 네트워크 주소들을 **후보(Candidate)**라고 부른다그리고 이 과정을 **후보 찾기(Finding Candidate)**라고 부른다

후보들을 수집하면 일반적으로 3개의 주소를 얻게 된다

- 자신의 사설 IP와 포트 넘버
- 자신의 공인 IP와 포트 넘버(STUN, TURN 서버로부터 획득 가능)
- TURN 서버의 IP와 포트넘버(TURN 서버로부터 획득 가능)

이 모든 과정은 **ICE(Interactive Connectivity Establishment)**라는 프레임워크 위에서 이루어진다

ICE는 두개의 단말이 P2P연결을 가능하게 하도록 최적으로 경로를 찾아주는 프레임워크이다

**요약**

ICE 프레임워크가 STUN 또는 TURN  서버를 이용해 상대방과 연결 가능한 후보들을 갖고 있다는것

그러니까 두 브라우저가 P2P 통신을 위해 통신할 수 있는 주소만큼은 확실하게 알아낸 셈이다

마지막으로 남은건 이제 미디어와 관련된 정보를 교환하는 것이다

### SDP(Session Description Protocol)

- WebRTC에서 스트리밍 미디어의 해상도나 형식, 코덱 등의 멀티미디어 컨텐츠의 초기 인수를 설명하기 위해 채택한 프로토콜
- 미디어 관련된 초기 세팅 정보를 기술하는 SDP는  **제안 응답 모델(Offer/Answer)**을 가지고 있다
- 이 모델은  어떤 피어가 이러한 미디어 스트림을 교환할 것이라고 제안하면 상대방으로부터 응답이 오기를 기다린다는 의미

### Trickle ICE

- ICE 프레임워크를 설정하다보면 Trickle ICE 옵션을 만날 수 있다
- 일반적으로 각 피어는 ICE 후보들을 수집해서 그 목록을 완성한 후 한꺼번에 교환하게 되는데 이러한 방식은 SDP의 제안 응답 모델과 맞물리면서 단점으로 작용한다

단점

- 후보를 모으는 데에도 시간이 오래 걸리고, 그 과정에서 네트워크 환경에 따라 지연이 걸릴 수 있다
- 한 쪽 피어의 ICE 후보 수집 작업이 완료되어야만 다른 피어가 ICE 후보를 모을 수 있기 때문에 비효율적(?)

이러한 비효율적 후보 교환 작업을 병렬 프로세스로 수행할 수 있게 만드는것이 바로 Trickle ICE

두개의 피어가 ICE 후보를 수집하고 교환하는 과정을, 동기적 프로세스에서 비동기적 프로세스로 만드는 기술

### 시그널링

![image](/uploads/1f555ac77cefe37afeeaae1b9cd1dd1f/image.png)

시그널링(Signaling)

- RTCPeerConnection 통신에 사용할 프로토콜, 채널, 미디어 코덱 및 형식, 데이터 전송 방법, 라우팅 정보와 NAT 통과 방법을 포함한 통신 규격을 교환하기 위해 두 장치의 제어 정보를 교환 하는 과정을 의미한다
- 시그널링은 WebRTC 자체에서 지원하는 기능이 아니고  WebRTC 연결전 미리 준비해야 하는 과정으로 WebRTC 자체의 스펙도 아니기 때문에 한가지로 딱 정해진 방법이 없다.
- 정해진 방법이 없는 이유는 알 수 없는 두 장치가 언제 어떤 방식으로 연결될 수 있는지 모든 경우를 예측하는 것이 불가능하기 때문

### WebRTC의 한계

1. 브라우저 호환성
2. 시그널링 서버에 대한 명시적인 표준이 없음
3. 실시간성이 매우 중요하기 때문에 UDP 위에서 동작한다 즉 데이터를 빠르게 전송할 수는 있지만 이 과정에서 데이터 손실이 발생할 수 있다 

1. **WebRTC는 어떻게 실시간으로 데이터를 교환할 수 있을까?**  

[](https://wormwlrm.github.io/2021/01/24/Introducing-WebRTC.html)

1. **WebRTC란 무엇인가?**

[[WebRTC] WebRTC란 무엇일까?](https://gh402.tistory.com/38?category=935378)

1. **WebRTC란 (STUN과 TURN 서버의 이해)**

[WebRTC란? (STUN과 TURN 서버의 이해) (2)](https://andonekwon.tistory.com/59)

---

# Kurento 미디어 서버란?

- 전체 WebRTC 스택의 기능적 구현을 제공하는 미디어 서버이다
- 오픈소스 프로젝트로 WebRTC의 사양이 모두 구현된 WebRTC 미디어 서버와 클라이언트 API를 제공하는 패키지이며 WebRTC 화상 어플리케이션의 기능 개발을 지원한다

### 쿠렌토를 사용할 수 있는 3가지 방법

![image](/uploads/b6460fbb399c83bba1b638093c6a4af1/image.png)

1번째 방법

- WebRTC 브라우저에서 직접적으로 Kurento JavaScript SDK를 사용하기
- 빠른 테스트 및 개발을 위해서만 권장되는 방법?

2번째 방법

- 자바 서버에서 Kurento Java SDK 를 사용하는 방법
- 웹 브라우저는 HTML, WebRTC 시그널링과 같은 작업을 위한 어플리케이션의 클라이언트  ,  자바 서버는  쿠렌토 미디어 서버의 클라이언트

3 번째 방법

- 2번째 방법과 같은데 Node.js서버를 사용하는 방식

### WebRTC 미디어 서버를 선택해야 하는 이유

- WebRTC는 P2P 연결을 통한 실시간 통신기능을 제공하는 프로토콜과 APIs 세트
- 중간 서버 없이 사용하도록 고안되었지만 실제로 더 복잡한 애플리케이션을 만들려고 할때는 부족하다

### 미디어 서버는 어떤 기능을 제공하는가?

- WebRTC 미디어 서버는 미디어 트래픽이 통과하는 멀티미디어 미들웨어이다
- 미디어 서버에서는 들어오는 스트림을 처리하고 다른 결과물을 내보낼 수 있다

1. Group Communications : 한 명의 스트리머가 방송을 시작하고 , 그 방에 접속해 있는 참여자들에게 미디어 스트림을 배포한다 
2. Mixing : 여러 수신 스트림을 하나의 복합 스트림으로 변환한다
3. Transcoding : 호환되지 않는 클라이언트 간에 코덱 및 형식을 즉석에서 조정한다
4. Recording : 미디어에 들어오는 스트림을 영구적인 방식으로 저장한다

![image](/uploads/e11398f0f3d9594bc075a8c509972673/image.png)

[[WebRTC] 쿠렌토(Kurento)는 무엇인가?](https://gh402.tistory.com/43?category=935378)

[Introduction to Kurento - Kurento 6.16.1-dev documentation](https://doc-kurento.readthedocs.io/en/latest/user/intro.html)

[[WebRTC] Kurento를 사용하여 실시간 스트리밍하기](https://gh402.tistory.com/48?category=935378)

깃허브 자바 예제코드

### 쿠렌토 튜토리얼(Hello World)

문서랑 깃허브에 제공된 파일이랑 다르다..?

웹 브라우저에서는 WebRTC를 활성화하기 위해 HTTPS을 사용해야 한다 그래서 웹 서버는 반드시 SSL과 인증서 파일을 사용해야한다

쿠렌토는 개발자들에게 쿠렌토 미디어 서버를 제어할 수 있는 쿠렌토 자바 클라이언트를 제공한다

이 클라이언트 라이브러리는 모든 종류의 Java 애플리케이션에서 사용할 수 있다

로컬 스트림이 쿠렌토 미디어 서버로 전송되고  이 로컬 스트림은 수정없이 다시 클라이언트로 전송된다

이런 행동이 구현되기 위해서는 하나의 미디어 요소로 구성된 미디어 파이프라인을 만들어야 한다

이 파이프라인은 양방향 미디어 흐름을 교환할 수 있는 기능을 가져야한다

![image](/uploads/65ad2b16d3f6af11227d71b75c99b1d4/image.png)

웹 애플리케이션이기 때문에  클라이언트 - 서버 아키텍처를 따라간다

클라이언트 측에서는 자바스크립트로 로직이 구현된다

서버 측에서는 쿠렌토 자바 클라이언트 API를 사용하는 스프링 부트 기반 애플리케이션 서버를 사용하여  쿠렌토 미디어 서버 기능을 제어한다

이러한 엔티티 통신을 하기 위해 두개의 WebSocket이 사용된다

1. 소켓은 사용자 시그널링 프로토콜을 구현하기 위해 클라이언트와 애플리케이션 서버 사이에 만들어진다 

1. 또 다른 Websocket은 쿠렌토 자바 클라이언트와 쿠렌토 미디어 서버 사이의 통신을 수행하는데 사용된다

이 통신을 **쿠렌토 프로토콜**을 사용하여 이루어진다

- 쿠렌토 프로토콜
    
    [Kurento Protocol - Kurento 6.16.1-dev documentation](https://doc-kurento.readthedocs.io/en/latest/features/kurento_protocol.html)
    
    ### 쿠렌토 프로토콜
    
    - 쿠렌토 프로토콜은 JSON-RPC 2.0 사양을 사용하여 API 메세지를 인코딩한다
    
    JSON-RPC 메세지 형식
    
    - 쿠렌토 프로토콜은 JSON-RPC 2.0 사양을 사용하여 API 메세지를 인코딩한다
    
    request 형식
    
    - jsonrpc : JSON-RPC 프로토콜 버전을 지정하는 문자열
    - id: 문자열 또는 숫자를 포함하는 클라이언트에 의해 설정된 고유 식별자
    - method: 호출할 메서드의 이름이 들어 있는 문자열
    - params: 메세드를 호출하는 동안 사용할 매개 변수 값을 고정하는 구조화된 값

## 자바  로직

### [Application.java](http://Application.java) 코드

```java
/*
 * Copyright 2018 Kurento (https://www.kurento.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kurento.tutorial.helloworld;

import org.kurento.client.KurentoClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

/**
 * Kurento Java Tutorial - Application entry point.
 */
@SpringBootApplication
@EnableWebSocket
public class Application implements WebSocketConfigurer
{
  @Bean
  public Handler handler()
  {
    return new Handler();
  }

  @Bean
  public KurentoClient kurentoClient()
  {
    return KurentoClient.create();
  }

  @Bean
  public ServletServerContainerFactoryBean createServletServerContainerFactoryBean() {
    ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
    container.setMaxTextMessageBufferSize(32768);
    return container;
  }

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry)
  {
    registry.addHandler(handler(), "/helloworld");
  }

  public static void main(String[] args) throws Exception
  {
    SpringApplication.run(Application.class, args);
  }
}
```

KurentoClient

```java
@Bean
  public KurentoClient kurentoClient()
  {
    return KurentoClient.create();
  } 
```

- 쿠렌토 클라이언트는 스프링 빈으로 인스턴스화 된다
- 쿠렌토 클라이언트는 쿠렌토 미디어 파이프라인을 만드는데 사용된다
- 파이프라인은 애플리케이션에 미디어 기능을 추가하는데 사용된다
- 쿠렌토 클라이언트가 인스턴스화 되면 쿠렌토미디어서버(KMS)와 통신하고 멀티미디어 기능을 제어할 준비가 된다

```java
@SpringBootApplication
@EnableWebSocket
public class Application implements WebSocketConfigurer

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry)
  {
    registry.addHandler(handler(), "/helloworld");
  }
```

- WebSocketHandler를 등록하기 위해 WebSocketConfigurer 인터페이스를 구현한다
- WebSocketHandler는  /helloword 경로에 대한 웹소켓 요청을 처리한다?

### Handler.java코드

```java
public class Handler extends TextWebSocketHandler
{
  private static final Logger log = LoggerFactory.getLogger(Handler.class);
  private static final Gson gson = new GsonBuilder().create();

  private final ConcurrentHashMap<String, UserSession> users =
      new ConcurrentHashMap<>();

  @Autowired
  private KurentoClient kurento;

  /**
   * Invoked after WebSocket negotiation has succeeded and the WebSocket connection is
   * opened and ready for use.
   */
  @Override
  public void afterConnectionEstablished(WebSocketSession session)
      throws Exception
  {
    log.info("[Handler::afterConnectionEstablished] New WebSocket connection, sessionId: {}",
        session.getId());
  }

  /**
   * Invoked after the WebSocket connection has been closed by either side, or after a
   * transport error has occurred. Although the session may technically still be open,
   * depending on the underlying implementation, sending messages at this point is
   * discouraged and most likely will not succeed.
   */
  @Override
  public void afterConnectionClosed(final WebSocketSession session,
      CloseStatus status) throws Exception
  {
    if (!status.equalsCode(CloseStatus.NORMAL)) {
      log.warn("[Handler::afterConnectionClosed] status: {}, sessionId: {}",
          status, session.getId());
    }

    stop(session);
  }

  /**
   * Invoked when a new WebSocket message arrives.
   */
  @Override
  protected void handleTextMessage(WebSocketSession session,
      TextMessage message) throws Exception
  {
    final String sessionId = session.getId();
    JsonObject jsonMessage = gson.fromJson(message.getPayload(),
        JsonObject.class);

    log.info("[Handler::handleTextMessage] message: {}, sessionId: {}",
        jsonMessage, sessionId);

    try {
      final String messageId = jsonMessage.get("id").getAsString();
      switch (messageId) {
        case "PROCESS_SDP_OFFER":
          // Start: Create user session and process SDP Offer
          handleProcessSdpOffer(session, jsonMessage);
          break;
        case "ADD_ICE_CANDIDATE":
          handleAddIceCandidate(session, jsonMessage);
          break;
        case "STOP":
          handleStop(session, jsonMessage);
          break;
        case "ERROR":
          handleError(session, jsonMessage);
          break;
        default:
          // Ignore the message
          log.warn("[Handler::handleTextMessage] Skip, invalid message, id: {}",
              messageId);
          break;
      }
    } catch (Throwable ex) {
      log.error("[Handler::handleTextMessage] Exception: {}, sessionId: {}",
          ex, sessionId);
      sendError(session, "[Kurento] Exception: " + ex.getMessage());
    }
  }

  /**
   * Handle an error from the underlying WebSocket message transport.
   */
```

```java
public class Handler extends TextWebSocketHandler
```

- handler 클래스는  텍스트 웹소켓 요청을 다루기위해   TextWebSocketHandler 를 extends한다 ( 문서에는 implements..?  github코드에는 extends)

```java
public void handleTextMessage(WebSocketSession session, TextMessage message)
    throws Exception {
  [...]
  switch (messageId) {
    case "start":
      start(session, jsonMessage);
      break;
    case "stop": {
      stop(session);
      break;
    }
    case "onIceCandidate":
      onRemoteIceCandidate(session, jsonMessage);
      break;
    default:
      sendError(session, "Invalid message, ID: " + messageId);
      break;
  }
  [...]
}
```

- 요청에 대한 작업을 한다 그리고  그 응답을 웹소켓을 통해 보낸다?
- 이전의 시퀀스 다이어그램의 시그널링 프로토콜의 서버 부분을 구현한다?

```java
github코드에는 handleProcessSdpOffer(session, jsonMessage);        문서에서는 start(session, jsonMessage);

handleProcessSdpOffer 

   final MediaPipeline pipeline = kurento.createMediaPipeline();
    user.setMediaPipeline(pipeline);

    final WebRtcEndpoint webRtcEp =
        new WebRtcEndpoint.Builder(pipeline).build();
    user.setWebRtcEndpoint(webRtcEp);
    webRtcEp.connect(webRtcEp);
```

- 이 메소드는 쿠렌토가 미디어를 어떻게 처리해야하는지 구성하는 부분이다
- 미디어 파이프라인이 여기서 만들어진다
- KurentoClient 객체는  MediaPipeline 객체를 만드는데 사용한다
- 이것을 사용하여 우리가 필요로 하는 요소들이 만들어지고 연결된다
- 이 경우  WebRTC 스트림을 받기 위해 그리고 클라이언트에게 다시 보내기 위해  우리는 하나의 WebRtcEndpoint를 인스턴스화 한다

```java

 // Common events for all objects that inherit from BaseRtpEndpoint
addErrorListener(
    new EventListener<ErrorEvent>() { ... });
addMediaFlowInStateChangeListener(
    new EventListener<MediaFlowInStateChangeEvent>() { ... });
addMediaFlowOutStateChangeListener(
    new EventListener<MediaFlowOutStateChangeEvent>() { ... });
addConnectionStateChangedListener(
    new EventListener<ConnectionStateChangedEvent>() { ... });
addMediaStateChangedListener(
    new EventListener<MediaStateChangedEvent>() { ... });
addMediaTranscodingStateChangeListener(
    new EventListener<MediaTranscodingStateChangeEvent>() { ... });

// Events specific to objects of class WebRtcEndpoint
addIceCandidateFoundListener(
    new EventListener<IceCandidateFoundEvent>() { ... });
addIceComponentStateChangeListener(
    new EventListener<IceComponentStateChangeEvent>() { ... });
addIceGatheringDoneListener(
    new EventListener<IceGatheringDoneEvent>() { ... });
addNewCandidatePairSelectedListener(
    new EventListener<NewCandidatePairSelectedEvent>() { ... });
```

- 이벤트 리스너를 만들어라
- 쿠렌토에서 관리되는 모든 객체는 여러 유형의 이벤트를 emit할수 있는 능력? 이 있다

WebRTC SDP 협상

- WebRTC에서 SDP 제안/응답 모델은 Peer간에 교환될 오디오 또는 비디오 트랙을 협상하는데 사용된다

## 클라이언트 로직

- 서버 사이드에서 이전에 생성된 웹소켓 서비스를 호출하기 위해서 우리는 자바스크립 클래스 WebSocet을 사용해야 한다
- 서버와의 WebRTC 상호작용을 단순화하기 위해서  Kurento-utils.js라 불리는 특정 Kurento 자바스크립트 라이브러리를 사용한다
- Kurento-utils.js 라이브러리는 adapter.js에 의존한다 이는 구글이 관리하는 JavaScript WebRTC 유틸리티로 브라우저의 차이를 추상화한다

```jsx
var ws = new WebSocket('ws://' + location.host + '/helloworld');

ws.onmessage = function(message) {        
   var parsedMessage = JSON.parse(message.data);
   console.info('Received message: ' + message.data);

   switch (parsedMessage.id) {
   case 'startResponse':
      startResponse(parsedMessage);
      break;
   case 'error':
      if (state == I_AM_STARTING) {
         setState(I_CAN_START);
      }
      onError('Error message from server: ' + parsedMessage.message);
      break;
   case 'iceCandidate':
      webRtcPeer.addIceCandidate(parsedMessage.candidate, function(error) {
         if (error)
            return console.error('Error adding candidate: ' + error);
      });
      break;
   default:
      if (state == I_AM_STARTING) {
         setState(I_CAN_START);
      }
      onError('Unrecognized message', parsedMessage);
   }
}

function start() {
   console.log('Starting video call ...');

   // Disable start button
   setState(I_AM_STARTING);
   showSpinner(videoInput, videoOutput);

   console.log('Creating WebRtcPeer and generating local sdp offer ...');

   var options = {
      localVideo : videoInput,
      remoteVideo : videoOutput,
      onicecandidate : onIceCandidate
   }
   webRtcPeer = new kurentoUtils.WebRtcPeer.WebRtcPeerSendrecv(options,
         function(error) {
            if (error)
               return console.error(error);
            webRtcPeer.generateOffer(onOffer);
         });
}

function onOffer(error, offerSdp) {
   if (error)
      return console.error('Error generating the offer');
   console.info('Invoking SDP offer callback function ' + location.host);
   var message = {
      id : 'start',
      sdpOffer : offerSdp
   }
   sendMessage(message);
}

function onIceCandidate(candidate) {
   console.log('Local candidate' + JSON.stringify(candidate));

   var message = {
      id : 'onIceCandidate',
      candidate : candidate
   };
   sendMessage(message);
}

function startResponse(message) {
   setState(I_CAN_STOP);
   console.log('SDP answer received from server. Processing ...');

   webRtcPeer.processAnswer(message.sdpAnswer, function(error) {
      if (error)
         return console.error(error);
   });
}

function stop() {
   console.log('Stopping video call ...');
   setState(I_CAN_START);
   if (webRtcPeer) {
      webRtcPeer.dispose();
      webRtcPeer = null;

      var message = {
         id : 'stop'
      }
      sendMessage(message);
   }
   hideSpinner(videoInput, videoOutput);
}

function sendMessage(message) {
   var jsonMessage = JSON.stringify(message);
   console.log('Sending message: ' + jsonMessage);
   ws.send(jsonMessage);
}
```

onmessage

- onmessage 리스너는 클라이언트 측에서 JSON 시그널링 프로토콜을 구현하는데 사용된다

메세지에는 3가지가 있다
