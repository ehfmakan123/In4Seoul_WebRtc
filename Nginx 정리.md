Nginx??

 [웹 서버 소프트웨어](https://ko.wikipedia.org/wiki/%EC%9B%B9_%EC%84%9C%EB%B2%84_%EC%86%8C%ED%94%84%ED%8A%B8%EC%9B%A8%EC%96%B4) 로, 가벼움과 높은 성능을 목표로 한다. 

[웹 서버](https://ko.wikipedia.org/wiki/%EC%9B%B9_%EC%84%9C%EB%B2%84), [리버스 프록시](https://ko.wikipedia.org/wiki/%EB%A6%AC%EB%B2%84%EC%8A%A4_%ED%94%84%EB%A1%9D%EC%8B%9C)및 메일 프록시 기능을 가진다. 

→ 위키피디아 참조 ([https://ko.wikipedia.org/wiki/Nginx](https://ko.wikipedia.org/wiki/Nginx) )

Why use Nginx ?

비동기 Event-Driven 기반 구조.

다수의 연결을 효과적으로 처리가능.

대부분의 코어 모듈이 Apache보다 적은 리소스로 더 빠르게 동작가능

더 작은 쓰레드로 클라이언트의 요청들을 처리가능

위와 같은 이유로 아파치보다 Nginx를 많이 쓰는 추세...!

→ “더 적은 자원으로 더 빠르게 서비스”

기본환경  - ubnutu 18.01 lts , nginx latest version , ssafy 대여 서버 사용(i6a503.p.ssafy.io)

 

1) 서버 패키지 목록 업데이트

$sudo apt update 

$sudo apt upgrade

$sudo apt autoremove

2) Nginx 다운로드

$sudo apt install nginx    // sudo apt remove nginx → 제거

3) Ngnix 실행

$sudo service nginx start ( stop , restart ,reload 기능 有)

$sudo service nginx status ( nginx 동작 확인 → active 초록불 ) 

4) Nginx 버전 확인

$nginx -v ( 보통 엔간한 프로그램 버전확인 *** -v / *** -version 으로 다 되긴 하는듯)

$sudo dpkg -l nginx → 버전 상세 확인

5) Nginx 경로 확인! 

$)sudo find / -name nginx.conf

- 보통은 root/etc/nginx 폴더에 설치가 됩니다!!

+@  설정 만지면서 알게 된 것...!!

1. nginx 환경설정은 nginx 경로에 있는 sites-avaiable 폴더의 dafault를 수정 (버전마다 다름)
2. default나 기타 *.conf 파일을 삭제 한 후 proj.conf 등의 파일을 만들어서 관리도 가능!

- default 설정
    
    listen 80 default_server;
    listen [::]:80 default_server;
    
    → 80 포트를 기본으로 사용
    
    #root /var/www/html; → 기본 root 페이지 (hello nginx! 같은 페이지가 나옴) 
    root /home/ubuntu/dist; → 프로젝트 적용할 dist 폴더로 경로 변경 
    
    index index.html index.htm index.nginx-debian.html; → root 안의 해당 파일을 읽음
    
    server_name [i6a503.p.ssafy.io](http://i6a503.p.ssafy.io/); → 도메인 주소
    
    location / {   → 로케이션에서 uri 별 다른 페이지를 띄우게 설정 가능
    # First attempt to serve request as file, then
    # as directory, then fall back to displaying a 404.
    try_files $uri $uri/ /index.html ;
    }
    ~~location @rewrite{
    rewrite ^(.+)$ /index.html last;
    }~~
    
    (기본 default 값으로 하니까 메인 페이지는 띄워지는데 이동이 안됐음..!)
    
    ** 다른 페이지 띄우는걸 하고 좀 찾아봤는데
    
    도메인 주소 , 도메인 주소/admin , 도메인주소/admin/login 등 전부 잘 나옵니다.
    
    여기에 추가로 없는 페이지의 경우 404 페이지를 서버단에서 띄우려고 했는데 잘 안되더라구요
    
    찾아보니 vue, react는 싱글 페이지 방식이라 에러 페이지를 서버에서 처리가 힘든것 같습니다.
    
    front 내부에서 40** ,5** 에러 페이지를 만들고 잘못된 요청시 해당 페이지로 보내는 방식으로 
    
    에러페이지를 띄워야 할 것 같습니다
    
    (도메인/melong/melong 이런식으로 가면 App.vue 빈페이지만 나옴 에러페이지 필요 )
    
    location /api { // proxy 설정 
    proxy_pass [http://localhost:8080/](http://localhost:8080/);
    proxy_http_version 1.1;
    proxy_set_header Connection "";
    
    proxy_set_header Host $host;
    proxy_set_header X-Real-IP $remote_addr;
    proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
    proxy_set_header X-Forwarded-Proto $scheme;
    proxy_set_header X-Forwarded-Host $host;
    proxy_set_header X-Forwarded-Post $server_port;
    
    }
    
    #  https 인증 관련 설정 443 포트를 열고 certbot으로 받은 ssl 인증 키를 복제하여 확인
    
    listen 443;
    ssl on;
    ssl_certificate /etc/letsencrypt/live/i6a503.p.ssafy.io/fullchain.pem;
    ssl_certificate_key /etc/letsencrypt/live/i6a503.p.ssafy.io/privkey.pem;
    

기본 Nginx 설치 및 설정 참조 

1. [https://t-okk.tistory.com/154](https://t-okk.tistory.com/154)
2. [https://m.blog.naver.com/jhc9639/220967352282](https://m.blog.naver.com/jhc9639/220967352282)
3. [https://icarus8050.tistory.com/57](https://icarus8050.tistory.com/57)
