// 계산기 서버 만들기
package ch23.c;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
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
            DataInputStream in = new DataInputStream(
          new BufferedInputStream(socket.getInputStream()))
          ) {
      
        out.write("계산기 서버에 오신 걸 환영합니다!");
        out.write("계산식을 입력하세요!");
        out.write("예) 23 + 7"); 
        
        
        while (true) {
        
        int a = in.readInt();
        String op = in.readUTF();
        int b = in.readInt();
        
        float result = 0;
        
        switch (op) {
          case "+": result = plus(a, b); break;
          case "-": result = minus(a, b); break;
          case "*": result = multiple(a, b); break;
          case "/": result = divide(a, b); break;
          case "%": result = pct(a, b); break;
          default:
            return;
        }
        out.write((int) result);
        
        if(in.readUTF() == "^") {
          out.write("^ 연산자를 지원하지 않습니다.");
        }
        
        else {
          out.write("식의 형식이 잘못되었습니다.");
        }
        
         if (in.readUTF() == "quit")
          out.write("안녕히 가세요!");
        break;
        
      }
      }
  }catch (Exception e) {
  }
}
  
  
  
  public static int plus(int a, int b) {
    return a+b;
  }
  public static int minus(int a, int b) {
    return a-b;
  }
  public static int multiple(int a, int b) {
    return a*b;
  }
  public static int divide(int a, int b) {
    return a/b;
  }
  public static float pct(int a, int b) {
    return a%b;
  }
  
}


