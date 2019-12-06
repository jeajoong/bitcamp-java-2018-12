package study.design_patterns.chain_of_responsibility;

public class TerminalHandler extends AbstractHandler{
  @Override
  public void handle(int a, int b, String op) {
    // 이 핸들러는 연결의 끝을 표현하기 위해 만든것이다.
    System.out.println(op + "해당연산자를 지원하지 않습니다");
  }


}
