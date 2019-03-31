package ch28.a;

// 애노테이션 정의
// 
public @interface MyAnnotation {

  
  
  /*
  @Controller - Presentation Layer에서 Contoller를 명시하기 위해서 사용 (클래스)
  
  @Service - Business Layer에서 Service를 명시하기 위해서 사용 (클래스)
      // 스프링 IoC 컨테이너가 관리하는 객체 중에서 비즈니스 로직을 담당하는 객체는 
      // 특별히 그 역할을 표시하기 위해 @Component 대신에 @Service 애노테이션을 붙인다.
  
  @Component - 그 외에 자동으로 스캔해서 등록하고 싶은 것들을 위해 사용 (직접 컨트롤이 가능한 Class들의 경우)
    //메타데이터 객체를 인자로 전달한다.
    //메타데이터 객체는 컴포넌트 생성에 필요한 정보(셀렉터, 템플릿, 스타일 정의 등)를 담고 있는 객체이다.
    //예를 들어 메타데이터 객체의 templateUrl 프로퍼티에는 컴포넌트의 뷰를 정의한 템플릿의 상대경로를 설정한다
    
  @Repository - Persistence Layer에서 DAO를 명시하기 위해서 사용 (클래스)
  @RequestMapping - 특정 URL에 매칭되는 클래스나 메소드임을 명시하기 위해 사용(클래스,메소드)
  @RequestParam - 요청(request)에서 특정한 파라미터의 값을 찾아낼때 사용(파라미터)
    
    
   -- AppConfig 에서 사용
    @ComponentScan(basePackages="com.eomcs.lms") 
      //Configuration 클래스 및 Annotation에 사용하는 설정들을 파싱한다.
        //1. Configuration 파싱
        //2. ComponentScan 설정 내역을 파싱한다.
        //3. Class 로딩 - 클래스로더를 이용하여 모든 자원을 Resource 인터페이스 형태로 불러옴
        //4. 빈 정의 설정
        //5. 빈 생성 & 주입
    
    @ImportResource("classpath:/com/eomcs/lms/conf/tx-context.xml") -> resources 폴더에 존재
      // 트랜잭션을 설정한 파일을 로딩한다. 파일내부에는 트랜잭션 advice를 적용할 대상을 정의한다.
      // => Spring IoC 컨테이너는 @ImportResource 애노테이션에 지정된 설정 파일에 따라 객체를 준비한다.
    --
    
    
    --- DatabaseConfig에서 사용
  @Configuration 
      // Spring IoC 컨테이너 설정을 수행하는 클래스임을 표시한다.

  @PropertySource("classpath:/com/eomcs/lms/conf/jdbc.properties")
      // Property 파일을 Environment로 로딩할 수 있다. 
      Environment env; 객체를 만든이유
      Environment는 스프링 프레임워크가 자동으로 Inject하는 Bean이다. 

  @EnableTransactionManagement
      //트랜잭션 매니저를 지정해 줄 수 있다 (Bean으로 된 것들을 찾아서 트랜잭션 관리)
    
  @Autowired
    스프링이 빈의 요구 사항과 매칭 되는 애플리케이션 컨텍스트상에서
    다른 빈을 찾아 빈 간의 의존성을 자동으로 만족시키도록 하는 수단이다

    @Bean ( 개발자가 컨트롤이 불가능한 외부 라이브러리들을 Bean으로 등록하고 싶은 경우 사용)
    스프링에서 이 태그를 붙인 것들의 객체를 자동으로 생성해줌
      (DatabaseConfig에서는 내부에 Environment 객체로 jdbc 드라이버 정보를 전달해준다)
    
    @MapperScan("com.eomcs.lms.dao")
    매퍼파일이 있는 경로 위치를 알려주고 매퍼파일을 읽어들임
    
    
    
  */
}
