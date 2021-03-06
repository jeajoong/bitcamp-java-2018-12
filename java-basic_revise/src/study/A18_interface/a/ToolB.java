// 인터페이스 구현 - 규칙에 따라 클래스를 만든다.
package study.A18_interface.a;

// 규칙에 따라 클래스를 만들면 도구를 사용하는 입장에서 
// 일관된 코딩을 할 수 있어 유지보수에 좋다.
public class ToolB implements A {

  // 인터페이스의 규칙에 따라 클래스를 작성할 때는 
  // 인터페이스에 선언된 모든 추상 메서드를 반드시 구현해야 한다.
  // 구현하지 않으면 추상 클래스를 그대로 갖고 있는 것이 된다.
  // 추상 클래스를 갖고 있는 클래스는 추상 클래스만이 가능하다.
  @Override
  public void m1() {
    System.out.println("ToolB.m1()");
  }
}
