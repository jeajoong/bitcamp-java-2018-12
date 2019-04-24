## 78일(2019-4-23)
- 웹 프로젝트 15단계 과제 수행 : XML로 설정하기
    - web 패키지 추가
        - 페이지 컨트롤러와 웹 관련 클래스들을 이 패키지로 옮긴다.
    - Spring WebMVC XML 설정하기
        - /WEB-INF/app-servlet.xml 파일 생성
    - JSP 파일 옮기기 및 수정
        - /WEB-INF/jsp/ 폴더로 JSP 파일을 옮긴다.
        - header.jsp 경로 수정
    - ContextLoaderListener의 IoC 컨테이너 설정하기
        - /WEB-INF/application-context*.xml 파일 생성
    - 페이지 컨트롤러 수정
        - Spring Web MVC 관련 애노테이션을 적용한다.
        
      src 14 - src 15는 컨트롤러에서 RequestMapping경로
        - JSP에서 파일URL 경로 수정한 작업폴더
        - ContextLoaderListener의 IoC 컨테이너 설정
      
      src 15 - src 16은 xml 파일을 수정했다(기억이 잘...)
      
      src 16 - src 16 real은 .xml파일을 사용하지 않고 자바 내에서 클래스로 처리하는 작업을 했다.
