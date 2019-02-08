// 계산기 서버 만들기
package ch23.c;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class CalculatoerServer {

  public static void main(String[] args) {
    
    try (Scanner keyboard = new Scanner(System.in);
        ServerSocket serverSocket = new ServerSocket(8888)) {
    
      
      try (
          Socket socket = serverSocket.accept();
          PrintWriter out = new PrintWriter(socket.getOutputStream());
          Scanner in = new Scanner(socket.getInputStream())) {
      
        out.write();
        
        
        
        int a = keyboard.nextInt();
        String op = keyboard.next();
        int b = keyboard.nextInt();
        
        float result = 0;
        switch (op) {
          case "+": result = plus(a, b); break;
          case "-": result = minus(a, b); break;
          case "*": result = multiple(a, b); break;
          case "/": result = divide(a, b); break;
          case "%": result = pct(a, b); break;
          default:
            System.out.println("지원하지 않는 연산자입니다.");
            return;
        }
        
        
        
      while (true) {
        String prompt = command();
        
        if (prompt.equals("quit"));
        System.out.println("안녕히 가세요!");
        break;
      }
    
}
      
  }catch (Exception e) {
    
  }
}
  
  
  
  public int plus(int a, int b) {
    return a+b;
  }
  public int minus(int a, int b) {
    return a-b;
  }
  public int multiple(int a, int b) {
    return a*b;
  }
  public int divide(int a, int b) {
    return a/b;
  }
  public float pct(int a, int b) {
    return a%b;
  }
  
}


