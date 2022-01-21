# API 명세서

>> [구글 스프레드시트 링크](https://docs.google.com/spreadsheets/d/1Kx0Zeih1FEAiNLaPm4TlEuWxEX31yteZYxaSJXF1vgM/edit#gid=0)

### 📌 **HTTP API 설계 원칙**

표준이 따로 있는 것은 아니고 조직마다 설계 원칙이 다르다.

참고: [Heroku 플랫폼 API 디자인 가이드](https://github.com/yoondo/http-api-design/tree/master/ko)

### 🔨 **API 명세서 작성툴 종류**

- Gitbook
- Postman
- Spring REST Docs

⇒ 일단 임시이니 스프레드시트에 작성함

## 🍪 함께 논의할 사항

### 공통

- 임의로 짠 것이니 고치고 싶은 부분은 마음껏 수정해주세요!
- Return 값도 정의서에 적을지

### accounts

1. ID 중복체크 api 필요할지
2. 회원정보 수정, 탈퇴
    1. 관리자: 필수
    2. 상담사 마이페이지 → 자기 회원정보 수정, 탈퇴를 필수
        - 이메일 수정 등 상담사가 직접 했으면 좋겠다.
3. 데스크 계정 생성
    - signup으로 한번에? 아님 따로?
    - 일단 `/accounts/create/` 로 따로 만들었는데 괜찮은지.

### meeting

1. 채팅 어떻게 api를 구분할지 모르겠어서 채팅 보내기/채팅 조회로 구분함.

### Next

- 화상상담 부분: WebRTC 기술 반영해 재작성 필요
- 지도 부분: WebSocket 기술 반영해 재작성 필요
- 채팅 부분: WebSocket 기술 반영해 재작성 필요
