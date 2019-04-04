# 서블릿 프로그래밍

## 자바 웹 프로젝트 만들기
- bitcamp-2018-12/java-web 디렉토리 생성
- '$ gradle init' 실행
- java-web/src/main/webapp 디렉토리 생성
- webapp/index.html 생성 및 편집
- bulid.gradle 파일 편집
- 'application' 플러그인 제거 및 그와 관계된 설정 제거
- 'eclipse-wtp', 'war' 플러그인 추가
- 'servletapi' 라이브러리 추가
- 이클립스 IDE dyd tjfwjd vkdlf todtjd
- '$ gradle eclipse' 실행
- 이클립스 IDE로 프로젝트 임포트

---나만의 서블릿 정리---

웹 브라우저에서 요청(URL)이 들어오면
서버에서 톰캣의
 Servlet Container로 전달해 주고
(톰캣)Servlet Container는 java프로그램에서 해당
 URL의 서블릿 객체를 찾는다 (서블릿:웹서버쪽에 작성되있는 자바 프로그램)
서블릿객체가 없다면 서블릿 클래스에 대해 인스턴스를 생성한다.

서블릿 객체의 생성자를 호출하고 init()를 사용한다.

그리고 service()를 생성하고 
서버로 돌려주고 서버는 웹브라우저로 응답한다.

응답할때는 webapp에 있는 자원(HTML,CSS,JavaScript,gif,...)을 브라우저로 보내주는것이다.
(localhost:8080/java-web/index.html 실행됨!)
(이클립스에서서버를 설정하게 되면 알아서 복사해줌)
서버를 만들때 src에 있는 java, resource, webapps에서
webapps폴더를 톰캣 서버의 java-web에 넣고
bin에 있는 (.java컴파일)class파일 그리고 src에서
가져온 resource를 복사해서 
톰캣 java-web 폴더에 있는 WEB-INF 폴더에 복사가 된다. 

