애플리케이션의 단위 테스트를 실행할 때 사용할 설정 파일을 두는 폴더


---나만의 서블릿 정리---

웹 브라우저에서 요청(URL)이 들어오면
서버에서 톰캣의 Servlet Container로 전달해 주고
(톰캣)Servlet Container는 java프로그램에서 해당 URL의 서블릿 객체를 찾는다 (서블릿:웹서버쪽에 작성되있는 자바 프로그램)
서블릿 객체가 없다면 서블릿 클래스에 대해 인스턴스를 생성한다.
서블릿 객체의 생성자를 호출하고 init()를 사용한다.
그리고 service()를 생성하고 
서버로 돌려주고 서버는 웹 브라우저로 응답한다.

응답할때는 webapp에 있는 자원(HTML,CSS,JavaScript,gif,...)을 브라우저로 보내주는것이다.
 (localhost:8080/java-web/index.html 실행됨!)


(이클립스에서 서버를 설정하게 되면 알아서 복사해줌)
서버를 만들때 src에 있는 java, resource, webapps에서
webapps폴더를 톰캣 서버의 java-web에 넣고
bin에 있는 (.java컴파일)class파일 그리고 src에서 가져온 resource를 복사해서 
톰캣 java-web 폴더에 있는 WEB-INF 폴더에 복사가 된다. 

