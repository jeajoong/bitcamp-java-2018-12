package study.A28_annotation.e;

// 필수 프로퍼티
public @interface MyAnnotation {
  String value();
}

/*
@Controller - Presentation Layer에서 Contoller를 명시하기 위해서 사용 (클래스)
@Service - Business Layer에서 Service를 명시하기 위해서 사용 (클래스)
@Repository - Persistence Layer에서 DAO를 명시하기 위해서 사용 (클래스)
@RequestMapping - 특정 URL에 매칭되는 클래스나 메소드임을 명시하기 위해 사용(클래스,메소드)
@RequestParam - 요청(request)에서 특정한 파라미터의 값을 찾아낼때 사용(파라미터)
@Component - 그 외에 자동으로 스캔해서 등록하고 싶은 것들을 위해 사용 (?)
*/


