# Git Commit Convention

- 팀 협업 시 Commit Message Convention을 정해두면 서로 간의 코드 리뷰와, 자신의 이전 로그를 살펴보는 것에 도움이 된다.

### **1. Commit Message Structure**

크게 **제목, 본문, 꼬리말** 세 가지 파트로 나누고, 각 파트는 빈줄을 두어서 구분한다.

```
type: subject  # 제목 (타입: 제목)

body  # 본문

footer  # 꼬리말
```

예시

```jsx
Feat: 추가 로그인 함수

로그인 API 개발

# 지라이슈번호
```

### **2. Commit Type 타입**

```
type: subject
```

- 타입은 영어, 첫 문자 대문자로.
- : 뒤에만 space있음

[Untitled](https://www.notion.so/6663f660eafb4bc5866478a838db4fb7)

### **3. Subject 제목**

코드 변경 사항에 대한 짧은 요약

1. 동사 원형으로 시작(첫 글자 대문자)
    - Fix ~
    - Add ~
    - Change ~
2. 50자 이내로 작성
3. 마지막에 특수문자 삽입 X (! ? . 등)

### **4. Body 본문**

- 선택사항. 부연설명이 필요할 때 작성.
1. 한 줄 당 72자 이내로 작성
2. 최대한 상세히
3. 무엇을 변경했는지 or 왜 변경했는지 (어떻게x)

### **5. Footer 꼬리말**

- 선택사항
- footer는 작성 안해도 될듯
- 지라 이슈 번호

## **좋은 커밋 메시지를 작성하기 위한 규칙(ENG)**

- 동명사보다 명사를 사용한다.
- 꼭 필요한 경우가 아니라면 관사(a, an, the)는 사용하지 않는다.
- 부정문 `Dont't`를 사용한다.

### **출처**

[협업을-위한-기본적인-git-커밋컨벤션-설정하기](https://overcome-the-limits.tistory.com/entry/%ED%98%91%EC%97%85-%ED%98%91%EC%97%85%EC%9D%84-%EC%9C%84%ED%95%9C-%EA%B8%B0%EB%B3%B8%EC%A0%81%EC%9D%B8-git-%EC%BB%A4%EB%B0%8B%EC%BB%A8%EB%B2%A4%EC%85%98-%EC%84%A4%EC%A0%95%ED%95%98%EA%B8%B0)

[https://doublesprogramming.tistory.com/256](https://doublesprogramming.tistory.com/256)

[https://blog.ull.im/engineering/2019/03/10/logs-on-git.html](https://blog.ull.im/engineering/2019/03/10/logs-on-git.html)  