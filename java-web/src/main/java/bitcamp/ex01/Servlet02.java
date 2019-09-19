// 서블릿 만들기 - javax.servlet.GenericServlet 추상 클래스 상속
package bitcamp.ex01;

import java.io.IOException;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

// GenericServlet 추상 클래스
// => javax.servlet.Servlet 인터페이스를 구현하였다.
// => service() 메서드만 남겨두고 나머지 메서드들은 모두 구현하였다.
// => 따라서 이 클래스를 상속 받는 서브 클래스는 service() 만 구현하면 된다.
public class Servlet02 extends GenericServlet {
  
  // GenericServlet 추상 클래스는 java.io.Serialize 인터페이스를 구현하였다.
  // => serialVersionUID 변수 값을 설정해야 한다.
  //    - 객체를 저장하고 불러올때 이 serialVersionUID 값을 가지고 불러온다는 뜻이며
  //    - serialVersionUID의 값을 직접 할당하지 않고 컴파일러에게 맡긴다면 
  //       아래의 경우 제대로 객체를 불러올 수 없을 것이다.
  //    ● 저장하는 쪽의 컴파일러와 불러오는 쪽의 컴파일러가 다를 경우
  //    ● 저장하는 시기의 클래스 내용과 불러오는 시기의 클래스 내용이 다를 경우
  private static final long serialVersionUID = 1L;

  
  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {
    System.out.println("Servlet02.service(ServletRequest,ServletResponse)");
  }
}







