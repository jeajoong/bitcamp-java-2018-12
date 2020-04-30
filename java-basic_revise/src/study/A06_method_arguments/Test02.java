// 메서드 - 메서드와 파라미터
package study.A06_method_arguments;

public class Test02 {
  public static void main(String[] args) {
    // 파라미터가 있는 명령어 블록을 사용하기
    // => 파라미터가 있는 메서드를 호출하기
    
    // 파라미터가 있는 메서드를 호출하려면 반드시 그 파라미터가 원하는 타입의 값을 넘겨줘야 한다.
    // m1(); // 컴파일 오류!
    
    // 파라미터에 값을 전달할 때 반드시 순서를 지켜야 한다.
    // m1(20, "홍길동"); // 컴파일 오류!
    
    // 파라미터에 넘겨주는 값이 파라미터의 타입과 일치해야 한다.
    // m1(20, 30); // 컴파일 오류!
    
    // 파라미터의 개수와 파라미터에 넘겨주는 값의 개수가 일치해야 한다.
    //m1("홍길동", 20, 20); // 컴파일 오류!
    
    m1("홍길동", 20); // OK!
    m1("임꺽정", 
        30);
    m1(
        "유관순", 
        17
    );
    
    // 이렇게 메서드를 호출할 때 파라미터에 넘겨주는 값을 "아규먼트(argument)"라 부른다.
  }
  
  // 외부에서 값을 받아 실행하는 메서드는 
  // 그 값을 담을 변수를 메서드 정의에 선언해야 한다.
  // 이렇게 외부에서 값을 받는 변수를 "파라미터(parameter)"라고 부른다.
  //
  static void m1(String name, int age) {
    System.out.printf("이름: %s\n", name);
    System.out.printf("나이: %s\n", age);
  }
}














