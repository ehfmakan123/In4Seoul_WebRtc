# JWT 관련 자료 조사

## Session 기반 인증 방식
JWT 토큰 기반 인증 전까지 인증방식

### 인증 방식
    1.클라이언트에서 로그인인 요청이 들어옴
    2.로그인에 성공하면 서버가 유저 세션을 만들고 메모리나 데이터베이스에 저장
    3.서버가 클라이언트에게 세션 ID를 보낸다.
    4.클라이언트의 브라우저에 세션의 ID만 쿠키에 저장하게 한다.
    5.세션 데이터가 서버의 메모리에 저장되므로, 확장 시 모든 서버가 접근할 수 있도록 별도의 중앙 세션 관리 시스템이 필요하다.

### JWT 구성
 .을 구분자로 3가지 문자열로 구성 됨.

    Header(헤더).Payload(내용).Signature(서명)
    ex) xxxx.yyy





참고자료

1.JWT & 토큰 기반 인증 시스템
[https://velog.io/@yh20studio/토큰-기반-인증-시스템-JWT](https://velog.io/@yh20studio/%ED%86%A0%ED%81%B0-%EA%B8%B0%EB%B0%98-%EC%9D%B8%EC%A6%9D-%EC%8B%9C%EC%8A%A4%ED%85%9C-JWT)
(토큰 만료시간 무조건 있어야 하고, 보안 때문에 무제한 불가능.)

2.Vue - Login 세션 유지하기
[https://kdinner.tistory.com/60](https://kdinner.tistory.com/60)

3.[Vue] .txt 파일 저장하기 및 .txt 불러와 data에 바인딩하기
[https://dreamcoding.tistory.com/48](https://dreamcoding.tistory.com/48)

JavaScript에서 클라이언트 IP 주소 가져 오기 -보안 약할것 같다.
[https://www.delftstack.com/ko/howto/javascript/get-ip-address-javascript/](https://www.delftstack.com/ko/howto/javascript/get-ip-address-javascript/)

반영구적 로그인 관련 JWT, 세션 쿠키 조사
