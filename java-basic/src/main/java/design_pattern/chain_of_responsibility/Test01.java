package design_pattern.chain_of_responsibility;

import java.util.Scanner;

public class Test01 {
  
  public static void main(String[] args) {
    Scanner keyScan = new Scanner(System.in);
    TerminalHandler terminalHandler = new TerminalHandler();
    PlusHandler plusHandler = new PlusHandler();
    MinusHandler minusHandler = new MinusHandler();
    MultipleHandler multipleHandler = new MultipleHandler();
    
    plusHandler.setNext(terminalHandler);
    // - 연산을 처리하고 싶다면 그 작업을 수행하는 객체를 만들어 체인에 연결하라
    minusHandler.setNext(plusHandler);

    multipleHandler.setNext(minusHandler);
    
    while (true) {
      System.out.print("a? ");
      int a = keyScan.nextInt();
      
      System.out.print("b? ");
      int b = keyScan.nextInt();

      System.out.print("연산자? ");
      String op = keyScan.next();
      
      minusHandler.handle(a, b, op);
    }
    
  }
}
