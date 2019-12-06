// 자바는 다중 상속을 허락하지 않는다.
package study.A13_inheritance.f;

public class Test03 extends A, X { // 컴파일 오류!

  public static void main(String[] args) {
    // 왜 다중 상속을 허락하지 않는가?
    Test03 obj = new Test03();
    
    obj.m1(); // 어떤 m1() 호출해야 하는가? A 클래스에도 m1()이 있고, X 클래스에도 m1() 있다.
    
    // 메서드를 호출할 때 수퍼 클래스들 사이에 같은 이름의 메서드가 여러 개 있을 경우
    // 그 중 어떤 것을 호출할 지 결정할 수 없기 때문에 다중 상속을 허락하지 않는다.
    // C++은 다중 상속이 가능한데, C++ 창시자가 다중 상속에 대해 잘못되었다고 시인했다!
    // 
  }

}
