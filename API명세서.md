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


기능	분류	번호	Method	URI	Body	Description	Return	비고
accounts	공통	1	GET	/accounts/check-duplicate/id/{id}		ID 중복체크		
		2	POST	/accounts/signup/	user_id, password1, password2, name, phone_number, email(필수x)	회원가입		
		3	POST	/accounts/login/	user_id, password	로그인	JWT	
		4	POST	/accounts/logout/	password(데스크)	로그아웃		데스크) password가 있어야 로그아웃 가능
	관리자	5	GET	/accounts/userlist/		유저 목록 조회		
		6	POST	/accounts/create/		데스크 계정 생성		
		7	GET	/accounts/{user_id}/		회원정보 조회		
		8	PUT	/accounts/{user_id}/		회원정보 수정		관리자가 회원코드 수정(=회원가입 승인)
		9	DELETE	/accounts/{user_id}/		회원탈퇴		
staff		10	GET	/staff/meeting-logs/	user_id	상담 기록 조회		
		11	GET	/staff/meeting/	user_id	상담 알림목록 조회		
		12	POST	/staff/meeting/	user_id	상담 연결		
meeting		13	POST	/meeting/		상담 신청(데스크)		
		14	DELETE	/meeting/		상담 종료		
		15	POST	/meeting/chat/		채팅 보내기		
		16	GET	/meeting/chat/		채팅 내용 조회		
		17	GET	/meeting/map/		지도 정보 조회		양방향 소통은 어떻게 하지?
board		18	GET	/board/posts/		글 목록 조회		당일날짜 글만 가져오기 / 검색하기 를 분리하는게 좋을까?
		19	POST	/board/posts/	title, content, password	글 작성		
		20	GET	/board/posts/{post_id}/		글 조회		하나의 글만 조회할 일은 없을 것 같음
		21	PUT	/board/posts/{post_id}/	password	글 수정		
		22	DELETE	/board/posts/{post_id}/	password	글 삭제		